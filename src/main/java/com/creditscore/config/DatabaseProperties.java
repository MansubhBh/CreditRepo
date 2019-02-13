package com.creditscore.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix="database")
public class DatabaseProperties {

    private MySqlProperties mysql;

    public void setMysql(MySqlProperties mysql) {
        this.mysql = mysql;
    }

    public MySqlProperties getMysql() {
        return mysql;
    }

    static class MySqlProperties {
        private String username;
        private String password;
        private String url;
        private String driver;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDriver() {
            return driver;
        }

        public void setDriver(String driver) {
            this.driver = driver;
        }
    }

}
