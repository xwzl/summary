package com.java.dubbo.my.framework.protocol.dubbo;

import com.alibaba.fastjson.JSON;
import com.java.dubbo.my.framework.register.LocalRegister;
import io.netty.channel.ChannelHandlerContext;

import java.lang.reflect.Method;

/**
 * 请求处理 handler
 *
 * @author xuweizhi
 * @since  2021/05/25
 */
public class RequestHandler implements ChannelHandler {

    @Override
    public void handler(ChannelHandlerContext ctx, Invocation invocation) throws Exception {
        Class<?> serviceImpl = LocalRegister.get(invocation.getInterfaceName());
        Method method = serviceImpl.getMethod(invocation.getMethodName(), invocation.getParamTypes());
        Object result = method.invoke(serviceImpl.newInstance(), invocation.getParams());
        // 返回服务结果
        ctx.writeAndFlush(JSON.toJSONString(result));
    }
}
