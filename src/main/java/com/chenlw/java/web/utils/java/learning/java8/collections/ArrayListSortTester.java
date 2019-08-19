package com.chenlw.java.web.utils.java.learning.java8.collections;

import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 描述:
 *
 * @author chenlw
 * @create 2019-08-14
 */
public class ArrayListSortTester {

    static List<Student> studentList = new ArrayList<>();

    static {
        studentList.add(new Student("student1", "1"));
        studentList.add(new Student("student5", "5"));
        studentList.add(new Student("student4", "4"));
        studentList.add(new Student("student3", "3"));
        studentList.add(new Student("student2", "2"));
    }


    public static void main(String[] args) {
        testListSort1();
    }

    /**
     * 测试List排序
     */
    public static void testListSort1() {
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student student1, Student student2) {
                return student1.getNo().compareTo(student2.getNo());
            }
        });
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    /**
     * 测试List排序
     */
    public static void testListSort2() {
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student student1, Student student2) {
                return student1.getNo().compareTo(student2.getNo());
            }
        });
        for (Student student : studentList) {
            System.out.println(student);
        }
    }


    @Data
    static class Student {

        private String name;
        private String no;

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
