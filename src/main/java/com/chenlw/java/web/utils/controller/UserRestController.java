package com.chenlw.java.web.utils.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.chenlw.java.web.utils.common.entity.ResultEntity;
import com.chenlw.java.web.utils.entity.UserEntity;
import com.chenlw.java.web.utils.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
public class UserRestController {

    @Autowired
    private IUserService mIUserService;

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

    @GetMapping("/queryPage")
    @ResponseBody
    public Page<UserEntity> queryPage(UserEntity userEntity, @PageableDefault Page<UserEntity> page) {
        return mIUserService.queryPage(userEntity, page);
    }

    @GetMapping("/queryList")
    @ResponseBody
    public List<UserEntity> queryList(UserEntity userEntity, @PageableDefault Page<UserEntity> page) {
        return mIUserService.queryPage(userEntity, page).getRecords();
    }


    @RequestMapping(value = "/login1")
    @ResponseBody
    public UserEntity login1(UserEntity userEntity, Model model, HttpServletRequest request) {
        UserEntity result = mIUserService.login1(userEntity);
        if (result != null) {
            //携带数据带前段
            model.addAttribute("user", userEntity);
            System.out.println(result.toString());
            //用户登录成功，将用户信息存储到session
            request.getSession().setAttribute("user", result);
        }
        return result;
    }

    @RequestMapping(value = "/login")
    @ResponseBody
    public ResultEntity login(UserEntity userEntity, HttpServletRequest request) {
        ResultEntity resultEntity = mIUserService.login(userEntity);
        if (resultEntity.getState() == 200) {
            //用户登录成功，将用户信息存储到session
            request.getSession().setAttribute("user", resultEntity.getData());
        }
        return resultEntity;
    }

}
