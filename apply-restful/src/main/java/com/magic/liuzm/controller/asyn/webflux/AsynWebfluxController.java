package com.magic.liuzm.controller.asyn.webflux;

import com.google.common.collect.Lists;
import com.magic.liuzm.controller.asyn.deferredresult.OrderService;
import com.magic.liuzm.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author zemin.liu
 * @date 2020/12/18 08:32
 * @description
 *
 * 官网（https://spring.io/reactive，https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html）
 * 更加详情使用见：https://github.com/MrLiu007/webfluxapply
 *
 * 优点：
 * 1.将主线程和业务线程分离，释放主线程，提高接口吞吐量
 * 2.不用自己维护线程处理
 *
 * 缺点：
 * 1.前端依旧要阻塞等待结果返回（但可通过事件发射方式，实现前端实时响应，见https://github.com/MrLiu007/webfluxapply）
 * 2.现在业务场景少，网关场景使用较多。
 */
@RestController
@RequestMapping("/asyn/v5")
@Slf4j
public class AsynWebfluxController {

    private static final String CLASSNAME = "AsynWebfluxController";

    @Autowired
    private OrderService orderService;

    @PostMapping("/orders/{id}")
    public Mono<Response<String>> orders(@PathVariable(name = "id", required = true) String goodsId){
        final String flag = CLASSNAME + "order";
        log.info("flag:{},goodsId:{} 接收请求开始处理...",flag,goodsId);
        String orderNo = orderService.order(goodsId);
        log.info("flag:{},goodsId:{} 返回最终数据...",flag,goodsId);
        return Mono.just(Response.ok(orderNo));
    }

    @GetMapping(value = "/orders/{ids}")
    public Flux<Response> orders(@PathVariable(value = "ids", required = true) String[] goodsIds) {
        final String flag = CLASSNAME + "orders";
        log.info("flag:{},接收请求开始处理...",flag);

        List<String> orderList = Lists.newLinkedList();
        Arrays.stream(goodsIds).distinct().forEach(goodsId ->{
            orderList.add(orderService.order(String.valueOf(goodsId)));
        });
        // 发射数据
        Flux<Response> result = Flux.fromStream(Stream.of(Response.ok(orderList)));
        log.info("flag:{},返回最终数据...",flag);
        return result;
    }
}
