package com.chenlw.java.web.utils.bank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenliwu
 * @create 2018-06-29 10:05
 **/
@Controller
@RequestMapping(value = "/api/bank")
public class BankQueryApiController {

    public static Map<String, String> usernameMap = new HashMap<>();

    static {
        usernameMap.put("1", "test1");
        usernameMap.put("2", "test2");
        usernameMap.put("3", "test3");
        usernameMap.put("4", "test4");
    }

//    @GetMapping("/getUsernameByToken")
//    @ResponseBody
//    public Object getUsernameByToken(String token) {
//        if (token == null) {
//            //return "admin";
//            return null;
//        }
//        return usernameMap.get(token);
//    }

}
