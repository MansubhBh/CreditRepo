package com.creditscore.service;

/**
 * Created by boys on 13/2/19.
 */
public interface LoginService {
    boolean checkLogin(String username, String password);
    int createLogin(String username, String password);
}
