package com.creditscore.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MDC.put("start_time", String.valueOf(System.currentTimeMillis()));
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long startTime = Long.parseLong(MDC.get("start_time"));
        long endTime = System.currentTimeMillis();
        int statusCode = response.getStatus();
      LOGGER.debug("uri=\"{}\", status={}, time_taken={}", request.getRequestURI(), statusCode, (endTime - startTime));
        super.afterCompletion(request, response, handler, ex);
    }
}
