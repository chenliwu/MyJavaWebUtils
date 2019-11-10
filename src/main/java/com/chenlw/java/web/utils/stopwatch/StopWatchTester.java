package com.chenlw.java.web.utils.stopwatch;

import org.springframework.util.StopWatch;

/**
 * StopWatch是位于org.springframework.util包下的一个工具类，通过它可方便的对程序部分代码进行计时(ms级别)，适用于同步单线程代码块。
 *
 * @author chenlw
 * @date 2019/09/30
 */
public class StopWatchTester {

    public static void main(String[] args) {

        try {
            test1();
        } catch (Exception e) {
            System.out.println("ERROR:" + e.getMessage());
        }

    }

    public static void test1() throws InterruptedException {
        StopWatch stopwatch = new StopWatch("test");
        stopwatch.start("task1");
        // do something
        Thread.sleep(100);
        stopwatch.stop();
        stopwatch.start("task2");
        // do something
        Thread.sleep(200);
        stopwatch.stop();
        System.out.println("stopwatch.prettyPrint()~~~~~~~~~~~~~~~~~");
        System.out.println(stopwatch.prettyPrint());
        System.out.println("getData() using time: " + stopwatch.getLastTaskTimeMillis());
    }

}
