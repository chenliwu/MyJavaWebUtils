package com.chenlw.java.web.utils.random;

import java.util.Random;

/**
 * @author chenlw 2019/08/13
 */
public class RandomTest {

    public static void main(String[] args) {
        test1();

    }


    public static void test1() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            System.out.println(random.nextInt(10));
        }

    }

}
