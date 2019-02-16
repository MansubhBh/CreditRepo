package com.creditscore.controller;


import com.creditscore.entity.CreditCardDetail;
import com.creditscore.entity.ProductDetail;
import com.creditscore.interceptor.RequestInterceptor;
import com.creditscore.repository.CreditCardRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;
import java.util.List;

@DisplayName("Credit card controller Test")
@RunWith(JUnitPlatform.class)
public class CreditCardControllerTest {

    private CreditCardController creditCardController;
    private CreditCardRepository mockRepository;


    @BeforeEach
    public void beforeTest(){
        mockRepository = Mockito.mock(CreditCardRepository.class);
        creditCardController = new CreditCardController(mockRepository);
    }

    @Test
    public void testAddCreditDetials() throws Exception{
        CreditCardDetail cardDetail = new CreditCardDetail("Bank of Western Australia", 10.14, "Visa Card", 10000, 1000, new ProductDetail("Holiday Offer", 1500, "Macbook"));
        Mockito.when(mockRepository.createCreditCard(cardDetail)).thenReturn(cardDetail);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(creditCardController)
                .addInterceptors(new RequestInterceptor())
                .build();
        ObjectMapper mapper = new ObjectMapper();
        String cardData = mapper.writeValueAsString(cardDetail);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/addCreditCard")
                .contentType(MediaType.APPLICATION_JSON)
                .content(cardData.getBytes(Charset.defaultCharset())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

    }

    @Test
    public void testListAllCardDetails() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(creditCardController)
                .addInterceptors(new RequestInterceptor())
                .build();
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/listAll"))
                .andExpect(MockMvcResultMatchers.status().isOk())
            //    .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(5)))
                .andReturn();

    }

    @Test
    public void testSearchCardDetailsWithBank() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(creditCardController)
                .addInterceptors(new RequestInterceptor())
                .build();
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/searchCreditCard?bank=cba"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

    }


    @Test
    public void testSearchCardDetailsWithBankAndType() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(creditCardController)
                .addInterceptors(new RequestInterceptor())
                .build();
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/searchCreditCard?bank=cba&type=visa"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

    }

    @Test
    public void testSearchWhenParamIsNotPassed() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(creditCardController)
                .setControllerAdvice(new AppErrorAdvice())
                .build();
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/searchCreditCard"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

    }

}
