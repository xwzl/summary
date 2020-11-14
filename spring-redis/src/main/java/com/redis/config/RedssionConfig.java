package com.redis.config;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Redssion config 设置
 *
 * @author xuweizhi
 * @since 2020/11/14 18:13
 */
@Configuration
public class RedssionConfig {

    @Value("${ip.host}")
    private String ipHost;

    @Bean
    public Redisson test() {
        Config config = new Config();
        config.useClusterServers().addNodeAddress("redis://"+ipHost + ":9000", "redis://"+ipHost + ":9001",
                "redis://"+ipHost + ":9002", "redis://"+ipHost + ":9003",
                "redis://"+ipHost + ":9004", "redis://"+ipHost + ":9005").setPassword("zhuge");

        return (Redisson) Redisson.create(config);
    }
}
