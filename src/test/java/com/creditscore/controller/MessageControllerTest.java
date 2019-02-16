package com.creditscore.controller;

import com.creditscore.interceptor.RequestInterceptor;
import com.creditscore.service.MessageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.swing.tree.ExpandVetoException;

@DisplayName("Message Controller Test")
@RunWith(JUnitPlatform.class)
public class MessageControllerTest {

    private MessageController messageController;
    private MessageService mockService;

    @BeforeEach
    public void beforeTest() {
        mockService = Mockito.mock(MessageService.class);
        messageController = new MessageController(mockService);
    }

    @Test
    public void testMessageInput() throws Exception {
        String expectedMessage = "<h1>some output</h1>";
        Mockito.when(mockService.createMessage("trt")).thenReturn("<h1>some output</h1>");
        MockMvc mvc = MockMvcBuilders.standaloneSetup(messageController)
                .addInterceptors(new RequestInterceptor())
                .build();
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/v1/test?message=trt"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String data = result.getResponse().getContentAsString();
        Assertions.assertEquals(expectedMessage, data);
    }


    @Test
    public void testWhenParamIsNotPassedItThrowsError() throws Exception {
        String expectedMessage = "<h1>some output</h1>";

        MockMvc mvc = MockMvcBuilders.standaloneSetup(messageController)
                .setControllerAdvice(new AppErrorAdvice())
                .build();
        mvc.perform(MockMvcRequestBuilders.get("/v1/test"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }


}
