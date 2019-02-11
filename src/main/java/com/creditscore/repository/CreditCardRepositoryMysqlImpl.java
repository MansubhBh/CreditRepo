package com.creditscore.repository;

import com.creditscore.entity.CreditCardDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by boys on 7/2/19.
 */
@Repository("mysqlCardRepository")
public class CreditCardRepositoryMysqlImpl implements CreditCardRepository {

    @Override
    public CreditCardDetail createCreditCard(CreditCardDetail creditCardDetail) {
        return null;
    }

    @Override
    public List<CreditCardDetail> listallCreditCardDetails() {
        return null;
    }

    @Override
    public List<CreditCardDetail> search(String keyword) {
        return null;
    }

    @Override
    public List<CreditCardDetail> searchDetail(String bank, String type) {
        return null;
    }
}
