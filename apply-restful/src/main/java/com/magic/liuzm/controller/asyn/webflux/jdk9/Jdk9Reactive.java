package com.magic.liuzm.controller.asyn.webflux.jdk9;

//import java.util.concurrent.SubmissionPublisher;

/**
 * @author zemin.liu
 * @date 2020/12/18 08:32
 * @description jdk9 reactive
 *
 * 1.reactive streams规范，在java语言中叫JVM上响应式流的规范（http://www.reactive-streams.org/）；
 *
 * 2.jdk9开始集成此reactive streams规范；
 *
 * 3.reactive stream规范
 * （1.四个接口
 * Publisher：发布者(生产者)
 * subscribe方法，参数为Subscriber或者子接口；
 * 它的具体实现类有：SubmissionPublisher,BlockingPushPublisher,PseudoPublisher,PullPublisher,PushPublisher,InputStreamProcessor,FileProcessor
 *
 * Subscriber：订阅者(消费者)
 * onSubscribe方法，参数为Subscription;
 * onNext方法，参数为接收到的数据;
 * onError方法，参数为接收的异常；
 * onComplete方法，无参数;
 * 它的具体实现类有：FixedContentSubscriber,RequestSubscriber,StreamSubscriber,ConsumerSubscriber
 *
 * Subscription：用于发布者与订阅者之间的通信
 * request方法(调用1次表示1次请求数据)
 * cancel方法(告知生产者，达到目标，不再请求数据)
 * 它的具体实现类：BufferedSubscription
 *
 * Processor:生产者产生数据，再对数据进行中间处理，最后消费者对数据进行消费，这也是通用流处理架构核心之处。
 * 为Publisher和Subscriber子接口。
 * 它的具体实现类：java没提供。
 *
 * （2.back pressure背压/流量控制：消费能够告诉生产者需要多少数据，那为什么要这样？
 * 就怕生产者生产数据过多，把消费者给压垮，
 * 那这谁来实现？
 * 逻辑由Subscription接口的request方法+cancel方法配合实现
 *
 */
public class Jdk9Reactive {


    /**
     * 请将jdk改成jdk9+，并将如下代码打开
     */
    public static void main(String[] args) throws Exception {
//        SubmissionPublisher<Integer> publisher = null;
//
//        try{
//            // 创建生产者（这里使用jdk自带对象），生产数据为Integer
//            publisher = new SubmissionPublisher();
//
//            // 创建处理器, 对Integer数据过滤并将其转换为String类型
//            MyProcessor processor = new MyProcessor();
//
//            // 生产者和处理器建立关系
//            publisher.subscribe(processor);
//
//            // 创建消费者，对象String类型数据进行消费
//            MySubscriber subscriber = new MySubscriber();
//
//            // 处理器与消费者建立关系
//            processor.subscribe(subscriber);
//
//            // 生产并发布数据
//            publisher.submit(-1);
//            for(int i = 0; i < 10 ;i++){
//                publisher.submit(i);
//            }
//        }catch (Exception e){
//            e.getStackTrace();
//        }finally {
//            // 关闭发布者
//            if(publisher != null){
//                publisher.close();
//            }
//        }
        // 测试场景：需主线程延迟停止, 否则数据没消费，程序就退出
        Thread.currentThread().join(5000);
    }
}
