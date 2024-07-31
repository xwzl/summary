package com.common.starter.auto;

import com.common.starter.auto.properties.HelloProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.Resource;

/**
 * @author xuweizhi
 * @since 2021/07/30 12:16
 */
@Configuration
@ConditionalOnProperty(value = "customer.hello.name")
@EnableConfigurationProperties(HelloProperties.class)
public class HelloAutoConfiguration {

    @Resource
    private HelloProperties helloProperties;

    @Bean
    public IndexController indexController(){
        return new IndexController(helloProperties);
    }
}

