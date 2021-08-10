package com.spring.cloud.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//配置多个   RibbonConfig不能被@SpringbootApplication的@CompentScan扫描到，否则就是全局配置的效果
//@RibbonClients(value = {
//        // 在SpringBoot主程序扫描的包外定义配置类
//        @RibbonClient(name = "mall-order",configuration = com.tuling.mall.rule.RibbonConfig.class),
//        @RibbonClient(name = "mall-account",configuration = com.tuling.mall.rule.RibbonConfig.class)
//})
@SuppressWarnings("all")
public class RibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(RibbonApplication.class, args);
    }
}
