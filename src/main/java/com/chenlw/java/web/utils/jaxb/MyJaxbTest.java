package com.chenlw.java.web.utils.jaxb;

import com.chenlw.java.web.utils.jaxb.entity.Book;
import com.chenlw.java.web.utils.jaxb.entity.Role;
import com.chenlw.java.web.utils.jaxb.entity.Student;

import java.util.Date;

/**
 * Created by chenlw on 2019/07/09  18:36.
 */
public class MyJaxbTest {

    public static void main(String[]args){
        //test1();
        test2();
    }

    public static void test1(){
        Book book = new Book();
        book.setId(100);
        book.setAuthor("James");
        book.setCalendar(new Date());
        book.setPrice(23.45f);   //默认是0.0

        String str = MyJaxbUtil.convertToXml(book,"GBK");
        System.out.println(str);
    }

    /**
     * 类中包含复杂对象的转换
     */
    public static void test2(){
        Student student = new Student();
        student.setId(12);
        student.setName("test");

        Role role = new Role();
        role.setDesc("管理");
        role.setName("班长");

        student.setRole(role);

        String str = MyJaxbUtil.convertToXml(student);
        System.out.println(str);
    }


    public static void test3(){

    }


}
