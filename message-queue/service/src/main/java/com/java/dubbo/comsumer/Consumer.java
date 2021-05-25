package com.java.dubbo.comsumer;

import com.java.dubbo.framework.proxy.ProxyFactory;
import com.java.dubbo.provider.api.HelloService;

/**
 * 消费者
 *
 * @author xuweizhi
 * @since 2021/05/25
 */
public class Consumer {

    public static void main(String[] args) {

//        Invocation invocation = new Invocation(HelloService.class.getName(), "sayHello", new Class[]{String.class}, new Object[]{"周瑜大都督"});
//        NettyClient nettyClient = new NettyClient();
//        String result = nettyClient.send("localhost", 8080, invocation);
//        System.out.println(result);

        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        for (int i = 0; i < 10; i++) {
            String result = helloService.sayHello("周瑜大大111");
            System.out.println(result);
        }

    }
}
