package com.spring.boot.profile.config;

import com.spring.boot.profile.util.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SpringBoot 2.3.4 配置文件升级测试
 *
 * @author xuweizhi
 * @since 2020/11/14 16:27
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(RandomProperties.class)
public class MigrateConfig {

    @Value("${ip.host}")
    private String ipHost;

    @Bean
    public MigrateProperties migrateProperties() {
        return new MigrateProperties();
    }

    @Bean
    @ConditionalOnBean(value = MigrateProperties.class)
    public String springString() {
        log.info("current redis ip is {}", ipHost);
        MigrateProperties migrateProperties = SpringContextUtil.getBean(MigrateProperties.class);
        log.info("current migrate properties is {}", migrateProperties);
        RandomProperties randomProperties = SpringContextUtil.getBean(RandomProperties.class);
        log.info("random properties is {}", randomProperties);
        return "This is a spring ioc String";
    }

    //@Bean
    //public RandomProperties randomProperties() {
    //    return new RandomProperties();
    //}
}
