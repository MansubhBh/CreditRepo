package com.creditscore.config;

import com.creditscore.interceptor.RequestInterceptor;
import com.creditscore.interceptor.TokenInterceptor;
import com.creditscore.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class WebConfiguration implements WebMvcConfigurer {


    @Autowired
    private UserTokenService userTokenService;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptor());
        registry.addInterceptor(new TokenInterceptor(userTokenService));

    }
}
