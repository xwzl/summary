package com.spring.cloud.dubbo.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author xuweizhi
 * @since 2021/08/11 15:26
 */
@SuppressWarnings("all")
@EnableFeignClients
@SpringBootApplication
public class UserDubboConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserDubboConsumerApplication.class, args);
    }
}
