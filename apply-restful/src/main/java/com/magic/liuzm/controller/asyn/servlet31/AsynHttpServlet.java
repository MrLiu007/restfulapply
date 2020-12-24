package com.magic.liuzm.controller.asyn.servlet31;

import com.magic.liuzm.controller.asyn.deferredresult.OrderService;
import com.magic.liuzm.dto.Response;
import com.magic.liuzm.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.AsyncContext;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.*;

/**
 * @author zemin.liu
 * @date 2020/12/22 19:28
 * @description 异步接口
 *
 *
 * 通过异步非阻塞Servlet，依赖Servlet3.1，也就是tomcat线程池实现；
 *
 * 使用时注意点：
 * Springboot激活时，记得在Application上添加@ServletComponentScan(basePackages = "com.magic.liuzm.controller.asyn.servlet31")
 * 参考：https://www.cnblogs.com/davenkin/p/async-servlet.html
 *
 * 优点：
 * 1.将主线程和业务线程分离，释放主线程，提高接口吞吐量
 *
 * 缺点：
 * 1.前端依旧要阻塞等待结果返回
 * 2.不够灵活，使用场景简单
 * 3.用自己维护线程处理
 */
@Slf4j
@WebServlet(name = "asynServlet",urlPatterns = "/asyn/v4/orders", asyncSupported = true)
public class AsynHttpServlet extends HttpServlet {

    private static final String CLASSNAME = "AsynHttpServlet";

    @Autowired
    private OrderService orderService;

    /**
     * 依赖线程池
     */
    private ExecutorService executor = Executors.newCachedThreadPool();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String flag = CLASSNAME + "doGet";
        log.info("flag:{},接收请求开始处理...",flag);
        // 异步上下文，将主线程数据异步化
        AsyncContext asyncContext = request.startAsync();

        // 添加流监听
        ServletInputStream inputStream = request.getInputStream();
        inputStream.setReadListener(new ReadListener() {
            @Override
            public void onDataAvailable() throws IOException {
                log.info("flag:{},监听器：数据可用... ",flag);
            }

            @Override
            public void onAllDataRead() throws IOException {
                log.info("flag:{},监听器：数据处理... ",flag);
                executor.execute(() -> {
                    // 模拟-业务逻辑
                    String orderNo = orderService.order("1");
                    try {
                        // 写入数据
                        asyncContext.getResponse().getWriter().write(JsonUtils.obj2String(Response.ok(orderNo)).toCharArray());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // 异步处理完成
                    asyncContext.complete();
                });
            }

            @Override
            public void onError(Throwable e) {
                log.info("flag:{},监听器：数据处理异常... ",flag,e);

                // 出现异常，异步处理完成
                asyncContext.complete();
            }
        });
    }
}
