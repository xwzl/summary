package com.spring.cloud.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Gateway专用测试接口
 *
 * @author xuweizhi
 * @since 2021/10/21 16:34
 */
@Slf4j
@RestController
@RequestMapping("gateway")
public class GatewayController {

    @GetMapping("/people/{name}")
    public String peopleName(@PathVariable("name") String name, HttpServletRequest httpServletRequest) {
        String header = httpServletRequest.getHeader("X-Request-color");
        // 网关添加的参数
        String red = httpServletRequest.getParameter("red");
        log.info("Request path variable {},header {}，gateway query parameter {}", name, header, red);
        return header;
    }

    @RequestMapping("different")
    public String different() {
        return "user";
    }

    @RequestMapping("equalMethod")
    public String equalMethod1() {
        return "user";
    }

}
