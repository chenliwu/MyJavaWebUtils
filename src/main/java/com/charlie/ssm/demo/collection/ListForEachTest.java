package com.charlie.ssm.demo.collection;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenlw 2019/08/02
 */
public class ListForEachTest {

    public static void main(String[] args) {

        testListForEach();
    }

    @Data
    @Accessors(chain = true)
    public static class Student {
        private String sno;

        private String name;

        public Student(){}

        public Student(String sno, String name) {
            this.sno = sno;
            this.name = name;
        }
    }

    /**
     * 测试for循环遍历List时候，更改对象的属性，List里面的数据是否会同步更改
     */
    public static void testListForEach() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student());
        studentList.add(new Student());
        studentList.add(new Student());
        int index = 0;
        for(Student student:studentList){
            student.setSno("学号"+index);
            student.setName("姓名"+index);
            index++;
        }
        studentList.stream().forEach((item)->{
            System.out.println(item.toString());
        });
    }


}
