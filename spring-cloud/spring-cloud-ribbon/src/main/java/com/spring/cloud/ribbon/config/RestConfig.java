package com.spring.cloud.ribbon.config;

import com.spring.cloud.ribbon.interceptor.MyLoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


/**
 * @author xuweizhi
 */
@Configuration
public class RestConfig {

    /**
     * 2021.1 完全剔除 ribbon
     */
    @Bean
    @LoadBalanced
    // @MyLoadBalanced // 对应配置类 config 打开
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
