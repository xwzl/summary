package com.java.dubbo.my.framework.protocol.http;

import com.java.dubbo.my.framework.protocol.dubbo.Invocation;
import com.java.dubbo.my.framework.Protocol;
import com.java.dubbo.my.framework.URL;

/**
 * http 协议
 *
 * @author xuweizhi
 * @since 2021/05/25 18:20
 */
public class HttpProtocol implements Protocol {

    @Override
    public void start(URL url) {
        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostname(), url.getPort());
    }

    @Override
    public String send(URL url, Invocation invocation) {
        HttpClient httpClient = new HttpClient();
        return httpClient.send(url.getHostname(), url.getPort(), invocation);
    }
}
