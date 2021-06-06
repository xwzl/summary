package com.java.consumer.consumer;


import com.java.common.module.providers.DemoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@EnableAutoConfiguration
public class CallbackDubboConsumerDemo {


    @Reference(version = "callback")
    private DemoService demoService;

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(CallbackDubboConsumerDemo.class);

        DemoService demoService = context.getBean(DemoService.class);

        // 用来进行callback：调用服务端后，如果服务端需要调用消费端，服务回调
        System.out.println(demoService.sayHello("周瑜", "d1", new DemoServiceListenerImpl()));
        System.out.println(demoService.sayHello("周瑜", "d2", new DemoServiceListenerImpl()));
        System.out.println(demoService.sayHello("周瑜", "d3", new DemoServiceListenerImpl()));
    }

}
