package com.spring.cloud.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.stereotype.Component;

/**
 * 业务断言
 *
 * @author xuweizhi
 * @since 2021/10/21 10:56
 */
@Slf4j
@Component
@SuppressWarnings("all")
public class CheckAuthGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {

    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return (exchange, chain) -> {
            log.info("调用CheckAuthGatewayFilterFactory===" + config.getName() + ":" + config.getValue());
            return chain.filter(exchange);
        };
    }
}
