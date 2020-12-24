package com.magic.liuzm.controller.asyn.webflux.jdk9;


import com.sun.tools.javac.comp.Flow;
import lombok.extern.slf4j.Slf4j;

//import java.util.concurrent.Flow;
//import java.util.concurrent.SubmissionPublisher;

/**
 * @author zemin.liu
 * @date 2020/12/19 23:59
 * @description 自定义Processor实现：Integer输入，String输出
 *
 * 自己实现Flow.Processor接口，实现与消费者相同方法
 * 自己继承SubmissionPublisher类，实现了subscribe方法
 *
 * 注意：请将jdk改成jdk9+，并将如下代码打开
 */
//@Slf4j
//public class MyProcessor extends SubmissionPublisher<String> implements Flow.Processor<Integer, String> {
//
//    private Flow.Subscription subscription;
//
//    @Override
//    public void onSubscribe(Flow.Subscription subscription) {
//        log.info("处理器：我建立订阅关系了");
//        // 建立订阅关系
//        this.subscription = subscription;
//
//        // 关系建立之后请求一个数据，开始处理数据
//        this.subscription.request(1);
//    }
//
//    @Override
//    public void onNext(Integer item) {
//        // 处理接收数据
//        log.info("处理器：我接受到数据:{}: " , item);
//
//        if (item > 0) {
//            // 只发布data大于0的数据
//            String newData =  "我是转换后的数据" + item;
//            log.info("处理器：我处理后数据:{}: " , newData);
//            this.submit(newData);
//        }
//
//        // 若达到目标，可调用cancel(),告知生产者不再请求数据
//        if(item == 9){
//            this.subscription.cancel();
//        }else {
//            // 请求下一条数据，开始处理数据
//            this.subscription.request(1);
//        }
//    }
//
//    @Override
//    public void onError(Throwable e) {
//        // 处理数据时产生异常
//        log.info("处理器：我处理时出现异常了",e);
//
//        // 调用cancel(),告知生产者不再请求数据
//        this.subscription.cancel();
//    }
//
//    @Override
//    public void onComplete() {
//        log.info("处理器：我处理完了");
//
//        // 调用close(),关闭生产者
//        this.close();
//    }
//}
