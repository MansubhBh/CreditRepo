package com.creditscore.service;


import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service("defaultMessageService")
public class MessageServiceImpl implements MessageService {
    @Override
    public String createMessage(String input) {
        if (input == null) {
            throw new IllegalArgumentException("message can't be null");
        }
        if ("".equals(input.trim())) {
            return "";
        }
        return String.format("<h1>%s</h1>", input);
    }
}
