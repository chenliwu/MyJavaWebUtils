package com.charlie.ssm.demo.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 描述:
 *
 * @author chenlw
 * @create 2019-05-06 14:10
 */
public class TestJava8 {

    public static void main(String[] args) {

        //test1();

        testIntStramRange();

    }

    /**
     * 简单的Java示例将Strings列表转换为大写。
     */
    public static void test1() {
        List<String> alpha = Arrays.asList("a", "b", "c", "d");

        //Before Java8
        List<String> alphaUpper = new ArrayList<>();
        for (String s : alpha) {
            //调用转大写方法
            alphaUpper.add(s.toUpperCase());
        }

        System.out.println("Before Java8");
        System.out.println(alpha); //[a, b, c, d]
        System.out.println(alphaUpper); //[A, B, C, D]

        // Java 8
        System.out.println("Java8");
        //双冒号运算符就是java中的方法引用，方法引用的格式是类名：：方法名。
        List<String> collect = alpha.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(collect); //[A, B, C, D]

        // Extra, streams apply to any data type.
        List<Integer> num = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> collect1 = num.stream().map(n -> n * 2).collect(Collectors.toList());
        System.out.println(collect1); //[2, 4, 6, 8, 10]
    }

    public static void testIntStramRange(){
        IntStream.range(0, 10).forEach(i -> {
            System.out.println(i);
        });
    }


}
