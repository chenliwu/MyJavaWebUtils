package com.chenlw.java.web.utils.java.learning.string;

/**
 * @author chenlw 2019/08/13
 */
public class StringTest {


    public static void main(String[] args) {
        try {
            //testByteArray();
            testStringSplit();
        } catch (Exception e) {
            System.out.println("异常信息：" + e.getMessage());
        }
    }

    public static void testByteArray() throws Exception {
        String srcStr1 = "客户姓名0                                                   客户账号0                     1000.00           摘要0                         ";
        byte[] utf8ByteArr = srcStr1.getBytes("UTF-8");
        byte[] gbkByteArr = srcStr1.getBytes("GBK");
        System.out.println("UTF-8编码，字符串的字节数组长度：" + utf8ByteArr.length);
        System.out.println("GBK编码，字符串的字节数组长度：" + gbkByteArr.length);
    }


    public static void testBgkByteArrTransfer() throws Exception {
        // 定长串  60 30 18 30 = 138
        String srcStr1 = "客户姓名0                                                   客户账号0                     1000.00           摘要0                         ";
        byte[] gbkByteArr = srcStr1.getBytes("GBK");

    }

    public static void testStringSplit(){
        String srcStr1 = "客户姓名0                                                   客户账号0                     1000.00           摘要0                         ";
        String[] strArr = srcStr1.split(" ");
        System.out.println("拆分数组长度："+strArr.length);
        for(String str:strArr){
            System.out.println(str);
        }
    }


}
