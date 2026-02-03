package com.spring.cloud.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * Nacos 不需要发现注解，自动注册
 *
 * @author xuweizhi
 */
@EnableFeignClients
@ComponentScan({"com.spring.cloud.order", "org.dromara.hmily"})
@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}
