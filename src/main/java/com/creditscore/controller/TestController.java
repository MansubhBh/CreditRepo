package com.creditscore.controller;

import com.creditscore.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @Qualifier("defaultMessageService")
    @Autowired
    private MessageService messageService;

    @GetMapping(value = "/test")
    public String testmethod(@RequestParam("message") String message ){
        return messageService.createMessage(message);
    }

}
