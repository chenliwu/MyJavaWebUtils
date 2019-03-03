package com.charlie.ssm.demo.controller;

import com.charlie.ssm.demo.common.entity.ResultEntity;
import com.charlie.ssm.demo.entity.UserInfoEntity;
import com.charlie.ssm.demo.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chenliwu
 * @create 2019-03-03 10:05
 **/
@RestController
@RequestMapping(value = "/api/userInfo")
public class UserInfoRestController {

    @Autowired
    private IUserInfoService userInfoService;


    @PostMapping
    public ResultEntity saveOrUpdate(UserInfoEntity userInfoEntity) {
        return userInfoService.saveOrUpdate(userInfoEntity);
    }


    @PostMapping("/login")
    public ResultEntity login(UserInfoEntity userInfoEntity) {
        return userInfoService.login(userInfoEntity);
    }


    @GetMapping("/list")
    public List list(UserInfoEntity userInfoEntity) {
        return userInfoService.list(userInfoEntity);
    }

}
