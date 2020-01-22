package com.chenlw.java.web.utils.springwiki;

/**
 * 测试spring的构造器注入方式
 *
 * @author chenlw
 * @date 2020/01/22
 */
public class UserTestServiceImpl {

    private UserTestDao userTestDao;

    public UserTestDao getUserTestDao() {
        return userTestDao;
    }

    public void setUserTestDao(UserTestDao userTestDao) {
        this.userTestDao = userTestDao;
    }

    /**
     * 如果只有一个有参数的构造方法并且参数类型与注入的bean的类型匹配，那就会注入到该构造方法中。
     *
     * @param userTestDao
     */
    public UserTestServiceImpl(UserTestDao userTestDao) {
        this.userTestDao = userTestDao;
    }

    public void login() {
        System.out.println("UserTestServiceImpl.login()");
        userTestDao.login();
    }

}
