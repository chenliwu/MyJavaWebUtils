package com.charlie.ssm.demo.java8.collections;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 *
 * @author chenlw
 * @create 2019-05-14 13:37
 */
public class MapTester {

    public static void main(String[]args){
        test1();
    }

    /**
     * 测试map散列结构的getOrDefault()方法
     * getOrDefault():如果map根据key查找不到value，则将返回参数中的默认值
     */
    public static void test1(){
        Map<String,String> map = new HashMap<>();
        map.put("beijing","北京");
        map.put("shanghai","上海");
        map.put("guangzhou","广州");
        map.put("shenzhen","深圳");
        System.out.println(map.getOrDefault("beijing","默认值"));
        System.out.println(map.getOrDefault("guiping","默认值"));
    }

}
