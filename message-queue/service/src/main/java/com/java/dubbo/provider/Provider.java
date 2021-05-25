package com.java.dubbo.provider;

import com.java.dubbo.framework.protocol.NettyServer;
import com.java.dubbo.framework.register.ZookeeperRegister;
import com.java.dubbo.my.framework.URL;
import com.java.dubbo.my.framework.register.LocalRegister;
import com.java.dubbo.provider.api.HelloService;
import com.java.dubbo.provider.impl.HelloServiceImpl;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 服务提供者
 *
 * @author xuweizhi
 * @since 2021/05/25
 */
public class Provider {

    public static void main(String[] args) throws UnknownHostException {
        String interfaceName = HelloService.class.getName();
        URL url = new URL(InetAddress.getLocalHost().getHostAddress(), 8081);
        LocalRegister.register(interfaceName, HelloServiceImpl.class);
        ZookeeperRegister.regist(interfaceName, url);
        NettyServer nettyServer = new NettyServer();
        nettyServer.start(url.getHostname(), url.getPort());
        System.out.printf("success, 成功暴露%s服务，地址为%s%n", interfaceName, url);
    }
}
