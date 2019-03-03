package com.charlie.ssm.demo.controller;

import com.charlie.ssm.demo.common.entity.ResultEntity;
import com.charlie.ssm.demo.entity.UserComment;
import com.charlie.ssm.demo.service.impl.UserCommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/userComment")
public class UserCommentRestController {

    @Autowired
    private UserCommentServiceImpl userCommentService;


    @PostMapping("/add")
    public ResultEntity add(UserComment userComment) {
        ResultEntity resultEntity = new ResultEntity();
        if (userCommentService.insert(userComment)) {
            resultEntity.setState(200);
            resultEntity.setMessage("评论成功");
        } else {
            resultEntity.setMessage("评论失败");
        }
        return resultEntity;
    }

    @GetMapping("/list")
    public ResultEntity list(UserComment userComment) {
        return userCommentService.list(userComment);
    }


}
