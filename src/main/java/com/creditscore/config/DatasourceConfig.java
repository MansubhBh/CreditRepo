package com.creditscore.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Configuration
@Component
public class DatasourceConfig {


    //this is not required if datasource was used.
    @Autowired
    @Bean
    public DataSource dataSource(DatabaseProperties properties) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setUsername(properties.getMysql().getUsername());
        hikariConfig.setPassword(properties.getMysql().getPassword());
        hikariConfig.setJdbcUrl(properties.getMysql().getUrl());
        hikariConfig.setDriverClassName(properties.getMysql().getDriver());
        DataSource dataSource = new HikariDataSource(hikariConfig);
        return dataSource;
    }


    //select * from login where username = :username and password = :password
    //setParameter("username", request.name)

    //if you were not using NamedParameterTemplate you would have something like this
    //select * from login where username = ? and password = ?

    @Autowired
    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

}
