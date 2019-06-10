package com.charlie.ssm.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
    public String openApp(ModelAndView model) {
        String timestamp = String.valueOf((new Date()).getTime());
        String appServerAddress = "http://192.168.0.178:8080/t2";

        //String openAppUrlScheme = String.format("bytter-bfs-app://index?appServerAddress=%s&token=test4", )
        return "page/openApp/openApp";
    }

    @RequestMapping(value = "/openApp1")
    public String openApp1() {
        //return "page/openApp/openApp";
        return "page/openApp/openAppTest2";
    }


    @RequestMapping(value = "/detectBrowserType")
    public String detectBrowserType() {
        return "page/openApp/detectBrowserType";
    }


}
