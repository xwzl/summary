package com.java.dubbo.my.framework.protocol.dubbo;

import com.java.dubbo.my.framework.Protocol;
import com.java.dubbo.my.framework.URL;

import java.util.concurrent.ExecutionException;

/**
 * dubbo 协议实现
 *
 * @author xuweizhi
 * @since 2021/05/25
 */
public class DubboProtocol implements Protocol {

    @Override
    public void start(URL url) {
        NettyServer nettyServer = new NettyServer();
        nettyServer.start(url.getHostname(), url.getPort());

    }

    @Override
    public String send(URL url, Invocation invocation) {
        NettyClient<?> nettyClient = new NettyClient<>();
        try {
            return nettyClient.send(url.getHostname(), url.getPort(), invocation);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
