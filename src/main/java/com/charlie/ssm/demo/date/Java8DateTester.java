package com.charlie.ssm.demo.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Java8 日期时间API运用
 * @author chenlw
 */
public class Java8DateTester {

    public static void main(String args[]) {
        test1();
        test2();
    }

    public static void test1() {
        // 根据需要解析的日期、时间字符串定义解析所用的格式器
        DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");
        // 获取当前时间
        LocalDateTime nowDateTime = LocalDateTime.now();
        // 执行解析
        String dateString = nowDateTime.format(yyyyMMdd);
        System.out.println("dateString.yyyyMMdd:" + dateString);
    }


    public static void test2() {
        // 根据需要解析的日期、时间字符串定义解析所用的格式器
        DateTimeFormatter HHmmssSSSSSS = DateTimeFormatter.ofPattern("HHmmssSSSSSS");
        // 获取当前时间
        LocalDateTime nowDateTime = LocalDateTime.now();
        // 执行解析
        String dateString = nowDateTime.format(HHmmssSSSSSS);
        System.out.println("dateString.HHmmssSSSSSS:" + dateString);
    }


}
