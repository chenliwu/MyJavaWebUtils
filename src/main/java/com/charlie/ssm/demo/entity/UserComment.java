package com.charlie.ssm.demo.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;
import java.util.List;

@TableName("user_comment")
@Data
public class UserComment {
    /**
     * @TableId(type = IdType.UUID)配置生成UUID
     */
    @TableId(type = IdType.UUID)
    private String id;

    @TableField("parent_id")
    private String parentId;

    @TableField("owner_id")
    private String ownerId;

    @TableField("commenter_id")
    private String commenterId;

    @TableField("content")
    private String content;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    /**
     * 评论列表，递归
     */
    List<UserComment> commentList;


}