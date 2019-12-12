package com.creditscore.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class AsyncMessageServiceImpl implements AsyncMessageService {
    private MessageService service;
    private ExecutorService executorService;
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncMessageServiceImpl.class);

    public AsyncMessageServiceImpl(MessageService messageService) {
        this.service = messageService;
        this.executorService = Executors.newFixedThreadPool(5);
    }

    @Override
    public CompletionStage<String> createCompletion(String input) {
        /**
         * start with a brand new async processing.
         * use a new threadpool
         * once the supply async block has completed then apply additional transformation
         */
        return CompletableFuture.supplyAsync(() -> input)
                .thenApplyAsync(i -> {
                    LOGGER.info("create message input {}", i);
                    return service.createMessage(i);
                }, executorService);
    }

    @Override
    public DeferredResult<String> createDeferred(String input) {
        DeferredResult<String> result = new DeferredResult<>();
        result.setResult(service.createMessage(input));
        return result;
    }
}
