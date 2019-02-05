package com.creditscore.entity;

import java.math.BigDecimal;

/**
 * Created by boys on 5/2/19.
 */
public class CreditCardDetail {

    private String bank;
    private double interest;
    private String type;
    private double limit;
    private  String productName;
    private double yearlyFee;
    private String rewards;
    private double balanceTransfer;

    public CreditCardDetail() {
    }

    public CreditCardDetail(String bank, double interest, String type, double limit, String productName, double yearlyFee, String rewards, double balanceTransfer) {
        this.bank = bank;
        this.interest = interest;
        this.type = type;
        this.limit = limit;
        this.productName = productName;
        this.yearlyFee = yearlyFee;
        this.rewards = rewards;
        this.balanceTransfer = balanceTransfer;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getYearlyFee() {
        return yearlyFee;
    }

    public void setYearlyFee(double yearlyFee) {
        this.yearlyFee = yearlyFee;
    }

    public String getRewards() {
        return rewards;
    }

    public void setRewards(String rewards) {
        this.rewards = rewards;
    }

    public double getBalanceTransfer() {
        return balanceTransfer;
    }

    public void setBalanceTransfer(double balanceTransfer) {
        this.balanceTransfer = balanceTransfer;
    }
}
