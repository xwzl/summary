package com.spring.cloud.order.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author xuweizhi
 */
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        boolean flag = true;
        // 简单的认证逻辑  从请求头中获取Authorization
        String authorization = request.getHeader("Authorization");
        log.info("=========Authorization:" + authorization);
        if (StringUtils.isEmpty(authorization)) {
            // 从请求参数中获取access_token
            String accessToken = request.getParameter("accessToken");
            if (StringUtils.isEmpty(accessToken)) {
                flag = false;
            }
        }
        return flag;
    }
}
