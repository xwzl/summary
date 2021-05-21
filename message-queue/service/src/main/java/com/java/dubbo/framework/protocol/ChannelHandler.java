package com.java.dubbo.framework.protocol;

import io.netty.channel.ChannelHandlerContext;

public interface ChannelHandler {

    void handler(ChannelHandlerContext ctx, Invocation invocation) throws Exception;

}
