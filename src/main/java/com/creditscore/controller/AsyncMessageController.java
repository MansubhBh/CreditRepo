package com.creditscore.controller;

import com.creditscore.service.AsyncMessageService;
import com.creditscore.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@RestController
@RequestMapping("/v2")
public class AsyncMessageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncMessageController.class);

    private AsyncMessageService messageService;

    @Autowired
    AsyncMessageController(AsyncMessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping(value = "/test")
    public CompletionStage<String> withCompletionStage(@RequestParam("message") String message) {
        return messageService.createCompletion(message);
    }


    @GetMapping(value = "/test2")
    public DeferredResult<String> testmethod(@RequestParam("message") String message) {
        return messageService.createDeferred(message);
    }

    @GetMapping(value = "/timedout")
    public CompletionStage<String> getMeSomething() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        //this timesout because we are not calling complete or completeExceptionally
        return completableFuture;
    }

    /**
    the result future is run in fork join pool as it is async and we are not providing our own pool.
    the logger.info in the main method just before returning the result is the one that's printed first.
    the lambda for supplyAsync will be scheduled at a later time.
    spring allows you to pass @CompletionStage, @CompletableFuture, ListenableFuture or DeferredResult
     */
    @GetMapping(value = "/random")
    public CompletionStage<String> getRandom() {
        CompletionStage<String> result = CompletableFuture.supplyAsync(() -> {
            long sleep = (long) (Math.random() * 5000);
            LOGGER.info("sleeping {} ms", sleep);
            try {
                Thread.sleep(sleep);
            } catch (Exception e) {
                Thread.interrupted();
            }
            LOGGER.info("slept {} ms", sleep);
            return String.valueOf(sleep);
        });

        LOGGER.info("returning random handle");
        return result;
    }
}

