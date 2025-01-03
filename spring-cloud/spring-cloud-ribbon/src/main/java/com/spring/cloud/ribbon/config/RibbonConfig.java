package com.spring.cloud.ribbon.config;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;


/**
 * 全局配置负载均衡策略
 */
// @Configuration
public class RibbonConfig {

    /**
     * 全局配置
     * 指定负载均衡策略
     */
    @Bean
    public IRule ribbonRule() {
        // 指定使用Nacos提供的负载均衡策略（优先调用同一集群的实例，基于随机权重）
        // mall-user  v1--- mall-order v1
        //mall-user  v2--- mall-order v2
//        return new NacosRule();
        return null;
    }
}
