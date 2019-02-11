package com.creditscore.repository;

import com.creditscore.entity.CreditCardDetail;
import com.creditscore.entity.ProductDetail;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
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

import javax.naming.directory.SearchResult;
import java.io.IOException;
import java.util.*;

/**
 * Created by boys on 10/2/19.
 */
@Repository("elasticsearchCardRepository")
public class ElasticSearchRepository implements CreditCardRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchRepository.class);

    private RestHighLevelClient restHighLevelClient;

    @Autowired
    public ElasticSearchRepository(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    @Override
    public CreditCardDetail createCreditCard(CreditCardDetail creditCardDetail) {

        try {
            IndexRequest indexRequest = new IndexRequest("creditscore", "offer");
            Map<String, Object> data = new HashMap<>();
            data.put("bank", creditCardDetail.getBank());
            data.put("balanceTransfer", creditCardDetail.getBalanceTransfer());
            data.put("interest", creditCardDetail.getInterest());
            data.put("limit", creditCardDetail.getLimit());
            data.put("productName", creditCardDetail.getProductName());
            data.put("rewards", creditCardDetail.getRewards());
            data.put("type", creditCardDetail.getType());
            data.put("yearlyFee", creditCardDetail.getYearlyFee());
            indexRequest.source(data);

            IndexResponse index = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            if (index.status().getStatus() != 201) {
                LOGGER.warn("Indexing operation didn't reached the expected value");
            }

        } catch (IOException e) {
            LOGGER.error("Error storing new offer", e);

        }

        return creditCardDetail;
    }

    //search query
    //builder
    //hits
    @Override
    public List<CreditCardDetail> listallCreditCardDetails() {

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


        SearchRequest searchRequest1 = new SearchRequest("creditscore").types("offer");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(
                QueryBuilders.boolQuery()
                        .must(
                                QueryBuilders.matchAllQuery()
                        )
        );

        searchRequest1.source(builder);

        List<CreditCardDetail> creditCardDetails = new ArrayList<>();
        try {

            SearchResponse searchResponse = restHighLevelClient.search(searchRequest1, RequestOptions.DEFAULT);
            LOGGER.info("sync response received from elasticsearch.");
            SearchHit[] searchHits = searchResponse.getHits().getHits();

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
        } catch (IOException e) {
            LOGGER.error("Error occured while searching", e);
        }


        return creditCardDetails;
    }

    //multi_match_query => multiple fields (name, productName, bank, type)
    @Override
    public List<CreditCardDetail> search(String keyword) {

        SearchRequest searchRequest1 = new SearchRequest("creditscore").types("offer");
        SearchSourceBuilder builder = new SearchSourceBuilder();

        builder.query(
                QueryBuilders.boolQuery()
                        .must(
                                QueryBuilders.matchQuery("bank", keyword)

                        )
//                .must(
//                        QueryBuilders.matchQuery("type", type)
//                )
        );

        searchRequest1.source(builder);

        List<CreditCardDetail> creditCardDetails = new ArrayList<>();

        try {

            SearchResponse searchResponse = restHighLevelClient.search(searchRequest1, RequestOptions.DEFAULT);
            SearchHit[] searchHits = searchResponse.getHits().getHits();

            for (SearchHit searchHit : searchHits) {
                Map<String, Object> sourceMap = searchHit.getSourceAsMap();
                CreditCardDetail detail = new CreditCardDetail(
                        String.valueOf(sourceMap.get("bank")),
                        Double.parseDouble(sourceMap.get("interest").toString()),
                        String.valueOf(sourceMap.get("type")),
                        Double.parseDouble(sourceMap.get("limit").toString()),
                        Double.parseDouble(sourceMap.get("balanceTransfer").toString()),
                        new ProductDetail(String.valueOf(sourceMap.get("productName")),
                                Double.valueOf(sourceMap.get("yearlyFee").toString()),
                                String.valueOf(sourceMap.get("rewards"))
                        )
                );
                creditCardDetails.add(detail);
            }


        } catch (IOException e) {
            LOGGER.error("Error occured", e);

        }
        return creditCardDetails;
    }

    @Override
    public List<CreditCardDetail> searchDetail(String bank, String type) {

        SearchRequest searchRequest2 = new SearchRequest("creditscore").types("offer");
        SearchSourceBuilder builder = new SearchSourceBuilder();

        builder.query(
                QueryBuilders.boolQuery()
                .must(
                        QueryBuilders.matchQuery("bank", bank)
                )
                .must(
                        QueryBuilders.matchQuery("type", type)
                )
        );

        searchRequest2.source(builder);

        List<CreditCardDetail> creditCardDetails = new ArrayList<>();

        try{
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest2, RequestOptions.DEFAULT);
            SearchHit[] searchHits = searchResponse.getHits().getHits();

            for (SearchHit searchHit : searchHits){
               Map<String,Object> sourceMap =  searchHit.getSourceAsMap();
                CreditCardDetail detail = new CreditCardDetail(
                        String.valueOf(sourceMap.get("bank")),
                        Double.parseDouble(sourceMap.get("interest").toString()),
                        String.valueOf(sourceMap.get("type")),
                        Double.parseDouble(sourceMap.get("limit").toString()),
                        Double.parseDouble(sourceMap.get("balanceTransfer").toString()),
                        new ProductDetail(String.valueOf(sourceMap.get("productName")),
                                Double.valueOf(sourceMap.get("yearlyFee").toString()),
                                String.valueOf(sourceMap.get("rewards"))
                        )
                );
                creditCardDetails.add(detail);
            }

        }
        catch(IOException e){
            LOGGER.error("Error occured ", e);
        }
        return creditCardDetails;

    }
}





