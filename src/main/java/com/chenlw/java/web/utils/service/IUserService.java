package com.chenlw.java.web.utils.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.chenlw.java.web.utils.common.entity.ResultEntity;
import com.chenlw.java.web.utils.entity.UserEntity;

/**
 *
 */
public interface IUserService{

    UserEntity getUserByUsername(String username);

    UserEntity login1(UserEntity userEntity);

    /**
     * 分页查询
     * @param userEntity
     * @param page
     * @return
     */
    Page<UserEntity> queryPage(UserEntity userEntity, Page<UserEntity> page);



    /**
     * 用户登录
     * @param userEntity
     * @return
     */
    ResultEntity login(UserEntity userEntity);

    /**
     * 用户注册
     * @param userEntity
     * @return
     */
    ResultEntity register(UserEntity userEntity);

    /**
     * 删除用户
     * @param userEntity
     * @return
     */
    ResultEntity delete(UserEntity userEntity);

}
