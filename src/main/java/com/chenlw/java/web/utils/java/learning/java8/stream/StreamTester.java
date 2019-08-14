package com.chenlw.java.web.utils.java.learning.java8.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 描述:
 *
 * @author chenlw
 * @create 2019-05-14 15:26
 */
public class StreamTester {

    public static void main(String[] args) {
        test1();
        //test2();
    }

    public static void test1() {
        ////skip(n)：跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。
        String[][] data = new String[][]{{"111", "222", "333"}, {"444", "555", "666"}, {"444", "888", "999"}, {"444", "555", "666"}};

        final int index = 1;
        //parallel()：并行计算，运行输出的结果不固定。
        String[] result = Arrays.stream(data)
                .parallel()
                .skip(1)
                .filter(e -> e != null)
                .map((e) -> {
                    //Arrays.stream(e).forEach(System.out::println);
                    return e[index];
                })
                .distinct()
                .toArray(String[]::new);

        System.out.println();
        Arrays.stream(result).forEach(e -> {
            System.out.println(e);
        });

        Set<String> resultSet = new HashSet<>();
        List<String> resultList = new ArrayList<>();
        for (int i = 1; i < data.length; i++) {
            if (data[i] != null) {
                if (!resultSet.contains(data[i][index])) {
                    resultSet.add(data[i][index]);
                    resultList.add(data[i][index]);
                }
            }
        }
        System.out.println();
        for(int i=0;i<resultList.size();i++){
            System.out.println(resultList.get(i));
        }


    }


    public static void test2() {
        List<String> list = Arrays.asList("AAA", "BBB", "CCC");
        String str = list.stream().collect(Collectors.joining(" OR ", " ", " "));
        System.out.println(str);

        String str1 = IntStream.range(0, list.size()).boxed().map(i -> list.get(i)).collect(Collectors.joining("OR", " ", " "));
        System.out.println(str1);
    }


}
