package com.java.dubbo.my.framework.protocol.dubbo;

import io.netty.channel.ChannelHandlerContext;

/**
 * 防止黄色下划线提示
 */
@SuppressWarnings("all")
public class Task implements Runnable {

    private Invocation invocation;
    private ChannelHandler channelHandler;
    private ChannelHandlerContext ctx;


    public Task(Invocation invocation, ChannelHandler channelHandler, ChannelHandlerContext ctx) {
        this.invocation = invocation;
        this.channelHandler = channelHandler;
        this.ctx = ctx;
    }

    @Override
    public void run() {
        try {
            channelHandler.handler(ctx, invocation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
