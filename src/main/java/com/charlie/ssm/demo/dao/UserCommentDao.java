package com.charlie.ssm.demo.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.charlie.ssm.demo.entity.UserComment;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCommentDao extends BaseMapper<UserComment> {

}