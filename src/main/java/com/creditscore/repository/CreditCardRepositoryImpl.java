package com.creditscore.repository;

import com.creditscore.entity.CreditCardDetail;

import java.util.ArrayList;
import java.util.List;

public class CreditCardRepositoryImpl implements CreditCardRepository {

    @Override
    public List<CreditCardDetail> prepareStaticData() {

        List<CreditCardDetail> creditCardDetails = new ArrayList<>();
        creditCardDetails.add(new CreditCardDetail("Bank of China", 10.14, "Visa Card", 10000, "Holiday Offer", 1500, "Macbook", 1000));
        creditCardDetails.add(new CreditCardDetail("American Express", 20.74, "Credit Card", 5000, " American Express Explorer® Credit Card", 395, "400 Travel Credit ", 2400));
        creditCardDetails.add(new CreditCardDetail("Citi", 9.01, "Credit Card", 6000, "New Year Offer", 1500, "300 Travel credit", 99));
        creditCardDetails.add(new CreditCardDetail("Bank West", 13.09, "Visa Card", 8000, "Christmas Offer", 1300, "Beats Headphone", 3000));
        creditCardDetails.add(new CreditCardDetail("Coles", 5.14, "credit Card", 7000, "Dashai Offer", 1250, "LCD TV", 1300));

        return creditCardDetails;

    }
}
