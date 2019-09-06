package com.chenlw.java.web.utils.java.learning.random;

import java.util.Random;

/**
 * @author chenlw 2019/08/13
 */
public class RandomTest {

    public static void main(String[] args) {
        test1();
        String condition = null;
        switch (condition) {
            case "1":
                System.out.println("1111");
                break;
            case "2":
                System.out.println("2222");
            default:
                System.out.println("default");
        }
    }


    public static void test1() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            System.out.println(random.nextInt(10));
        }

    }

}
