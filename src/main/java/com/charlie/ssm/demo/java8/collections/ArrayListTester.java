package com.charlie.ssm.demo.java8.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 描述:
 *
 * @author chenlw
 * @create 2019-05-10 16:08
 */
public class ArrayListTester {

    public static void main(String[] args) {
        //testAnyMatch();
        testAllMatch();
        // testFilter();
        //test1();
    }

    /**
     * 测试anyMatch
     */
    public static void testAnyMatch() {
        List<String> list = Arrays.asList("AAA", "BBB", "CCC");
        //遍历列表，只有一个item返回true，anyMatch就返回true
        boolean result = list.stream().anyMatch(str -> "CCC".equals(str));
        System.out.println(result);
    }

    public static void testAllMatch() {
        List<String> list = Arrays.asList("AAA", "AAA", "AAA");
        //遍历列表，所有item返回true，allMatch才返回true
        boolean result = list.stream().allMatch(str -> "AAA".equals(str));
        System.out.println(result);
    }

    /**
     * 测试filter
     * filter()方法返回true的元素，才会保留。否则就会被过滤。
     */
    public static void testFilter() {
        List<String> list = Arrays.asList("AAA", "BBB", "CCC", "DDD");
        list.stream().filter(str -> "AAA".equals(str) || "BBB".equals(str)).forEach(System.out::println);
    }

    /**
     * 测试引用传递
     */
    public static void test1() {
        List<Student> studentList = new ArrayList<>();
        List<Student> studentListTemp = new ArrayList<>();
        studentList.add(new Student("AAA", "1"));
        studentList.add(new Student("BBB", "2"));

        studentListTemp.addAll(studentList);
        studentListTemp.stream()
                .map(student -> {
                    student.name = student.name + " map";
                    return student;
                })
                .forEach(System.out::println);

//        studentListTemp.addAll(studentList);
//        for (Student student : studentListTemp) {
//            student.name = student.name + " map";
//        }


        System.out.println("\n");
        studentList.stream().forEach(System.out::println);
    }

    static class Student {

        public String name;
        public String no;

        public Student(String name, String no) {
            this.name = name;
            this.no = no;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", no='" + no + '\'' +
                    '}';
        }
    }


}
