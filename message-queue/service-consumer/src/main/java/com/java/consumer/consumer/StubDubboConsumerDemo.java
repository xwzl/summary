package com.java.consumer.consumer;

import com.java.common.module.providers.DemoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

/**
 * 本地存根，名字很抽象，但实际上不难理解，本地存根就是一段逻辑，这段逻辑是在服务消费端执行的，这段逻辑一般都是由服务提供者提供，服务提供者
 * 可以利用这种机制在服务消费者远程调用服务提供者之前或之后再做一些其他事情，比如结果缓存，请求参数验证等等。
 */
@EnableAutoConfiguration
public class StubDubboConsumerDemo {

    /**
     *  stub = "true" 实际调用 com.java.common.module.providers.DemoService+Stub 实现
     */
//    @Reference(version = "timeout", timeout = 1000, stub = "com.tuling.DemoServiceStub")
    @Reference(version = "timeout", timeout = 1000, stub = "true")
    private DemoService demoService;

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(StubDubboConsumerDemo.class);

        // 实际调用这个方法 com.java.common.module.providers.DemoServiceStub
        DemoService demoService = context.getBean(DemoService.class);

        System.out.println((demoService.sayHello("周瑜")));


    }

}
