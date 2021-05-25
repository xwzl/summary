package com.java.dubbo.my;


import com.java.dubbo.my.api.BookService;
import com.java.dubbo.my.api.impl.BookServiceImpl;
import com.java.dubbo.my.framework.Protocol;
import com.java.dubbo.my.framework.URL;
import com.java.dubbo.my.framework.protocol.http.HttpServer;
import com.java.dubbo.my.framework.register.LocalRegister;
import com.java.dubbo.my.framework.register.RemoteMapRegister;
import com.java.dubbo.my.utils.proxy.ProtocolProxyFactory;

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
        URL url = new URL("localhost", 8080);
        // 本地服务列表，只有当前进程能够获取，因此写入本地文件中
        LocalRegister.register(BookService.class.getName(), BookServiceImpl.class);
        // todo 2. 注册远程服务
        RemoteMapRegister.regist(BookService.class.getName(), url);
        // 3. 启动服务
        //baseServer(url);

        Protocol protocol = ProtocolProxyFactory.getProtocol();
        protocol.start(url);
    }

    private static void baseServer(URL url) {
        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostname(), url.getPort());
    }
}
