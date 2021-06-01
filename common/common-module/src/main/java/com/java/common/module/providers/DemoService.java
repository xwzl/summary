package com.java.common.module.providers;

import java.util.concurrent.CompletableFuture;

/**
 * @author xuweizhi
 * @since 2021/06/01 22:25
 */
public interface DemoService {
    // 同步调用方法
    String sayHello(String name);

    // 异步调用方法
    default CompletableFuture<String> sayHelloAsync(String name) {
        return null;
    };

    // 添加回调
    default String sayHello(String name, String key, DemoServiceListener listener) {
        return null;
    };
}
