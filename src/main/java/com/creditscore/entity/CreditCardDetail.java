package com.creditscore.entity;


import java.util.HashMap;
import java.util.Map;

public class CreditCardDetail {

    private String bank;
    private double interest;
    private String type;
    private double limit;
    private String productName;
    private double yearlyFee;
    private String rewards;
    private double balanceTransfer;

    public CreditCardDetail() {
    }

    public CreditCardDetail(String bank, double interest, String type, double limit, double balanceTransfer, ProductDetail productDetail) {
        this.bank = bank;
        this.interest = interest;
        this.type = type;
        this.limit = limit;
        this.productName = productDetail.getProductName();
        this.yearlyFee = productDetail.getYearlyFee();
        this.rewards = productDetail.getRewards();
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

    public Map<String, Object> asMap() {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("bank", this.bank);
        paramMap.put("interest", this.interest);
        paramMap.put("type", this.type);
        paramMap.put("limit", this.limit);
        paramMap.put("productName", this.productName);
        paramMap.put("yearlyFee", this.yearlyFee);
        paramMap.put("rewards", this.rewards);
        paramMap.put("balanceTransfer", this.balanceTransfer);
        return paramMap;
    }
}
