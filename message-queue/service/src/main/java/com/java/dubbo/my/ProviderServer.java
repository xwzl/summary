package com.java.dubbo.my;


import com.java.dubbo.my.api.BookService;
import com.java.dubbo.my.api.impl.BookServiceImpl;
import com.java.dubbo.my.framework.Protocol;
import com.java.dubbo.my.framework.URL;
import com.java.dubbo.my.framework.ZookeeperRegister;
import com.java.dubbo.my.framework.config.ApplicationConfig;
import com.java.dubbo.my.framework.config.ZookeeperConfig;
import com.java.dubbo.my.framework.protocol.auto.AutoService;
import com.java.dubbo.my.framework.protocol.auto.AutoValue;
import com.java.dubbo.my.framework.protocol.http.HttpServer;
import com.java.dubbo.my.framework.register.LocalRegister;
import com.java.dubbo.my.framework.register.RemoteMapRegister;
import com.java.dubbo.my.utils.ApplicationFactory;
import com.java.dubbo.my.utils.proxy.ProtocolProxyFactory;

import java.lang.reflect.Field;
import java.util.Optional;

/**
 * 服务提供者 server
 * ,
 *
 * @author xuweizhi
 * @since 2021/05/25 15:27
 */
public class ProviderServer {

    public static void main(String[] args) {
        // 1. 注册本地服务
        URL url = new URL("localhost", 8003);
        // 基本初始化流程
        //baseInitServiceServer(url);
        customerInitServiceServer(url);

        Protocol protocol = ProtocolProxyFactory.getProtocol();
        protocol.start(url);

        // 3. 启动服务
        //baseServer(url);
    }

    public static void customerInitServiceServer( URL url) {
        try {
           ApplicationFactory applicationFactory = ApplicationFactory.getInstance(ProviderServer.class.getPackage().getName());
            // 解析配置类
            for (Class<?> config : applicationFactory.getConfigs()) {
                Object configInstance = config.getConstructor().newInstance();
                Field[] fields = config.getDeclaredFields();
                for (Field field : fields) {
                    Optional.ofNullable(field.getAnnotation(AutoValue.class)).ifPresent(autoValue -> {
                        applicationFactory.getResourcesUtils().resolver(config, field, configInstance);
                    });
                }
                applicationFactory.addSingleton(config.getName(), configInstance);
            }
            // 注册服务类
            ZookeeperConfig zookeeperConfig = applicationFactory.getBean(ZookeeperConfig.class);
            for (Class<?> serviceImpl : applicationFactory.getServices()) {
                if (serviceImpl.getAnnotation(AutoService.class) != null) {
                    Class<?>[] services = serviceImpl.getInterfaces();
                    for (Class<?> service : services) {
                        LocalRegister.register(service.getName(), serviceImpl);
                        RemoteMapRegister.regist(service.getName(), url);
                        if (zookeeperConfig.getEnable() != null && zookeeperConfig.getEnable()) {
                            ZookeeperRegister.register(service.getName(), url);
                        }
                    }
                }
            }
            ApplicationConfig.applicationFactory = applicationFactory;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void baseInitServiceServer(URL url) {
        // 本地服务列表，只有当前进程能够获取，因此写入本地文件中
        LocalRegister.register(BookService.class.getName(), BookServiceImpl.class);
        RemoteMapRegister.regist(BookService.class.getName(), url);
        ZookeeperRegister.register(BookService.class.getName(), url);
    }

    private static void baseServer(URL url) {
        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostname(), url.getPort());
    }
}
