package com.chenlw.java.web.utils.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/params")
public class TestParamController {

    @RequestMapping("/index")
    public String testParam1(){
        return "page/testParam";
    }

}
