/**
 * 
 */
package com.magic.liuzm.controller.asyn.future;

import com.magic.liuzm.controller.asyn.deferredresult.OrderService;
import com.magic.liuzm.dto.Response;
import com.magic.liuzm.enums.HttpCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

/**
 * @author zemin.liu
 * @date 2020/11/24 10:46
 * @description 异步接口
 *
 * 通过java 并发库Future实现
 *
 * 优点：
 * 1.将主线程和业务线程分离，释放主线程，提高接口吞吐量
 *
 * 缺点：
 * 1.前端依旧要阻塞等待结果返回
 * 2.不够灵活，使用场景简单。
 * 3.自己维护线程处理
 *
 */
@RestController
@RequestMapping("/asyn/v3")
@Slf4j
public class AsynFutureController {

	private static final String CLASSNAME = "AsynFutureController";

	@Autowired
	private OrderService orderService;

	/**
	 * 依赖线程池
	 */
	private ExecutorService executor = Executors.newCachedThreadPool();

	@PostMapping("/orders/{id}")
	public Response<String> order(@PathVariable(name = "id", required = true) String goodsId){
		final String flag = CLASSNAME + "order";
		log.info("flag:{},goodsId:{} 接收请求开始处理...",flag,goodsId);

		Response<String> result = Response.error(HttpCodeEnum.INTERNAL_SERVER_ERROR);
		// FutureTask
		FutureTask<Response<String>> futureTask = new FutureTask(()->{
			String orderNo = orderService.order(goodsId);
			return Response.ok(orderNo);
		});
		executor.execute(futureTask);
		log.info("flag:{} 接收请求结束处理,交给任务异步处理，阻塞当前线程，等待结果...",flag);
		try {
			// 10s超时
			result = futureTask.get(10000, TimeUnit.MILLISECONDS);
		}catch (Exception e){
			e.getStackTrace();
			// 中断
			futureTask.cancel(true);
			return result;
		}finally {
			// 关闭线程池
			executor.shutdown();
		}
		return result;
	}

	@PostMapping("/orders2/{id}")
	public Response order2(@PathVariable(name = "id", required = true) String goodsId){
		final String flag = CLASSNAME + "order2";
		log.info("flag:{},goodsId:{} 接收请求开始处理...",flag,goodsId);
		Response<String> result = Response.error(HttpCodeEnum.OK);

		// CompletableFuture,无返回值
		CompletableFuture.runAsync(() -> orderService.order(goodsId));
		log.info("flag:{} 接收请求结束处理,交给任务异步处理，阻塞当前线程，等待结果...",flag);
		return result;
	}
}
