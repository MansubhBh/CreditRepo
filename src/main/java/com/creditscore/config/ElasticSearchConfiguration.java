package com.creditscore.config;

import org.apache.http.HttpHost;
import org.apache.http.protocol.HTTP;
import org.elasticsearch.client.Node;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@SuppressWarnings("unused")
public class ElasticSearchConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchConfiguration.class);

    @Bean(destroyMethod = "close")
    public RestHighLevelClient restHighLevelClient(@Value("${elasticsearch.cluster}") String cluster,
                                                   @Value("${elasticsearch.host}") String host,
                                                   @Value("${elasticsearch.protocol}") String protocol) {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost(host, 9200, protocol))
                        .setFailureListener(new RestClient.FailureListener() {

                            @Override
                            public void onFailure(Node node) {
                                LOGGER.error("task=elasticsearch-client-builder, message=\"error connecting to es\", host={}, port={}", host, 9200);
                            }
                        })
        );
        return client;

    }
}
