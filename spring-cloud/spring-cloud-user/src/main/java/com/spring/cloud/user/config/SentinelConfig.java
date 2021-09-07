// package com.spring.cloud.user.config;
//
// import com.alibaba.csp.sentinel.adapter.servlet.CommonFilter;
// import com.alibaba.csp.sentinel.adapter.servlet.callback.WebCallbackManager;
// import com.spring.cloud.user.sentinel.handler.MyUrlBlockHandler;
// import org.springframework.boot.web.servlet.FilterRegistrationBean;
// import org.springframework.context.annotation.Bean;
//
// /**
//  * Sentinel config
//  *
//  * @author xuweizhi
//  * @since 2021/09/06 16:02
//  */
// // @Configuration
// @SuppressWarnings("all")
// public class SentinelConfig {
//
//     // @Bean
//     public FilterRegistrationBean sentinelFilterRegistration() {
//         FilterRegistrationBean registration = new FilterRegistrationBean();
//         registration.setFilter(new CommonFilter());
//         registration.addUrlPatterns("/*");
//         // 入口资源关闭聚合  解决流控链路不生效的问题
//         registration.addInitParameter(CommonFilter.WEB_CONTEXT_UNIFY, "false");
//         registration.setName("sentinelFilter");
//         registration.setOrder(1);
//
//         //CommonFilter的BlockException自定义处理逻辑
//         WebCallbackManager.setUrlBlockHandler(new MyUrlBlockHandler());
//
//         //解决授权规则不生效的问题
//         //com.alibaba.csp.sentinel.adapter.servlet.callback.RequestOriginParser
//         //WebCallbackManager.setRequestOriginParser(new MyRequestOriginParser());
//
//         return registration;
//     }
//
// }
