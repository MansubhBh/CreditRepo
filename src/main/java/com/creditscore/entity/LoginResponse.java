package com.creditscore.entity;


public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private long validity;

    public LoginResponse(String accessToken, String refreshToken, long validity) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.validity = validity;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public long getValidity() {
        return validity;
    }

    public void setValidity(long validity) {
        this.validity = validity;
    }
}
