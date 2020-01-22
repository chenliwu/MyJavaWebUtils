package com.chenlw.web.utils.springwiki;

import com.chenlw.java.web.utils.springwiki.SpringTestBean;
import com.chenlw.java.web.utils.springwiki.UserTestServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author chenlw
 * @date 2020/01/22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring.xml", "classpath*:spring-mvc.xml"})
@WebAppConfiguration
public class SpringWikiTester {

    private static ApplicationContext applicationContext;


    public static void main(String[] args) {
        applicationContext = new FileSystemXmlApplicationContext("classpath:spring.xml");
        SpringTestBean springTestBean = (SpringTestBean) applicationContext.getBean("springTestBean");
        System.out.println(springTestBean.toString());
    }


    @Before
    public void initApplicationContext() {
        applicationContext = new FileSystemXmlApplicationContext("classpath:spring.xml");
    }

    /**
     * 通过Spring的ApplicationContext来获取实例对象
     */
    @Test
    public void getBeanByApplicationContext() {
        SpringTestBean springTestBean = (SpringTestBean) applicationContext.getBean("springTestBean");
        System.out.println(springTestBean.toString());
    }


    /**
     * 测试spring的构造器注入
     */
    @Test
    public void testSpringConstructorInjection() {
        // 获取Service实例
        UserTestServiceImpl userTestService = (UserTestServiceImpl) applicationContext.getBean("userTestServiceImpl");
        // 模拟业务方法
        userTestService.login();
    }




}
