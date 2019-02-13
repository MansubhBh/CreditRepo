package com.creditscore.controller;

import com.creditscore.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AppErrorAdvice {

    @ExceptionHandler({UnauthorizedException.class})
    public ResponseEntity<Map<String, Object>> unauthorised(Exception ex) {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("code", "access_denied");
        errorMap.put("message", "Unauthorised Access");
        errorMap.put("cause", ex.getMessage());
        return new ResponseEntity<>(errorMap, HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Map<String, Object>> errorHandler(Exception ex) {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("cause", ex.getMessage());
        return new ResponseEntity<>(errorMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
