package com.chenlw.java.web.utils.json;

import com.chenlw.java.web.utils.entity.UserEntity;

/**
 * @author chenlw 2019/08/09
 */
public class JacksonJsonTester {

    public static void main(String[] args) {
        try {
            testBeanToJson();
        } catch (Exception e) {
            System.out.println("异常信息：" + e.getMessage());
        }
    }

    /**
     * 测试对象转化成Json
     */
    public static void testBeanToJson() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("chenlw");
        userEntity.setPassword("chenlw");
        String userJson = JacksonJsonMapper.objectToJson(userEntity);
        System.out.println("对象转化成json：" + userJson);

    }
}
