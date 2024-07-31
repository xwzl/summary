//package com.spring.cloud.user.sentinel.handler;
//
//import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.stereotype.Component;
//
//
///**
// * MyRequestOriginParser
// *
// * @author xuweizhi
// * @since 2021/09/06 16:02
// */
//@Component
//public class MyRequestOriginParser implements RequestOriginParser {
//    /**
//     * 通过request获取来源标识，交给授权规则进行匹配
//     *
//     * @param request
//     * @return
//     */
//    @Override
//    public String parseOrigin(HttpServletRequest request) {
//        // 标识字段名称可以自定义   serviceName = order
//        String origin = request.getParameter("serviceName");
////        if (StringUtil.isBlank(origin)){
////            throw new IllegalArgumentException("serviceName参数未指定");
////        }
//        return origin;
//    }
//}
