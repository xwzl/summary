package com.java.interview.java.config;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 配置文件
 *
 * @author xuweizhi
 * @since 2022/06/07 10:46
 */
@Configuration
@EnableConfigurationProperties(PropertiesConfig.TestProperties.class)

public class PropertiesConfig implements InitializingBean {


    @Resource
    private TestProperties testProperties;


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("");
    }

    @Data
    @ConfigurationProperties(prefix = "test")
    public static class TestProperties {

        private Map<String,Object> my;
    }
}
