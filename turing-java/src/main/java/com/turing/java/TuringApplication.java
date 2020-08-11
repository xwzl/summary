package com.turing.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 启动类
 *
 * @author xuweizhi
 * @since 2020/08/11 15:55
 */
@SpringBootApplication
public class TuringApplication {

    public static void main(String[] args) {
        SpringApplication.run(TuringApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
