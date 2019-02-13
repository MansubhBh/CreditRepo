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

    /**
     * encryption key:
     * iv key:
     *
     * aes256 (username;10min;l8343;access_token)
     * hex 3428524852485248842852385428584288533535353
     *
     * aes256 (username/10min/8343/refresh_token)
     * hex ae84384384388348348384384388
     *
     *
     * @param username
     * @return
     */

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
        //decrypt the token,
        //if it can't be decrypted throw error
        //if it can be decrypted but the 10 min window has elapsed then throw expired exception
        //otherwise if it has "access_token" word it is valid
        return token.startsWith("access_");
    }

    @Override
    public LoginResponse refreshUserToken(String refreshToken) {
        //decrypt the refreshtoken
        //make sure it resembles the structure of a refresh token
        //** -> later check that the refresh token is in the database too.
        //** ---> futher on, if refresh token is older than 1hr, throw error.
        //if it resembles, use username from there and call create User Token
        //to receive a brand new user token

        //at this point you will realise you can call again with the older refresh token
        //it is a good idea to store refresh token in a cache or a table.
        //once it is used to generate new one you want to delete it from the table.
        //to add this extra security, insert refresh_token into database at **
        if (refreshToken.startsWith("refresh_")) {
            return new LoginResponse("access_token2", "refresh_token2", 121002);
        }
        throw new UnauthorizedException("Invalid Token");
    }
}


