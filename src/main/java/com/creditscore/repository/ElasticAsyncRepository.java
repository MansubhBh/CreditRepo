package com.creditscore.repository;

import com.creditscore.entity.CreditCardDetail;

import java.util.List;
import java.util.concurrent.CompletionStage;

public interface ElasticAsyncRepository {

    CompletionStage<List<CreditCardDetail>> listallCreditCardDetails();
}
