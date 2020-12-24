package com.magic.liuzm.controller.asyn.deferredresult;

import com.magic.liuzm.dto.Response;
import lombok.Data;
import org.springframework.web.context.request.async.DeferredResult;
/**
 * @author zemin.liu
 * @date 2020/12/17 11:07
 * @description 任务实体
 */
@Data
public class Task {

    /**
     * 任务编号
     */
    private String taskId;
    /**
     * 商品编号
     */
    private String goodsId;

    /**
     * 异步结果对象
     */
    private DeferredResult<Response<String>> taskResult;

    Task(String taskId,String goodsId,DeferredResult<Response<String>> taskResult){
        this.taskId = taskId;
        this.goodsId = goodsId;
        this.taskResult = taskResult;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                "goodsId=" + goodsId +
                ", taskResult" + "{responseMsg=" + taskResult.getResult() + "}" +
                '}';
    }
}
