package com.chenlw.java.web.utils.string;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class StringTest {


    public static void main(String[]args){

    }

    public static void test1(){
        String str = "cgenlw";
        InputStream inputStream = new ByteArrayInputStream(str.getBytes());
    }


}
