package com.java.dubbo.my.framework;

import com.java.dubbo.my.framework.protocol.dubbo.Invocation;

/**
 * 协议
 *
 * @author xuweizhi
 * @since 2021/05/25 18:21
 */
public interface Protocol {
    /**
     * url 配置
     *
     * @param url 参数
     */
    void start(URL url);

    /**
     * 发送信息
     *
     * @param url        url
     * @param invocation 请求参数
     * @return 返回值
     */
    String send(URL url, Invocation invocation);
}
