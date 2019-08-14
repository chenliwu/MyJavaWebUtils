package com.chenlw.java.web.utils.java.learning.string;

/**
 * @author chenlw 2019/08/13
 */
public class StringTest {


    public static void main(String[] args) {
        try {
            // testByteArray();
            // testStringSplit();
            testByteArray();
            testBgkByteArrTransfer();
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


    /**
     * 解析String定长串
     * 截取GBK字节数组
     *
     * @throws Exception
     */
    public static void testBgkByteArrTransfer() throws Exception {
        // 定长串  60 30 18 30 = 138
        String srcStr1 = "客户姓名0                                                   客户账号0                     1000.00           摘要0                         ";
        byte[] gbkByteArr = srcStr1.getBytes("GBK");
        byte[] accountNameByteArr = subByte(gbkByteArr, 0, 60);
        String accountName = new String(accountNameByteArr, "GBK");
        System.out.println(accountName + "|");
        System.out.println(accountName.trim() + "|");

        byte[] accountByteArr = subByte(gbkByteArr, 60, 30);
        String account = new String(accountByteArr, "GBK");
        System.out.println(account + "|");
        System.out.println(account.trim() + "|");

        byte[] amountByteArr = subByte(gbkByteArr, 90, 18);
        String amount = new String(amountByteArr, "GBK");
        System.out.println(amount + "|");
        System.out.println(amount.trim() + "|");

        byte[] summaryByteArr = subByte(gbkByteArr, 108, 30);
        String summary = new String(summaryByteArr, "GBK");
        System.out.println(summary + "|");
        System.out.println(summary.trim() + "|");
    }


    /**
     * 截取byte数组   不改变原数组
     *
     * @param b      原数组
     * @param off    偏差值（索引）
     * @param length 长度
     * @return 截取后的数组
     */
    public static byte[] subByte(byte[] b, int off, int length) {
        byte[] b1 = new byte[length];
        System.arraycopy(b, off, b1, 0, length);
        return b1;
    }


}
