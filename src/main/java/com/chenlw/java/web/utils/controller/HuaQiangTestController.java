package com.chenlw.java.web.utils.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenlw
 * @date 2020/03/09
 */
@RequestMapping("/hq")
@Controller
public class HuaQiangTestController {


    @PostMapping("/universalNotification")
    public Map<String, Object> universalNotification(Map<String, Object> data) {
        Map<String, Object> objectMap = new HashMap<>();
        System.out.println(data.toString());
        return objectMap;
    }


    @PostMapping("/universalLinkNotification")
    public Map<String, Object> universalLinkNotification(Map<String, Object> data) {
        Map<String, Object> objectMap = new HashMap<>();
        System.out.println(data.toString());
        return objectMap;
    }

}
