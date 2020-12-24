/**
 * 
 */
package com.magic.liuzm.controller.asyn.callable;

import com.magic.liuzm.controller.asyn.deferredresult.OrderService;
import com.magic.liuzm.controller.asyn.deferredresult.TaskQueue;
import com.magic.liuzm.dto.Response;
import com.magic.liuzm.enums.HttpCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.function.Function;

/**
 * @author zemin.liu
 * @date 2020/11/24 10:46
 * @description 异步接口
 *
 * 通过java 并发库Callable实现
 *
 * 优点：
 * 1.将主线程和业务线程分离，释放主线程，提高接口吞吐量
 * 2.不用自己维护线程处理
 *
 * 缺点：
 * 1.前端依旧要阻塞等待结果返回
 * 2.不够灵活，使用场景简单。
 */
@RestController
@RequestMapping("/asyn/v2")
@Slf4j
public class AsynCallableController {

	private static final String CLASSNAME = "AsynCallableController";

	@Autowired
	private OrderService orderService;

	@PostMapping("/orders/{id}")
	public Callable<Response<String>> order(@PathVariable(name = "id", required = true) String goodsId) {
		final String flag = CLASSNAME + "order";
		log.info("flag:{},goodsId:{} 接收请求开始处理...",flag,goodsId);

		Callable<Response<String>> result = (()->{
			String orderNo = orderService.order(goodsId);
			return Response.ok(orderNo);
		});
		log.info("flag:{} 接收请求结束处理,交给任务异步处理，阻塞当前线程，等待结果...",flag);
		return result;
	}

}
