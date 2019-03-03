package com.charlie.ssm.demo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.charlie.ssm.demo.common.entity.ResultEntity;
import com.charlie.ssm.demo.dao.UserInfoDao;
import com.charlie.ssm.demo.entity.UserInfoEntity;
import com.charlie.ssm.demo.service.IUserInfoService;
import com.mysql.jdbc.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenliwu
 * @create 2019-03-03 9:39
 **/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfoEntity> implements IUserInfoService {


    @Override
    public ResultEntity saveOrUpdate(UserInfoEntity userInfoEntity) {
        ResultEntity resultEntity = new ResultEntity();
        if (StringUtils.isNullOrEmpty(userInfoEntity.getId())) {
            if (insert(userInfoEntity)) {
                resultEntity.setMessage("新增用户成功");
                resultEntity.setState(200);
            } else {
                resultEntity.setMessage("新增用户失败");
            }
        } else {
            if (updateById(userInfoEntity)) {
                resultEntity.setMessage("修改用户信息成功");
                resultEntity.setState(200);
            } else {
                resultEntity.setMessage("修改用户信息失败");
            }
        }
        return resultEntity;
    }

    @Override
    public ResultEntity login(UserInfoEntity userInfoEntity) {
        ResultEntity resultEntity = new ResultEntity();
        EntityWrapper<UserInfoEntity> wrapper = new EntityWrapper<>();
        wrapper.setEntity(userInfoEntity);
        if (selectCount(wrapper) >= 1) {
            resultEntity.setMessage("登录成功");
            resultEntity.setState(200);
        } else {
            resultEntity.setMessage("账号或密码错误，登录失败");
        }
        return resultEntity;
    }


    @Override
    public List<UserInfoEntity> list(UserInfoEntity userInfoEntity) {
        EntityWrapper<UserInfoEntity> wrapper = new EntityWrapper<>();
        wrapper.setEntity(userInfoEntity);
        return selectList(wrapper);
    }
}
