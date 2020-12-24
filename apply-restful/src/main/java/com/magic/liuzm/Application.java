package com.magic.liuzm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author zemin.liu
 * @date 2020/11/24 10:57
 * @description 启动类
 */
@SpringBootApplication
@ServletComponentScan(basePackages = "com.magic.liuzm.controller.asyn.servlet31")
public class Application {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
