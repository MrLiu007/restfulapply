package com.magic.liuzm.controller.asyn.webflux.jdk9;

import lombok.extern.slf4j.Slf4j;

//import java.util.concurrent.Flow;

/**
 * @author zemin.liu
 * @date 2020/12/20 16:59
 * @description 自定义消费者，消费String类型数据
 *
 * 自己实现Flow.Subscriber接口
 *
 * 注意：请将jdk改成jdk9+，并将如下代码打开
 */
//@Slf4j
//public class MySubscriber implements Flow.Subscriber<String>{
//
//    private Flow.Subscription subscription;
//
//    @Override
//    public void onSubscribe(Flow.Subscription subscription) {
//        log.info("消费者：我建立订阅关系了");
//        // 建立订阅关系
//        this.subscription = subscription;
//
//        // 关系建立之后请求一个数据，用于消费
//        this.subscription.request(1);
//    }
//
//    @Override
//    public void onNext(String item) {
//        // 处理接收数据
//        log.info("消费者：接受到数据data:{}",item);
//
//        // 若达到目标，可调用cancel(),告知生产者，自己不再请求数据
//        if("9".equals(item)){
//            this.subscription.cancel();
//        }else {
//            // 请求下一条数据，用于消费
//            this.subscription.request(1);
//        }
//    }
//
//    @Override
//    public void onError(Throwable e) {
//        // 处理数据时产生异常
//        log.info("消费者：我处理时出现异常了",e);
//
//        // 调用cancel(),告知生产者不再请求数据
//        this.subscription.cancel();
//    }
//
//    @Override
//    public void onComplete() {
//        log.info("消费者：我处理完了");
//    }
//}
