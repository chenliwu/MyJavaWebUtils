package com.chenlw.java.web.utils.string;

public class ByteArrayTest {


    public static void main(String[] args) {
        try {
            testUTF8Bytes();
            testGBKBytes();
        } catch (Exception e) {
            System.out.println("异常信息：" + e.getMessage());
        }
    }

    public static void testUTF8Bytes() {
        // 四个中文，字节数组长度为12。在UTF-8编码情况下，一个中文占3个字节。
        String str = "举世武双";
        byte[] strBytes = str.getBytes();
        System.out.println("字节数组长度：" + strBytes.length);
        for (int i = 0; i < strBytes.length; i++) {
            System.out.println(strBytes[i]);
        }
    }

    public static void testGBKBytes() throws Exception {
        // 四个中文，字节数组长度为8。在GBK编码情况下，一个中文占2个字节。
        String str = "举世武双";
        byte[] strBytes = str.getBytes("GBK");
        System.out.println("字节数组长度：" + strBytes.length);
        for (int i = 0; i < strBytes.length; i++) {
            System.out.println(strBytes[i]);
        }
    }

}
