package com.spring.cloud.ribbon.rule;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;


/**
 * 局部配置必须 SpringBootApplication 扫描不要的包
 *
 * @author xuweizhi
 */
// @Configuration
public class RibbonConfig {

    @Bean
    public IRule ribbonRule() {
        return new NacosRandomWithWeightRule();
    }
}
