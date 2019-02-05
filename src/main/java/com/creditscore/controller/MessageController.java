package com.creditscore.controller;

import com.creditscore.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {

    private final MessageService messageService;

    @Autowired
    MessageController(@Qualifier("defaultMessageService")
                      MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping(value = "/test")
    public String testmethod(@RequestParam("message") String message) {
        return messageService.createMessage(message);
    }

}
