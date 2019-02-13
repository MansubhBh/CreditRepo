package com.creditscore.interceptor;

import com.creditscore.exception.UnauthorizedException;
import com.creditscore.service.UserTokenService;
import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TokenInterceptor extends HandlerInterceptorAdapter {

    private final UserTokenService userTokenService;

    @Autowired
    public TokenInterceptor(UserTokenService userTokenService) {
        this.userTokenService = userTokenService;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenInterceptor.class);
    private final Pattern AUTH_HEADER_PATTERN = Pattern.compile("Bearer (.*)");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MDC.put("start_time", String.valueOf(System.currentTimeMillis()));
        String uri = request.getRequestURI();

        //this is protected if this url is called, make sure there is token
        if (uri.contains("/addCreditCard")) {
            String authHeader = request.getHeader("authorization");
            Matcher authPatternMatcher = AUTH_HEADER_PATTERN.matcher(authHeader);

            if (authPatternMatcher.matches()) {
                String accessToken = authPatternMatcher.group(1);
                if (!userTokenService.checkUserToken(accessToken)) {
                    throw new UnauthorizedException("Invalid Access Token");
                }
            } else {
                throw new UnauthorizedException("Invalid Access Token");
            }
        }
        //otherwise everything else is allowed.
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long startTime = Long.parseLong(org.slf4j.MDC.get("start_time"));
        long endTime = System.currentTimeMillis();
        int statusCode = response.getStatus();
        String query = request.getQueryString();
        LOGGER.debug("uri=\"{}\", query=\"{}\", status={}, time_taken={}", request.getRequestURI(), query, statusCode, (endTime - startTime));
        super.afterCompletion(request, response, handler, ex);
    }
}


//Add a interceptor which checks the URI and if the uri is "/token" then don't do anything
//        The TokenService must be injected to Interceptor too.
//        In the interceptor, if the url endsWith /addCreditCard then make sure you call a
// service that is UserTokenService which validates
//        the existence of the Header -> "authorization" whose value is "Bearer <whatever the token is>"
//
//

