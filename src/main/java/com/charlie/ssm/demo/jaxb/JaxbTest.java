package com.charlie.ssm.demo.jaxb;

import com.charlie.ssm.demo.jaxb.entity.Book;

import java.util.Date;

/**
 * Created by chenlw on 2019/07/09  18:36.
 */
public class JaxbTest {

    public static void main(String[]args){
        test1();
    }

    public static void test1(){
        Book book = new Book();
        book.setId(100);
        book.setAuthor("James");
        book.setCalendar(new Date());
        book.setPrice(23.45f);   //默认是0.0

        String str = JaxbUtil.convertToXml(book,"GBK");
        System.out.println(str);
    }

}
