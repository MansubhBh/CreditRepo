package com.creditscore.controller;


import com.creditscore.entity.Login;
import com.creditscore.entity.LoginResponse;
import com.creditscore.entity.RefreshRequest;
import com.creditscore.service.LoginService;
import com.creditscore.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private UserTokenService userTokenService;
    private LoginService loginService;

    @Autowired
    public LoginController(UserTokenService userTokenService, LoginService loginService){
        this.userTokenService = userTokenService;
        this.loginService = loginService;
    }

    /**
     * When you want to login or when you want to refresh token
     * @param login
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> createToken(@RequestBody Login login){
        boolean checklogin = loginService.checkLogin(login.getUsername(), login.getPassword());
        if (!checklogin){
            throw new RuntimeException("Invalid login");
        }
        return new ResponseEntity<LoginResponse>(userTokenService.createUserToken(login.getUsername()), HttpStatus.OK);

    }


    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refreshToken(@RequestBody RefreshRequest refreshRequest){
        return new ResponseEntity<LoginResponse>(userTokenService.refreshUserToken(refreshRequest.getRefresh_token()), HttpStatus.OK) ;
    }
}
