package com.java.provider.service;

import com.java.common.module.providers.DemoService;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.protocol.rest.support.ContentType;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * http 的方式访问就行了,两种协议共同存在是，service 必须指定具体协议，不然两个协议都会去创建，报错无法启动
 *
 */
@Path("demo")
@Service(version = "rest")
// @Service(version = "rest",protocol = "rest")
public class RestDemoService implements DemoService {

    @GET
    @Path("say")
    @Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
    @Override
    public String sayHello(@QueryParam("name") String name) {
        System.out.println("执行了rest服务" + name);

        URL url = RpcContext.getContext().getUrl();
        return String.format("%s: %s, Hello, %s", url.getProtocol(), url.getPort(), name);  // 正常访问
    }

}
