package com.creditscore.service;

import com.creditscore.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private LoginRepository loginRepository;

    @Autowired
    public LoginServiceImpl(@Qualifier("mysqlLoginRepository") LoginRepository loginRepository){
        this.loginRepository = loginRepository;
    }

    @Override
    public boolean checkLogin(String username, String password) {
        return loginRepository.checkLogin(username,password);
    }

    @Override
    public int createLogin(String username, String password) {
        return loginRepository.createLogin(username,password);
    }
}
