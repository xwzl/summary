package com.spring.cloud.order.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * gateway controller
 *
 * @author xuweizhi
 * @since 2021/11/04 11:32
 */
@RestController
@RequestMapping("gateway1")
public class GatewayController {

    @RequestMapping("different1")
    public String different() {
        return "order";
    }

    @RequestMapping("equalMethod1")
    public String equalMethod() {
        return "order";
    }
}
