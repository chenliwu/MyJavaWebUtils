package com.chenlw.ssm.test.httpclient;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring.xml","classpath*:spring-mvc.xml","classpath*:spring-mybatis.xml"})
@WebAppConfiguration
public class TestHttpClient {




}
