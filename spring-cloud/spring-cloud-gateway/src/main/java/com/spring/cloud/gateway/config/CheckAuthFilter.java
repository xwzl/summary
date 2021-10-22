package com.spring.cloud.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * CheckAuthFilter 配置
 *
 * @author xuweizhi
 * @since 2021/10/21 14:28
 */
@Slf4j
@Order(-1)
@Component
public class CheckAuthFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //校验请求头中的token
        List<String> token = exchange.getRequest().getHeaders().get("token");
        log.info("token:" + token);
        if (token.isEmpty()) {
            return null;
        }
        return chain.filter(exchange);
    }
}
