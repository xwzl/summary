package com.spring.cloud.gateway.config;

import com.spring.cloud.gateway.predicates.BusinessRoutePredicateFactory;
import org.springframework.cloud.gateway.config.conditional.ConditionalOnEnabledPredicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Gateway 配置
 *
 * @author xuweizhi
 * @since 2021/10/21 14:28
 */
@Configuration
public class GatewayAutoConfig {
    @Bean
    @ConditionalOnEnabledPredicate
    public BusinessRoutePredicateFactory businessRoutePredicateFactory() {
        return new BusinessRoutePredicateFactory();
    }
}
