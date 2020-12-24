package com.magic.liuzm.controller.asyn.deferredresult;

import com.magic.liuzm.BizException;
import com.magic.liuzm.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author zemin.liu
 * @date 2020/12/17 11:09
 * @description 任务队列
 */
@Slf4j
@Component
public class TaskQueue {
    private static final String CLASSNAME = "TaskQueue";

    /**
     * 自定义队列（最大队列数10）
     */
    private BlockingQueue<Task> queue = new LinkedBlockingDeque<>(10);

    /**
     * 加入任务
     * @param goodsId 商品编号
     * @param result DeferredResult对象
     */
    public void put(String goodsId,DeferredResult<Response<String>> result) throws BizException {
        final String flag = CLASSNAME + "put";
        String taskId = "T_"+ System.currentTimeMillis();
        log.info("flag:{} 任务加入队列，taskId:{}",flag,taskId);

        queue.offer(new Task(taskId,goodsId,result));
    }

    /**
     * 获取任务
   */
    public Task take() throws BizException {
        final String flag = CLASSNAME + "take";

        // 从queue移出一个
        Task task = queue.poll();
        if(task == null){
            return null;
        }

        log.info("flag:{} 获得任务，taskId:{}",flag,task.getTaskId());
        return task;
    }
}
