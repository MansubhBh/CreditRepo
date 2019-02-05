package com.creditscore.repository;

import com.creditscore.entity.CreditCardDetail;

import java.util.List;

/**
 * Created by boys on 5/2/19.
 */

public interface CreditCardRepository {

    List<CreditCardDetail> prepareStaticData();
}
