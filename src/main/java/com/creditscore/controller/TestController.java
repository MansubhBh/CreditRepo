package com.creditscore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TestController {
    @RequestMapping(value = "/test" , method = RequestMethod.GET)
    @ResponseBody
    public String testmethod(@RequestParam("message") String message ){
        return "Hello " + message;
    }

}
