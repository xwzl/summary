package com.java.dubbo.my.framework.config;

import com.java.dubbo.my.utils.ApplicationFactory;

/**
 * @author xuweizhi
 * @since 2021/05/26 18:29
 */
public class ApplicationConfig {

    public static ApplicationFactory applicationFactory;

    public static ApplicationFactory getApplicationFactory() {
        return applicationFactory;
    }

    public static void setApplicationFactory(ApplicationFactory applicationFactory) {
        ApplicationConfig.applicationFactory = applicationFactory;
    }
}
