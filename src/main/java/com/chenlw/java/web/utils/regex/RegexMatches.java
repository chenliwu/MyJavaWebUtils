package com.chenlw.java.web.utils.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chenlw
 * @date 2019/10/29
 */
public class RegexMatches {

    private static final String REGEX = "\\bcat\\b";
    private static final String INPUT = "cat cat cat cattie cat";

    public static void main(String args[]) {
        test2();
    }

    /**
     * Start 方法返回在以前的匹配操作期间，由给定组所捕获的子序列的初始索引，end 方法最后一个匹配字符的索引加 1。
     */
    public static void test1() {
        Pattern p = Pattern.compile(REGEX);
        // 获取 matcher 对象
        Matcher m = p.matcher(INPUT);
        int count = 0;

        while (m.find()) {
            count++;
            System.out.println("Match number " + count);
            System.out.println("start(): " + m.start());
            System.out.println("end(): " + m.end());
        }
    }

    public static void test2() {
        Pattern pattern = Pattern.compile(REGEX);
        // 获取 matcher 对象
        Matcher matcher = pattern.matcher(INPUT);
        if (matcher.find()) {
            System.out.println("start(): " + matcher.start());
            System.out.println("end(): " + matcher.end());
        }
    }


}
