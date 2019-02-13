package com.creditscore.repository;


import com.creditscore.entity.Login;
import com.google.common.hash.Hashing;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.swing.tree.RowMapper;
import java.nio.charset.Charset;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository("mysqlLoginRepository")
public class LoginRepositoryImpl implements LoginRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    LoginRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean checkLogin(String username, String password) {
        Map<String,Object> parammap = new HashMap<>();
        parammap.put("username", username);
        parammap.put("password", Hashing.sha256().hashString(password,Charset.defaultCharset()).toString());
        int result = jdbcTemplate.queryForObject(Queries.CHECK_LOGIN,parammap,Integer.class);
        return (result == 1) ? true: false;
    }

    @Override
    public int createLogin(String username, String password) {
        Map<String, Object> parammap = new HashMap<>();
        parammap.put("username", username);
        parammap.put("password", Hashing.sha256().hashString(password, Charset.defaultCharset()).toString());
        int result1 = jdbcTemplate.update(Queries.INSERT_LOGIN, parammap);
        return result1;

    }

    static class Queries {
        private static final String INSERT_LOGIN = "insert into creditscore.login(username,password) values(:username, :password)";

        private static final String CHECK_LOGIN = "select count(*) from creditscore.login where username=:username and password=:password";
    }
}

//
//use sha256 hasing on password. Hashing.sha256().hashString(password).toString()
//        select count(*) from creditscore.login.... where us . and pass = ...