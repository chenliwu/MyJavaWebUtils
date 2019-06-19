package com.charlie.ssm.demo.controller;

import com.charlie.ssm.demo.utils.AESUtils;
import com.charlie.ssm.demo.utils.BytterBfsAppUrlSchemeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//import java.util.Base64;
import org.apache.commons.codec.binary.Base64;

import java.util.Date;

/**
 * 导航Controller
 *
 * @author: chenlw
 * @date 2018/8/5  11:13
 **/
@Controller
public class GuideController {

    @RequestMapping(value = "/testPath")
    public String testPath() {
        return "page/testPath";
    }


    @RequestMapping(value = "/viewImg")
    public String viewImg() {
        return "page/viewImg";
    }

    @RequestMapping(value = "/testDownFile")
    public String testDownFile() {
        return "page/testDownFile";
    }

    @RequestMapping(value = "/list")
    public String list() {
        return "page/pages/user/list";
    }

    @RequestMapping(value = "/index")
    public String toMainPage() {
        return "page/index";
    }

    @RequestMapping(value = "/login")
    public String toLoginPage() {
        return "page/pages/login1";
    }

    @RequestMapping(value = "/openApp")
    public String openApp(Model model) throws Exception {
        String appServerAddress = "http://192.168.0.178:8080/t2";
        String token = "test4";
        String timestamp = String.valueOf((new Date()).getTime());
        String openAppUrlScheme = BytterBfsAppUrlSchemeUtils.getOpenAppUrlSchemeByToken(appServerAddress,token,timestamp);
        model.addAttribute("openAppUrlScheme", openAppUrlScheme);
        return "page/openApp/openApp";
    }




    @RequestMapping(value = "/openApp1")
    public String openApp1(Model model) throws Exception {
//        // timestamp作为加密和解密的密匙
//        String timestamp = AESUtils.getKey(String.valueOf((new Date()).getTime()));
//        String appServerAddress = "http://192.168.0.178:8080/t2";
//
//        String encrypAppServerAddress = Base64.encodeBase64String(AESUtils.encryptAES(appServerAddress, timestamp).getBytes());
//        //String encrypAppServerAddress = AESUtils.encryptAES(appServerAddress, timestamp);
//
//        String openAppUrlScheme = String.format("bytter-bfs-app://index?appServerAddress=%s&token=test4&timestamp=%s",
//                encrypAppServerAddress, timestamp);
//        model.addAttribute("openAppUrlScheme", openAppUrlScheme);
        return "page/openApp/openApp1";
    }


    @RequestMapping(value = "/detectBrowserType")
    public String detectBrowserType() {
        return "page/openApp/detectBrowserType";
    }


}
