package com.chenlw.java.web.utils.java8study201907;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;

/**
 * Java8 Stream流运用
 *
 * @author chenlw 2019/07/28
 */
public class StreamTester {

    public static List<String> dataList = new ArrayList<>();

    static {
        dataList.add("data1");
        dataList.add("data2");
        dataList.add("data3");
        dataList.add("data4");
        dataList.add("data5");
        dataList.add("data6");
        dataList.add("data7");
        dataList.add("data8");
        dataList.add("data9");
    }


    public static void main(String[] args) {
        forEachList();
        // listCount();

    }

    /**
     * 遍历List
     */
    public static void forEachList() {
        System.out.println("\n遍历List");
//        dataList.stream().forEach((item)->{
//            System.out.println(item);
//        });

        // stream流的map方法，要返回一个参数，然后可以继续forEach
//        dataList.stream().map((item) -> {
//            item = item + "map";
//            return item;
//        }).forEach((item) -> {
//            System.out.println(item);
//        });


        dataList.stream().limit(5).forEach((item)->{
            System.out.println(item);
        });

    }

    /**
     *
     */
    public static void listCount() {
        System.out.println("\n统计集合列表的数据");
        // 统计集合列表的数据
        List<Integer> numbers = Arrays.asList(10,20,30,40,50);

        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());

    }


}
