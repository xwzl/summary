package com.summar.elasticsearch.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author xuweizhi
 * @since 2021/07/19 18:22
 */
@Slf4j
@Component
public class SpringBootStartListener implements ApplicationRunner {

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Swagger-ui 地址: http://localhost:8080/" + applicationName + "/doc.html");
    }
}
