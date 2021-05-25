package com.java.dubbo.my.framework;

import java.io.Serializable;

/**
 * host + port 对象
 *
 * @author xuweizhi
 * @since 2021/05/25 15:32
 */
@SuppressWarnings("all")
public class URL implements Serializable {

    private String hostname;
    private Integer port;

    public URL(String hostname, Integer port) {
        this.hostname = hostname;
        this.port = port;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
