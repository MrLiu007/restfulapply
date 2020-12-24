/**
 * 
 */
package com.magic.liuzm.controller.asyn.deferredresult;

import com.magic.liuzm.dto.Response;
import com.magic.liuzm.enums.HttpCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @author zemin.liu
 * @date 2020/11/24 10:46
 * @description 异步接口
 *
 * 通过spring mvc的DeferredResult技术：底层依赖java AsyncTaskExecutor + Servlet 3.0实现（查看StandardServletAsyncWebRequest）
 * 优点：
 * 1.将主线程和业务线程分离，释放主线程，提高接口吞吐量
 *
 * 缺点：
 * 1.前端依旧要阻塞等待结果返回
 * 2.灵活，使用场景多。
 * 3.需要自己维护线程处理
 *
 */
@RestController
@RequestMapping("/asyn/v1")
@Slf4j
public class AsynDeferredResultController {

	private static final String CLASSNAME = "AsynDeferredResultController";

	// 超时时间
	private static final long OUT_OF_TIME = 10000L;

	@Autowired
	private TaskQueue taskQueue;


	@PostMapping("/orders/{id}")
	public DeferredResult<Response<String>> order(@PathVariable(name = "id", required = true) String goodsId) {
		final String flag = CLASSNAME + "order";
		log.info("flag:{},goodsId:{} 接收请求开始处理...",flag,goodsId);

		// 创建DeferredResult对象:设置超时时间,超时返回结果
		DeferredResult<Response<String>> result = new DeferredResult(OUT_OF_TIME, Response.error(HttpCodeEnum.REQUEST_TIMEOUT));

		result.onTimeout(() -> {
			log.info("flag:{} 下单请求处理超时回调",flag);
		});

		result.onCompletion(() -> {
			log.info("flag:{} 下单请求处理完成回调",flag);
		});

		// 添加任务，并发-加锁
		synchronized (taskQueue) {
			taskQueue.put(goodsId,result);
		}
		log.info("flag:{} 接收请求结束处理,交给任务异步处理，阻塞当前线程，等待结果...",flag);
		return result;
	}

}
