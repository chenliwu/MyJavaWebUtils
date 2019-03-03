package com.charlie.ssm.demo.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.charlie.ssm.demo.entity.UserInfoEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoDao extends BaseMapper<UserInfoEntity> {



}