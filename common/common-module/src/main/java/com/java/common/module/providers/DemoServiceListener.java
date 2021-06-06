package com.java.common.module.providers;

/**
 * 服务端反调消费端接口
 *
 * @author xuweizhi
 * @since 2021/06/01 22:25
 */
public interface DemoServiceListener {
    void changed(String msg);
}
