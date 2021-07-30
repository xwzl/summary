package com.summary.elasticsearch.client;

import lombok.Data;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * ElasticSearch Client Example
 *
 * @author xuweizhi
 * @since 2021/07/27 19:04
 */
public class ElasticSearchExample {

    @Data
    public static class Client {

        private RestHighLevelClient restHighLevelClient;

        public Client(RestHighLevelClient restHighLevelClient) {
            // 建立与ES的连接
            // 1. 使用RestHighLevelClient构建客户端连接。
            // 2. 基于RestClient.builder方法来构建RestClientBuilder
            // 3. 用HttpHost来添加ES的节点
            RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost("localhost", 9200, "http")
                    , new HttpHost("localhost", 9201, "http"), new HttpHost("localhost", 9202, "http"));
            restHighLevelClient = new RestHighLevelClient(restClientBuilder);
        }
    }
}
