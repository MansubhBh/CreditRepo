package com.creditscore.service;

import com.creditscore.entity.LoginResponse;
import com.creditscore.exception.UnauthorizedException;
import com.creditscore.repository.LoginRepository;
import com.google.common.hash.Hashing;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserTokenServiceImpl implements UserTokenService {

    private final LoginRepository loginRepository;

    @Autowired
    public UserTokenServiceImpl(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public LoginResponse createUserToken(String username) {
        LoginResponse loginResponse = new LoginResponse("access_" + username, "refresh_" + username, 100000);
        /*
        Encryption: AES256
         */
        return loginResponse;
    }

    @Override
    public boolean checkUserToken(String token) {
        if (Strings.isEmpty(token) || StringUtils.containsWhitespace(token)) {
            throw new RuntimeException("invalid token value");
        }
        return token.startsWith("access_");
    }

    @Override
    public LoginResponse refreshUserToken(String refreshToken) {
        if (refreshToken.startsWith("refresh_")) {
            return new LoginResponse("access_token2", "refresh_token2", 121002);
        }
        throw new UnauthorizedException("Invalid Token");
    }
}


