package com.creditscore.repository;

import com.creditscore.entity.CreditCardDetail;
import com.creditscore.entity.ProductDetail;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Repository("elasticsearchAsyncRepository")
public class ElasticAsyncRepositoryImpl implements ElasticAsyncRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticAsyncRepositoryImpl.class);

    private RestHighLevelClient restHighLevelClient;

    @Autowired
    public ElasticAsyncRepositoryImpl(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    //search query
    //builder
    //hits
    @Override
    public CompletionStage<List<CreditCardDetail>> listallCreditCardDetails() {
        CompletableFuture<List<CreditCardDetail>> promise = new CompletableFuture<>();

        SearchRequest searchRequest1 = new SearchRequest("creditscore").types("offer");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(
                QueryBuilders.boolQuery()
                        .must(
                                QueryBuilders.matchAllQuery()
                        )
        );

        searchRequest1.source(builder);

        restHighLevelClient.searchAsync(searchRequest1, RequestOptions.DEFAULT, new ActionListener<SearchResponse>() {
            @Override
            public void onResponse(SearchResponse searchResponse) {
                LOGGER.info("response received from elasticsearch. async");
                SearchHit[] searchHits = searchResponse.getHits().getHits();
                List<CreditCardDetail> creditCardDetails = new ArrayList<>();
                for (SearchHit searchHit : searchHits) {
                    Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
                    CreditCardDetail detail = new CreditCardDetail(
                            String.valueOf(sourceAsMap.get("bank")),
                            Double.parseDouble(sourceAsMap.get("interest").toString()),
                            String.valueOf(sourceAsMap.get("type")),
                            Double.parseDouble(sourceAsMap.get("limit").toString()),
                            Double.parseDouble(sourceAsMap.get("balanceTransfer").toString()),
                            new ProductDetail(String.valueOf(sourceAsMap.get("productName")),
                                    Double.valueOf(sourceAsMap.get("yearlyFee").toString()),
                                    String.valueOf(sourceAsMap.get("rewards"))
                            )
                    );
                    creditCardDetails.add(detail);
                }
                promise.complete(creditCardDetails);
            }

            @Override
            public void onFailure(Exception e) {
                promise.exceptionally(t -> {
                    LOGGER.error("error encountered where processing. returning empty", e);
                    return new ArrayList<>();
                });
                //can also directly throw the exception received if the client is interested in the error
                //promise.completeExceptionally(e);
            }
        });


        return promise;
    }


}





