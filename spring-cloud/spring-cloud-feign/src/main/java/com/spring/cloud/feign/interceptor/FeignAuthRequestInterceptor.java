package com.spring.cloud.feign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.util.UUID;

@SuppressWarnings("all")
public class FeignAuthRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        // 业务逻辑  模拟认证逻辑
        String accessToken = UUID.randomUUID().toString();
        template.header("Authorization",accessToken);
    }
}
