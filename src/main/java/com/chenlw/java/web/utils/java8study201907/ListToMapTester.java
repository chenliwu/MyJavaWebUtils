package com.chenlw.java.web.utils.java8study201907;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author chenlw 2019/07/29
 */
public class ListToMapTester {

    public static void main(String[] args) {

        // listToMap();
        // listToMap1();
        listToMapAndRevise();
    }


    /**
     * List -> Map
     * 需要注意的是：
     * toMap 如果集合对象有重复的key，会报错Duplicate key ....
     * apple1,apple12的id都为1。
     * 可以用 (k1,k2)->k1 来设置，如果有重复的key,则保留key1,舍弃key2
     */
    public static void listToMap() {
        System.out.println("list转map测试1");
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("001", "001"));
        studentList.add(new Student("002", "002"));
        studentList.add(new Student("003", "003"));
        studentList.add(new Student("004", "004"));
        studentList.add(new Student("005", "005"));

        Map<String, Student> studentMap = studentList.stream().collect(Collectors.toMap(Student::getSno, a -> a, (k1, k2) -> k1));
        studentMap.forEach((key, value) -> {
            System.out.println("key:" + key + " ---- " + "value:" + value);
        });
    }

    /**
     * 重复key的情况
     * 在list转为map时，作为key的值有可能重复，这时候流的处理会抛出个异常：Java.lang.IllegalStateException:Duplicate key。
     * 这时候就要在toMap方法中指定当key冲突时key的选择。(这里是选择第二个key覆盖第一个key)
     */
    public static void listToMap1() {
        System.out.println("list转map测试2");
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("001", "001"));
        studentList.add(new Student("001", "002"));
        studentList.add(new Student("003", "003"));
        Map<String, Student> studentMap = studentList.stream().collect(Collectors.toMap(Student::getSno, Function.identity(), (key1, key2) -> key2));
        studentMap.forEach((key, value) -> {
            System.out.println("key:" + key + " ---- " + "value:" + value);
        });
    }

    /**
     * 测试引用对象的修改
     */
    public static void listToMapAndRevise(){
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("001", "001"));
        studentList.add(new Student("002", "002"));
        studentList.add(new Student("003", "003"));
        Map<String, Student> studentMap = studentList.stream().collect(Collectors.toMap(Student::getSno, Function.identity(), (key1, key2) -> key2));
        Student student = studentMap.get("001");
        student.setName("1212313");
        studentList.stream().forEach((item)->{
            System.out.println(item);
        });
    }


    public static class Student {

        /**
         * 学号
         */
        private String sno;

        /**
         * 姓名
         */
        private String name;

        public Student(String sno, String name) {
            this.sno = name;
            this.name = name;
        }


        public String getSno() {
            return sno;
        }

        public void setSno(String sno) {
            this.sno = sno;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "sno='" + sno + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

}
