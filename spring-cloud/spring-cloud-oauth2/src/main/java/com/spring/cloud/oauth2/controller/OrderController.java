package com.spring.cloud.oauth2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuweizhi
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @GetMapping(value = "/{id}")
    public String getOrder(@PathVariable String id) {
        return "order id : " + id;
    }
}
