package com.java.dubbo.my.framework.protocol.dubbo;

import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 自定义 handler
 *
 * @author xuweizhi
 * @since 2021/05/25
 */
public class DispatcherHandler implements ChannelHandler {
    /**
     * 处理类
     */
    private final ChannelHandler channelHandler;

    /**
     * 线程池
     */
    private final ExecutorService executorService;

    @SuppressWarnings("all")
    public DispatcherHandler(ChannelHandler channelHandler) {
        this.channelHandler = channelHandler;
        // 自定义线程池
        executorService = Executors.newFixedThreadPool(10);

    }

    @Override
    public void handler(ChannelHandlerContext ctx, Invocation invocation) throws Exception {
        System.out.println("线程池执行");
        executorService.execute(new Task(invocation, channelHandler, ctx));
    }
}
