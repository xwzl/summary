package com.spring.cloud.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xuweizhi
 */
@EnableFeignClients
@SuppressWarnings("all")
@ComponentScan({"com.spring.cloud.user","org.dromara.hmily"})
@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class UserApplication {


    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(UserApplication.class, args);
        // while (true) {
        //     //当动态配置刷新时，会更新到 Enviroment中，因此这里每隔一秒中从Enviroment中获取配置
        //     String userName = applicationContext.getEnvironment().getProperty("common.name");
        //     String userAge = applicationContext.getEnvironment().getProperty("common.age");
        //     System.err.println("common name :" + userName + "; age: " + userAge);
        //     TimeUnit.SECONDS.sleep(3);
        // }
    }

}
