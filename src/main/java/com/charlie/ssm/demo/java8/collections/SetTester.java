package com.charlie.ssm.demo.java8.collections;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 描述:
 *
 * @author chenlw
 * @create 2019-05-13 9:02
 */
public class SetTester {

    public static void main(String[] args) {
        //forEachSet();
        set2ListUsingJava8();
    }

    /**
     * 遍历set集合
     */
    static void forEachSet() {
        Set<String> set = new HashSet<String>();
        set.add("AAA");
        set.add("BBB");
        set.add("CCC");
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String str = it.next();
            System.out.println(str);
        }
    }

    /**
     * 使用JAVA8特性将set集合转化成List
     * （1）使用filter()过滤元素：filter方法返回值为true才会保留，否则会被过滤。
     * （2）mao()：遍历集合的元素，进行自定义处理，并返回集合的数据类型。
     */
    static void set2ListUsingJava8() {
        Set<String> set = new HashSet<String>();
        set.add("AAA");
        set.add("BBB");
        set.add("CCC");
        set.add("DDD");
        List<String> list = set.stream()
                .filter(elem -> elem.equals("AAA") || elem.equals("BBB"))
                .map(elem -> {
                    return elem + "111";
                }).collect(Collectors.toList());
        list.stream().forEach(System.out::println);
    }

}
