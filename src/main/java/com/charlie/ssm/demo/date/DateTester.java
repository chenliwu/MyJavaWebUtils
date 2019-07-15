package com.charlie.ssm.demo.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间日期测试
 *
 * @author chenlw
 */
public class DateTester {

    public static void main(String[]args){
        test1();
        test2();
    }

    public static void test1(){
        System.out.println("test1");
        SimpleDateFormat sf = new SimpleDateFormat("HHmmssSSS ");
        String date1 = sf.format(new Date());
        System.out.println("HHmmssSSS："+date1);
    }

    public static void test2(){
        System.out.println("test2");
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd ");
        String date1 = sf.format(new Date());
        System.out.println("yyyyMMdd："+date1);
    }


}
