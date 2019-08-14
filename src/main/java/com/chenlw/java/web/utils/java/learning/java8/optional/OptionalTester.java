package com.chenlw.java.web.utils.java.learning.java8.optional;

import java.util.*;

/**
 * 描述:
 *
 * @author chenlw
 * @create 2019-05-14 9:17
 */
public class OptionalTester {

    public static void main(String[] args) {
        //test1();
        //test2();
        test3();
    }

    public static void test1() {
        List<Integer> list = Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80, 90, 100);

        //调用 stream 里的 min 或 max 函数得到最大值或最小值。
        System.out.println("\nstream()的方法来取得最大最小值");
        int max = list.stream().filter(e -> e != null).max(Comparator.naturalOrder()).orElse(null);
        System.out.println("max=" + max);
        int min = list.stream().filter(e -> e != null).min(Comparator.naturalOrder()).orElse(null);
        System.out.println("min=" + min);


        System.out.println("\n使用Collections类的方法来取得最大最小值");
        //使用Collections
        System.out.println("max=" + Collections.max(list));
        System.out.println("min=" + Collections.min(list));

    }

    public static void test2() {
        List<Long> list = Arrays.asList(new Long(100));

        //什么时候会执行orElse()呢？ 当数组为空的时候，就会执行。
        System.out.println("\n测试orElse()");
        long max = list.stream().filter(e -> e != null).max(Comparator.naturalOrder()).orElse(new Date().getTime());
        System.out.println("max=" + max);
        long min = list.stream().filter(e -> e != null).min(Comparator.naturalOrder()).orElse((long) 0);
        System.out.println("min=" + min);


    }

    public static void test3() {
        List<Integer> list = Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80, 90, 100);
        Optional<Integer> optional = list.stream()
                .filter(e -> e <= 50)
                .findFirst();
        System.out.println(optional.get());
    }


}
