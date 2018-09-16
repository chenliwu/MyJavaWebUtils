package com.charlie.ssm.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/param")
public class TestParamController {

    @GetMapping("/page")
    public String page(){
        return "page/testParam";
    }

    /**
     * get请求方式传递参数
     * @param paramName
     */
    @GetMapping("/testGet")
    @ResponseBody
    public String testGet(@RequestParam("paramName")String paramName, Model model){
        System.out.println("paramName:"+paramName);
        model.addAttribute("paramName",paramName);
        return paramName;
    }

    /**
     *
     * @param paramName
     * @return
     */
    @GetMapping("/testParam/{paramName}")
    @ResponseBody
    public String testParam(@PathVariable String paramName){
        System.out.println("paramName:"+paramName);
        return paramName;
    }



}
