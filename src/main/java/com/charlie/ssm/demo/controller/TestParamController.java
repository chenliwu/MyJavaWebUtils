package com.charlie.ssm.demo.controller;

import com.charlie.ssm.demo.utils.NetworkUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/param")
public class TestParamController {

    @GetMapping("/page")
    public String page(){
        return "page/testParam";
    }


    @GetMapping("/testRequestIp")
    @ResponseBody
    public String testRequestIp(HttpServletRequest request){
        StringBuilder sb = new StringBuilder();
//        sb.append("getRemoteHost:"+request.getRemoteHost());  //发出请求的客户机的主机名
//        sb.append("\n");
//        sb.append("getLocalAddr:"+request.getLocalAddr());  //发出请求的IP地址
//        System.out.println(sb.toString());
        try {
            sb.append(NetworkUtils.getIpAddress(request));
            System.out.println(sb.toString());
        }catch (Exception e){
            System.out.println("异常："+e.getMessage());
        }
        return sb.toString();
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
