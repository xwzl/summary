package com.java.dubbo.my.framework.register;

import java.util.HashMap;
import java.util.Map;


/**
 * 本地服务列表缓存
 *
 * @author xuweizhi
 * @since 2021/05/25
 */
public class LocalRegister {

    /**
     * 服务提供者实现缓存
     */
    private static final Map<String, Class<?>> PROVIDER_SERVICES = new HashMap<>();

    public static void register(String interfaceName, Class<?> implClass) {
        PROVIDER_SERVICES.put(interfaceName, implClass);
    }

    public static Class<?> get(String interfaceName) {
        return PROVIDER_SERVICES.get(interfaceName);
    }
}
