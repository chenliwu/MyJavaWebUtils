package com.chenlw.java.web.utils.springwiki;

import org.springframework.stereotype.Repository;

/**
 * @author chenlw
 * @date 2020/01/22
 */
@Repository
public class UserTestDao {


    public boolean login(){
        System.out.println("UserTestDao.login()");
        return true;
    }

}
