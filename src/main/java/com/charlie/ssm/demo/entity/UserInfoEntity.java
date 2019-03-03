package com.charlie.ssm.demo.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

@TableName("user_info")
@Data
public class UserInfoEntity {

    @TableId(type = IdType.UUID)
    private String id;

    @TableField("username")
    private String username;

    @TableField("login_name")
    private String loginName;

    @TableField("password")
    private String password;


    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;


}