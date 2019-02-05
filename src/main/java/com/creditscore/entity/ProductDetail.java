package com.creditscore.entity;


public class ProductDetail {
    private String productName;
    private double yearlyFee;
    private String rewards;

    public ProductDetail(String productName, double yearlyFee, String rewards) {
        this.productName = productName;
        this.yearlyFee = yearlyFee;
        this.rewards = rewards;
    }

    public String getProductName() {
        return productName;
    }

    public double getYearlyFee() {
        return yearlyFee;
    }

    public String getRewards() {
        return rewards;
    }
}
