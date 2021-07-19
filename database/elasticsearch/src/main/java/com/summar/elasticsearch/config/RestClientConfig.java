package com.summar.elasticsearch.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

/**
 * ElasticSearch 配置
 *
 * @author xuweizhi
 */
@Configuration
public class RestClientConfig {


    @Value("${spring.elasticsearch.rest.uris}")
    private String urls;

    /**
     * 客户端
     *
     * @return 客户端
     */
    @Bean
    public RestHighLevelClient restHighLevelClient() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder().connectedTo(urls)
                .build();

        return RestClients.create(clientConfiguration).rest();
    }

    /**
     * TemplateConfig
     *
     * @param restHighLevelClient 客户端
     * @return 返回值
     */
    @Bean
    public ElasticsearchRestTemplate elasticsearchRestTemplate(RestHighLevelClient restHighLevelClient) {
        return new ElasticsearchRestTemplate(restHighLevelClient);
    }
}
