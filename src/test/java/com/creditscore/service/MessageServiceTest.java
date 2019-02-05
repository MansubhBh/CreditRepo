package com.creditscore.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@DisplayName("Message Service Test")
public class MessageServiceTest {

    private MessageService service;


    @BeforeEach
    public void beforeTest() {
        service = new MessageServiceImpl();
    }

    @Test
    public void testFormatsGivenInputMessage() {
        String helloMessage = service.createMessage("hello");
        Assertions.assertEquals("<h1>hello</h1>", helloMessage);

        String messageWithSpace = service.createMessage("hello there   ");
        Assertions.assertEquals("<h1>hello there   </h1>", messageWithSpace);

    }

    @Test
    public void testThrowsErrorWhenInputIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class,
                new Executable() {
                    @Override
                    public void execute() {
                        service.createMessage(null);
                    }
                });
    }

    @Test
    public void testThrowsErrorWhenInputIsNullWithLambda() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> service.createMessage(null)
        );
    }


    @Test
    public void testDoesNothingWhenInputIsEmpty() {
        Assertions.assertEquals("", service.createMessage(""));
        Assertions.assertEquals("", service.createMessage("     "));
    }


}
