package com.chenlw.java.web.utils.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenlw
 * @date 2020/03/09
 */
@RequestMapping("/hq")
@Controller
public class HuaQiangTestController {


    @GetMapping("/ssocas/oauth2.0/profile")
    @ResponseBody
    public Object sso(String access_token, Long expires_in) {
        Map<String, Object> objectMap = new HashMap<>();
        System.out.println("access_token:" + access_token);
        System.out.println("expires_in:" + expires_in);
        objectMap.put("id", "admin");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id","admin");
        jsonObject.put("loginName","admin");
        return jsonObject.toString();
    }



    /**
     * 分别传递参数
     *
     * @param userList
     * @param content
     * @param type
     * @return
     */
    @PostMapping("/universalNotification1")
    @ResponseBody
    public Map<String, Object> universalNotification1(String userList, String content, String type) {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("userList", userList);
        objectMap.put("content", content);
        objectMap.put("type", type);
        return objectMap;
    }

    /**
     * 只传一个JSON串，然后解析字段
     *
     * @param data
     * @return
     */
    @PostMapping("/universalNotification2")
    @ResponseBody
    public Map<String, Object> universalNotification2(String data) {
        Map<String, Object> objectMap = new HashMap<>();
        if (!StringUtils.isEmpty(data)) {
            JSONObject jsonObject = new JSONObject(data);
            System.out.println(jsonObject.toString());
            if (jsonObject.get("userList") != null) {
                objectMap.put("userList", jsonObject.get("userList"));
            }
            if (jsonObject.get("content") != null) {
                objectMap.put("content", jsonObject.get("content"));
            }
            if (jsonObject.get("type") != null) {
                objectMap.put("type", jsonObject.get("type"));
            }
        }
        return objectMap;
    }


    @PostMapping("/universalLinkNotification")
    @ResponseBody
    public Map<String, Object> universalLinkNotification(Map<String, Object> data) {
        Map<String, Object> objectMap = new HashMap<>();
        System.out.println(data.toString());
        return objectMap;
    }

}
