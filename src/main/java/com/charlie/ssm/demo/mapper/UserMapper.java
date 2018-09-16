package com.charlie.ssm.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.charlie.ssm.demo.entity.UserEntity;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<UserEntity> {

    /**
     * 根据参数动态查询记录
     * @param userEntity
     * @return
     */
    List<UserEntity> selectRecordsByParameter(UserEntity userEntity);


    /**
     * Select注解可在里面书写SQL语句，参数可用#{param},${param}方式写入
     * @param userEntity
     * @return
     */
    @Select("SELECT user_id,username,password FROM tb_user WHERE username = #{username} AND password = #{password}")
    List<UserEntity> selectListByParameter(UserEntity userEntity);

}