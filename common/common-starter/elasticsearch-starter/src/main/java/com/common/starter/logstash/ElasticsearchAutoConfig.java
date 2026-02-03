package com.common.starter.logstash;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * 配置
 *
 * @author xuweizhi
 * @since 2021/07/20 11:31
 */
@Slf4j
@Configuration
@SuppressWarnings("all")
@ConditionalOnProperty(prefix = "spring.elasticsearch.rest", name = "uris")
public class ElasticsearchAutoConfig {

    // @Resource
    // private ElasticsearchProperties elasticsearchProperties;


//    /**
//     * RestHighLevelClient bean 不存在才执行,默认配置
//     *
//     * @return 返回值
//     */
//    @Bean
//    @ConditionalOnMissingBean(value = RestHighLevelClient.class)
//    public RestHighLevelClient elasticsearchClient() {
//        List<String> uris = elasticsearchProperties.getUris();
//        ClientConfiguration clientConfiguration = ClientConfiguration.builder().connectedTo(uris.get(0)).withBasicAuth(elasticsearchProperties.getUsername(), elasticsearchProperties.getPassword()).build();
//        return RestClients.create(clientConfiguration).rest();
//    }
//
//    /**
//     * TemplateConfig
//     *
//     * @param restHighLevelClient 客户端
//     * @return 返回值
//     */
//    @Bean
//    @ConditionalOnMissingBean(value = ElasticsearchRestTemplate.class)
//    public ElasticsearchRestTemplate elasticsearchRestTemplate(RestHighLevelClient elasticsearchClient) {
//        return new ElasticsearchRestTemplate(elasticsearchClient);
//    }


}
