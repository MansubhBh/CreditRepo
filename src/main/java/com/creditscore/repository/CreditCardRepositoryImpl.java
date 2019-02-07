package com.creditscore.repository;

import com.creditscore.entity.CreditCardDetail;
import com.creditscore.entity.ProductDetail;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository("inmemoryCardRepository")
public class CreditCardRepositoryImpl implements CreditCardRepository {

    private static final List<CreditCardDetail> creditCardDetails = new ArrayList<>();

    public CreditCardRepositoryImpl(){
        prepareStaticData();
    }


    public List<CreditCardDetail> prepareStaticData() {
        creditCardDetails.add(new CreditCardDetail("Bank of China", 10.14, "Visa Card", 10000, 1000, new ProductDetail("Holiday Offer", 1500, "Macbook")));
        creditCardDetails.add(new CreditCardDetail("American Express", 20.74, "Credit Card", 5000, 2400, new ProductDetail(" American Express Explorer® Credit Card", 395, "400 Travel Credit ")));
        creditCardDetails.add(new CreditCardDetail("Citi", 9.01, "Credit Card", 6000, 99, new ProductDetail("New Year Offer", 1500, "300 Travel credit")));
        creditCardDetails.add(new CreditCardDetail("Bank West", 13.09, "Visa Card", 8000, 3000, new ProductDetail("Christmas Offer", 1300, "Beats Headphone")));
        creditCardDetails.add(new CreditCardDetail("Coles", 5.14, "credit Card", 7000, 1300, new ProductDetail("Dashai Offer", 1250, "LCD TV")));
        return creditCardDetails;
    }


    @Override
    public CreditCardDetail createCreditCard(CreditCardDetail creditCardDetail) {
        int indextoNewAdd = creditCardDetails.size();
        if (creditCardDetail == null) {
            throw new NullPointerException("data to save cannot be null");
        }
            creditCardDetails.add(indextoNewAdd, creditCardDetail);
            return creditCardDetails.get(indextoNewAdd);
        }


    @Override
    public List<CreditCardDetail> listallCreditCardDetails() {
        if (creditCardDetails.isEmpty() ){
            return Collections.emptyList();
        }
        return creditCardDetails;
    }

    @Override
    public List<CreditCardDetail> search(String keyword) {
        return creditCardDetails.stream()
                .filter(creditCardDetail -> creditCardDetail.getBank().toLowerCase().contains(keyword.trim().toLowerCase()))
                .collect(Collectors.toList());
    }

}
