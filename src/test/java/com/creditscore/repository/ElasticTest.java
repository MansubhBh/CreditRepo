package com.creditscore.repository;

import com.creditscore.entity.CreditCardDetail;
import com.creditscore.entity.ProductDetail;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.util.Map;


/*
{
    "query": {
        "bool": {
            "must": {
                "match_all": {}
            }
        }
    }
}
*/

public class ElasticTest {
    public static void main(String[] args) throws Exception {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("elasticsearch", 9200, "http"))
        );


        SearchRequest searchRequest1 = new SearchRequest("creditscore").types("offer");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(
                QueryBuilders.boolQuery()
                        .must(
                                QueryBuilders.matchAllQuery()
                        )
                        .filter(
                                QueryBuilders.rangeQuery("limit")
                                        .lte("1000")
                                        .gte("0")
                        )
        );

        searchRequest1.source(builder);

        SearchResponse searchResponse = client.search(searchRequest1, RequestOptions.DEFAULT);
        long found = searchResponse.getHits().totalHits;
        //use Stream Arrays.stream(hits).map(hit -> new CreditCardDetail()).collector
        System.out.println("found = " + found);
        ObjectMapper mapper = new ObjectMapper();
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            System.out.println("hit = " + hit.getSourceAsMap());
            Map<String, Object> offer = hit.getSourceAsMap();
            CreditCardDetail detail = new CreditCardDetail(
                    String.valueOf(offer.get("bank")),
                    Double.valueOf(String.valueOf(offer.getOrDefault("interest", "0"))),
                    String.valueOf(offer.getOrDefault("type", "")),
                    0.0d,
                    0.0d,
                    new ProductDetail(String.valueOf(offer.getOrDefault("productName", "")), 0.0d, String.valueOf(offer.getOrDefault("rewards", "")))
            );
            String data = mapper.writeValueAsString(detail);
            System.out.println(data);
        }
        client.close();
    }
}
