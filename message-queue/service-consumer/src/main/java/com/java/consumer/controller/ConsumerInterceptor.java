package com.java.consumer.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.ArrayList;
import java.util.List;

public class ConsumerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 测试账号
        List<String> tester = new ArrayList<>();
        tester.add("18888888888");

        String iphone = request.getParameter("iphone");
        if (tester.contains(iphone)) {
            RpcContext.getContext().setAttachment("dubbo.tag", "tag1");
        } else {
            RpcContext.getContext().setAttachment("dubbo.tag", "tag2");
        }
        return true;
    }
}
