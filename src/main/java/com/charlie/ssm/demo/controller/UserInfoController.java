package com.charlie.ssm.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chenliwu
 * @create 2019-03-03 10:05
 **/
@Controller
@RequestMapping(value = "/userInfo")
public class UserInfoController {

    @RequestMapping(value = "/add")
    public String add() {
        return "page/pages/userInfo/add";
    }

    @RequestMapping(value = "/list")
    public String list() {
        return "page/pages/userInfo/list";
    }


}
