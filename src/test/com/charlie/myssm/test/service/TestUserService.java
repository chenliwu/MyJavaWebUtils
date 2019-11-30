package com.charlie.myssm.test.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

import com.chenlw.java.web.utils.common.entity.ResultEntity;
import com.chenlw.java.web.utils.entity.UserEntity;
import com.chenlw.java.web.utils.service.IUserService;
import com.chenlw.java.web.utils.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * 单元测试
 * @author chenliwu
 * @create 2018-06-29 10:51
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring.xml","classpath*:spring-mvc.xml","classpath*:spring-mybatis.xml"})
@WebAppConfiguration
public class TestUserService {

    @Autowired
    private IUserService mUserService;

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void testLogin(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("chenliwu");
        userEntity.setPassword("chenliwu");

        ResultEntity resultEntity = mUserService.login(userEntity);
        System.out.println(resultEntity.toString());
    }



    /**
     * 测试分页：使用MyBatis-Plus现有的方法
     *
     * @author: chenlw
     * @date 2018/8/6  22:49
     **/
    @Test
    public void testSelectPage(){
        Page<UserEntity> page = new Page<>(1,10);

        EntityWrapper<UserEntity> wrapper = new EntityWrapper<>();
        wrapper.orderBy("user_id",false);
        //传递page对象到selectPage里面，插件会自动添加limit语句实现分页查询
        page = userService.selectPage(page,wrapper);
        List<UserEntity> list = page.getRecords();
        System.out.println("page:"+page.toString());
        for(UserEntity entity:list){
            System.out.println(entity.toString());
        }
    }




    /********************  使用MyBatis-Plus条件构造器 *******************************/

    @Test
    public void testSelect(){
        EntityWrapper<UserEntity> wrapper = new EntityWrapper<>();
        //按照user_id字段排序，降序排列DESC
        wrapper.orderBy("user_id",false);
        List<UserEntity> list = userService.selectList(wrapper);
        System.out.println("size = "+list.size());
        for(UserEntity entity:list){
            System.out.println(entity.toString());
        }
    }

    /**
     * 通过实体类动态添加where查询语句
     *  SELECT
     user_id AS userId,
     username,
     `password`
     FROM
     tb_user
     WHERE
     username='chenlw'
     AND `password`='chenlw'
     */
    @Test
    public void testLogin1(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("chenlw");
        userEntity.setPassword("chenlw");
        EntityWrapper<UserEntity> wrapper = new EntityWrapper<>(userEntity);
        List<UserEntity> list = userService.selectList(wrapper);
        System.out.println("size = "+list.size());
        for(UserEntity entity:list){
            System.out.println(entity.toString());
        }
    }


    @Test
    public void testLogin2(){
        EntityWrapper<UserEntity> wrapper = new EntityWrapper<>();
        Object[] param = new Object[]{"clw2","clw3","clw4"};
        wrapper.in("username",param);
        List<UserEntity> list = userService.selectList(wrapper);
        System.out.println("size = "+list.size());
        for(UserEntity entity:list){
            System.out.println(entity.toString());
        }
    }



    /**
     * 模糊查询
     */
    @Test
    public void testLikeQuery(){
        EntityWrapper<UserEntity> wrapper = new EntityWrapper<>();
        wrapper.like("username","clw");
        List<UserEntity> list = userService.selectList(wrapper);
        System.out.println("size = "+list.size());
        for(UserEntity entity:list){
            System.out.println(entity.toString());
        }
    }



}
