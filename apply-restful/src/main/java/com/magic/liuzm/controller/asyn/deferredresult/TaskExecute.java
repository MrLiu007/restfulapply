package com.magic.liuzm.controller.asyn.deferredresult;

import com.magic.liuzm.BizException;
import com.magic.liuzm.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
/**
 * @author zemin.liu
 * @date 2020/12/17 11:12
 * @description 任务执行器
 */
@Slf4j
@Component
public class TaskExecute {

    private static final String CLASSNAME = "TaskExecute";

    @Autowired
    private TaskQueue taskQueue;

    @Autowired
    private OrderService orderService;

    /**
     * 初始化启动
     */
    @PostConstruct
    public void init() {
        final String flag = CLASSNAME + "init";
        log.info("flag:{},开始处理任务",flag);
        new Thread(this::execute).start();
    }

    /**
     * 消费任务
     */
    private void execute() {
        final String flag = CLASSNAME + "execute";
        while (true) {
            try {
                Task task;

                // 取出任务，并发-加锁
                synchronized (taskQueue) {
                    task = taskQueue.take();
                }
                if (task != null) {
                    log.info("flag:{},taskId:{}处理下单请求",flag,task.getTaskId());
                    String orderNo = orderService.order(task.getGoodsId());
                    log.info("flag:{},taskId:{},返回orderNo:{},处理下单请求完毕",flag,task.getTaskId(),orderNo);
                    // 返回订单编号
                    task.getTaskResult().setResult(Response.ok(orderNo));
                }else {
                    log.info("flag:{},暂无task处理",flag);
                    try {
                        // 1s拉取1次
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (BizException e) {
                e.printStackTrace();
            }
        }
    }
}
