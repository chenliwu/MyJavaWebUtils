package com.charlie.ssm.demo.service;

import com.charlie.ssm.demo.common.entity.ResultEntity;
import com.charlie.ssm.demo.entity.UserComment;
import com.charlie.ssm.demo.entity.UserInfoEntity;

import java.util.List;

/**
 *
 */
public interface IUserCommentService {

    ResultEntity queryComment(UserComment userComment);

    ResultEntity list(UserComment userComment);

}
