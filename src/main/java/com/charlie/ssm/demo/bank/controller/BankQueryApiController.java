package com.charlie.ssm.demo.bank.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.charlie.ssm.demo.common.entity.ResultEntity;
import com.charlie.ssm.demo.entity.UserEntity;
import com.charlie.ssm.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenliwu
 * @create 2018-06-29 10:05
 **/
@Controller
@RequestMapping(value = "/api/user")
public class BankQueryApiController {

    public static Map<String, String> usernameMap = new HashMap<>();

    static {
        usernameMap.put("1", "test1");
        usernameMap.put("2", "test2");
        usernameMap.put("3", "test3");
        usernameMap.put("4", "test4");
    }

    @GetMapping("/getUsernameByToken")
    @ResponseBody
    public Object getUsernameByToken(String token) {
        if (token == null) {
            //return "admin";
            return null;
        }
        return usernameMap.get(token);
    }

}
