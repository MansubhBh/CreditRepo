package com.creditscore.repository;

public interface LoginRepository {

    boolean checkLogin(String username, String password);
    int createLogin(String username, String password);
}
