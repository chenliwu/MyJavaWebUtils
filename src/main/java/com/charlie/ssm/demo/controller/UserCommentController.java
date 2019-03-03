package com.charlie.ssm.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userComment")
public class UserCommentController {

    @RequestMapping(value = "/add")
    public String add() {
        return "page/pages/userComment/add";
    }


}
