package com.spring.zookeeper.distributedlock.zkcfg;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 腾讯课堂-图灵学院  郭嘉
 * @Slogan 致敬大师，致敬未来的你
 */
@Configuration
public class CuratorCfg {

    @Bean(initMethod = "start")
    public CuratorFramework curatorFramework(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        return CuratorFrameworkFactory.newClient("192.168.232.10:2181", retryPolicy);
    }








}
