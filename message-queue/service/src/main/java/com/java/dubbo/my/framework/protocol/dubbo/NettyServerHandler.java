package com.java.dubbo.my.framework.protocol.dubbo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Netty Server Handler
 *
 * @author xuweizhi
 * @since 2021/05/25 15:06
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 真正处理业务的 handler
     */
    private final ChannelHandler channelHandler;

    public NettyServerHandler(ChannelHandler channelHandler) {
        this.channelHandler = channelHandler;
    }

    /**
     * 处理逻辑
     *
     * @param ctx ctx
     * @param msg msg
     * @throws Exception exception
     */
    @Override
    @SuppressWarnings("all")
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Invocation invocation = (Invocation) msg;
        channelHandler.handler(ctx, invocation);
        //Class<?> serviceImpl = LocalRegister.get(invocation.getInterfaceName());
        //Method method = serviceImpl.getMethod(invocation.getMethodName(), invocation.getParamTypes());
        //Object result = method.invoke(serviceImpl.newInstance(), invocation.getParams());
        //System.out.println("Netty-------------" + JSON.toJSONString(result));
        //ctx.writeAndFlush(JSON.toJSONString(result));
    }
}
