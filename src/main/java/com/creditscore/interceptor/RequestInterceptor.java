package com.creditscore.interceptor;

import org.slf4j.MDC;
import org.springframework.web.reactive.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Handler;

public class RequestInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MDC.put("start_time", String.valueOf(System.currentTimeMillis()));
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long startTime = Long.parseLong(MDC.get("start_time"));
        long endTime = System.currentTimeMillis();
        int statusCode = response.getStatus();
        System.out.println("uri=\"" + request.getRequestURI() + "\", status=" + statusCode + ", time_taken=" + (endTime - startTime));

        super.afterCompletion(request, response, handler, ex);
    }
}
