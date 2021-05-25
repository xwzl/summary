package com.java.dubbo.my.framework.protocol.dubbo;

import io.netty.channel.ChannelHandlerContext;

/**
 * handler 处理
 *
 * @author xuweizhi
 * @since 2021/05/25
 */
public interface ChannelHandler {

    /**
     * 处理实际业务的统一入口
     *
     * @param ctx        ctx
     * @param invocation invocation
     * @throws Exception 异常
     */
    void handler(ChannelHandlerContext ctx, Invocation invocation) throws Exception;

}
