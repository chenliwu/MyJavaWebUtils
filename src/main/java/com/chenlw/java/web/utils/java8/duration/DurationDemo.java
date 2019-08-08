package com.chenlw.java.web.utils.java8.duration;

import java.time.Duration;

/**
 * 描述:
 *
 * @author chenlw
 * @create 2019-05-14 10:37
 */
public class DurationDemo {

    public static void main(String[] args) {

        Duration duration = Duration.ofMillis(2000);
        System.out.println(duration.getSeconds());
    }


}
