package com.java.dubbo.my.framework.protocol.http;

import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

/**
 * http server container
 *
 * @author xuweizhi
 * @since 2021/05/25 15:06
 */
public class HttpServer {

    /**
     * 服务容器主方法
     *
     * @param hostname 主机名称
     * @param port     端口
     */
    public void start(String hostname, Integer port) {
        // tomcat 容器
        Tomcat tomcat = new Tomcat();

        // sever 容器
        Server server = tomcat.getServer();
        Service service = server.findService("Tomcat");

        // 连接器
        Connector connector = new Connector();
        connector.setPort(port);

        // 引擎
        Engine engine = new StandardEngine();
        engine.setDefaultHost(hostname);

        // 地址
        Host host = new StandardHost();
        host.setName(hostname);

        // 上下文
        String contextPath = "";
        Context context = new StandardContext();
        context.setPath(contextPath);
        // 设置监听器
        context.addLifecycleListener(new Tomcat.FixContextListener());

        // host 设置请求上下文
        host.addChild(context);
        // engine 设置 host
        engine.addChild(host);
        // service 设置 engine
        service.setContainer(engine);
        // service 设置连接监听
        service.addConnector(connector);

        // 设置 servlet 处理请求
        tomcat.addServlet(contextPath, "dispatcher", new DispatcherServlet());
        // 拦截所有请求
        context.addServletMappingDecoded("/*", "dispatcher");

        try {
            // 容器启动
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }

    }
}
