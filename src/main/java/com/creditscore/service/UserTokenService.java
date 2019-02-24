package com.creditscore.service;


import com.creditscore.entity.LoginResponse;

public interface UserTokenService {

    LoginResponse createUserToken(String username);
    boolean checkUserToken(String token);
    LoginResponse refreshUserToken(String refreshToken);
}


