package com.spring.boot.profile.boot;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @author xuweizhi
 */
@Configuration
@Import(CustomizeDeferredImportSelector.class)
public class AppConfig {

    public @Bean
    MongoClient mongoClient() {
        return MongoClients.create("mongodb://192.168.208.128:4000");
    }

    public @Bean
    MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "productdb");
    }
}
