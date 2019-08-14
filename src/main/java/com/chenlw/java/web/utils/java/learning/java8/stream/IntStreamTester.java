package com.chenlw.java.web.utils.java.learning.java8.stream;

import java.util.stream.IntStream;

/**
 * 描述:
 *
 * @author chenlw
 * @create 2019-05-14 10:11
 */
public class IntStreamTester {

    public static void main(String[] arg) {
        test1();
    }

    public static void test1(){
        IntStream.range(0, 5).forEach(System.out::println);
    }
}
