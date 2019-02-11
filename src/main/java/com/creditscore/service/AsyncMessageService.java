package com.creditscore.service;


import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletionStage;

/*
CompletionStage - java 8
CompletableFuture - java 8

ListenableFuture - guava (java 8)

DeferredResult - spring

 */
public interface AsyncMessageService {
    CompletionStage<String> createCompletion(String input);
    DeferredResult<String> createDeferred(String input);
}
