package com.common.starter.auto.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xuweizhi
 * @since 2021/07/30 12:14
 */
@ConfigurationProperties("customer.hello")
public class HelloProperties {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

