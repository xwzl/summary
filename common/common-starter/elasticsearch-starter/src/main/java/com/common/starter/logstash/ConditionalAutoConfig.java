package com.common.starter.logstash;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Conditional 测试类
 *
 * @author xuweizhi
 * @since 2021/07/20 16:13
 */
@Slf4j
@Configuration
public class ConditionalAutoConfig {

    /**
     * 类路径不存在时才允许初始化
     *
     * @return 字段名称
     */
    @Bean
    @ConditionalOnMissingClass(value = "com.summary.elasticsearch.config.RestClientConfigs")
    public String stringConditionalOnMissingClass() {
        log.info("com.summary.elasticsearch.config.RestClientConfigs class path is not exist");
        return "String conditional bean";
    }

    /**
     * classpath 中存在 DemoServiceMock 初始化
     *
     * @return 字段名称
     */
    @Bean
    @ConditionalOnClass(name = "com.java.common.module.providers.DemoServiceMock")
    public String stringConditionalOnClass() {
        log.info("DemoServiceMock is exist elasticsearch-starter not exist elasticsearch,so not init");
        return "String conditional bean";
    }

    /**
     * RestHighLevelClient bean 存在初始化,强制配置
     *
     * @return 返回值
     */
    //@Bean
    //@ConditionalOnMissingBean(value = RestHighLevelClient.class)
    //public String stringConditionalOnBean() {
    //    log.info(RestHighLevelClient.class.getName() + " bean is exist");
    //    return "String conditional bean";
    //}

}
