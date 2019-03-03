package com.charlie.ssm.demo.service;

import com.charlie.ssm.demo.common.entity.ResultEntity;
import com.charlie.ssm.demo.entity.UserInfoEntity;

import java.util.List;

/**
 *
 */
public interface IUserInfoService {


    ResultEntity saveOrUpdate(UserInfoEntity userInfoEntity);

    /**
     * 用户登录
     *
     * @param userInfoEntity
     * @return
     */
    ResultEntity login(UserInfoEntity userInfoEntity);


    List<UserInfoEntity> list(UserInfoEntity userInfoEntity);

}
