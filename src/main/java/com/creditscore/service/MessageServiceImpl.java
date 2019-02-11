package com.creditscore.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service("defaultMessageService")
public class MessageServiceImpl implements MessageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Override
    public String createMessage(String input) {
        LOGGER.info("message service input = {}", input);
        if (input == null) {
            throw new IllegalArgumentException("message can't be null");
        }
        if ("".equals(input.trim())) {
            return "";
        }
        return String.format("<h1>%s</h1>", input);
    }
}
