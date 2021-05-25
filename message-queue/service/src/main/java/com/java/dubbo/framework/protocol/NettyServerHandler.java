package com.java.dubbo.framework.protocol;

import com.java.dubbo.my.framework.protocol.dubbo.ChannelHandler;
import com.java.dubbo.my.framework.protocol.dubbo.Invocation;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    private ChannelHandler channelHandler;

    public NettyServerHandler(ChannelHandler channelHandler) {
        this.channelHandler = channelHandler;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Invocation invocation = (Invocation) msg;
        channelHandler.handler(ctx, invocation);
    }
}
