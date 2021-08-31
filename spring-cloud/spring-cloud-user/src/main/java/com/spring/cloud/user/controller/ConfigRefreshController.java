package com.spring.cloud.user.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 配置文件动态刷新
 *
 * @author xuweizhi
 * @since 2021/08/31 21:50
 */
@RefreshScope
@RestController
@RequestMapping("configRefresh")
public class ConfigRefreshController {

    @Value("${common.age}")
    private String age;

    @Value("${common.name}")
    private String name;

    @GetMapping("/common")
    public String hello() {
        return name + "," + age;
    }

}
