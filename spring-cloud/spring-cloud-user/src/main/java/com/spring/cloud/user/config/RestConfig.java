package com.spring.cloud.user.config;


import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.spring.cloud.commom.utils.GlobalExceptionUtil;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author xuweizhi
 */
@Configuration
@SuppressWarnings("all")
public class RestConfig {

    // /**
    //  * 注入负载均衡策略
    //  */
    // @Autowired
    // private LoadBalancerClient loadBalancer;

    @Bean
    // @LoadBalanced
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        // restTemplate.setInterceptors(Collections.singletonList(new LoadBalancerInterceptor(loadBalancerClient)));
        return restTemplate;
    }

    /**
     * Sentinel 通过 ClientHttpRequestInterceptor 注入 RestTemplate 实现限流等逻辑,
     * 流控和熔断逻辑在 spring-cloud-user 服务而不是在 spring-cloud-order 服务
     */
    @Bean
    @LoadBalanced
    @SentinelRestTemplate(
            fallbackClass = GlobalExceptionUtil.class, fallback = "fallback",
            blockHandler = "handleException", blockHandlerClass = GlobalExceptionUtil.class
    )
    public RestTemplate sentinelRestTemplate() {
        return new RestTemplate();
    }

}
