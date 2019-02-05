package com.creditscore.service;


import org.springframework.stereotype.Service;

@Service("dbMessageService")
public class DBMessageServiceImpl implements MessageService {
    @Override
    public String createMessage(String input) {
        //no operation
        throw new UnsupportedOperationException("not implemented yet");
    }
}
