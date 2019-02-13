package com.creditscore.repository;

import com.creditscore.entity.Login;
import com.creditscore.service.LoginService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.jdbc.core.JdbcTemplate;

@RunWith(JUnitPlatform.class)
public class LoginRepositoryImplTest {

    private LoginRepository loginRepository;
    private LoginService loginService;

    @Autowired
    public LoginRepositoryImplTest(LoginRepository loginRepository){
        this.loginRepository = loginRepository;
    }


//    @Test
//    public void testIfCreateLogin(){
//        String user = loginService.checkLogin("ashik", "word");
//        Assertions.assertEquals();
//
//    }



}
