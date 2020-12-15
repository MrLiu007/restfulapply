package com.magic.liuzm;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.matching.StringValuePattern;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import java.io.IOException;

/**
 * @author zemin.liu
 * @date 2020/12/14 17:48
 * @description WireMock Server
 *
 *
 * WireMock官网：http://wiremock.org/
 *
 * Mock相关知识请看项目：https://github.com/MrLiu007/mockapply
 */
public class WireMockServer {

    /**
     * 一适应场景：
     *
     * 当开发人员的开发进度不一致时，可依赖WireMock构建的接口，模拟不同请求与响应，从而避某一模块的开发进度
     *
     * 二实现步骤：
     *
     * 1.安装WireMock服务器
     * java -jar wiremock-standalone-2.27.2.jar --port 8082（下载较慢，放到resource/jar下）
     *
     *
     * 参考官网或者下载jar : http://wiremock.org/docs/running-standalone/
     *
     * 2.pom引入WireMock依赖
     *
     * 参考官网：http://wiremock.org/docs/getting-started/
     *
     * 3.运行WireMock客户端(伪造Restful api)
     *  通过WireMock类进行构造（见下面例子），注入wiremock，即使下面main方法结束，也可访问，若修改代码再次执行例子就行。
     *
     * 4.浏览器范围api接口：
     * http://localhost:8082/api/v1/schools/1
     *
     *
     */
    public static void main(String[] args) throws IOException {
        // 指定wiremock服务器的端口
        WireMock.configureFor(8082);
        // 清除配置
        WireMock.removeAllMappings();
        // 从本地拉取响应json（可从接口api文档或者工具中取）
        ClassPathResource resource = new ClassPathResource("mock/" + "get_school.txt");
        String content = StringUtils.join(FileUtils.readLines(resource.getFile(), "UTF-8").toArray(), "\n");
        System.out.println(content);
        // 为请求"/schools/{id}"伪造response
        String requestUrl = "/api/v1/schools/1";
        // 其中添加.withHeader("content-type", "application/json")能解决乱码问题
        WireMock.stubFor(
                WireMock.get(WireMock.urlPathEqualTo(requestUrl))
                        .willReturn(WireMock.aResponse().withHeader("content-type", "application/json").withBody(content).withStatus(200)));
    }
}
