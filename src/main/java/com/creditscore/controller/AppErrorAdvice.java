package com.creditscore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AppErrorAdvice {

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Map<String, Object>> errorHandler(Exception ex) {
        Map errorMap = new HashMap<>();
        errorMap.put("cause", ex.getMessage());
        return new ResponseEntity<Map<String, Object>>(errorMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
