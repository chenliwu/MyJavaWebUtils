package com.charlie.ssm.demo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.charlie.ssm.demo.common.entity.ResultEntity;
import com.charlie.ssm.demo.dao.UserCommentDao;
import com.charlie.ssm.demo.entity.UserComment;
import com.charlie.ssm.demo.service.IUserCommentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论Service
 *
 * @author chenliwu
 * @create 2019-03-03 9:39
 **/
@Service
public class UserCommentServiceImpl extends ServiceImpl<UserCommentDao, UserComment> implements IUserCommentService {

    @Override
    public ResultEntity queryComment(UserComment userComment) {
        return null;
    }

    /**
     * 查询评论列表
     *
     * @param userComment
     * @return
     */
    @Override
    public ResultEntity list(UserComment userComment) {
        EntityWrapper<UserComment> wrapper = new EntityWrapper<>();
        //先查询出一级评论列表
        wrapper.isNull("parent_id");
        List<UserComment> parentUserCommentList = selectList(wrapper);
        for (int i = 0, size = parentUserCommentList.size(); i < size; i++) {
            parentUserCommentList.get(i).setCommentList(dfs(parentUserCommentList.get(i)));
        }
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setState(200);
        resultEntity.setData(parentUserCommentList);
        return resultEntity;
    }


    /**
     * 递归查询二级评论
     *
     * @param parentUserComment
     * @return
     */
    private List<UserComment> dfs(UserComment parentUserComment) {
        if (parentUserComment == null) {
            return null;
        }
        EntityWrapper<UserComment> wrapper = new EntityWrapper<>();
        wrapper.where("parent_id = {0}", parentUserComment.getId());
        List<UserComment> childCommentList = selectList(wrapper);
        for (int i = 0, size = childCommentList.size(); i < size; i++) {
            childCommentList.get(i).setCommentList(dfs(childCommentList.get(i)));
        }
        return childCommentList;
    }

}
