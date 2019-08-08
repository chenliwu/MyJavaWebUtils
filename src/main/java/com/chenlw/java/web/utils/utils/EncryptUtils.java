package com.chenlw.java.web.utils.utils;

import java.math.BigInteger;
import java.util.Date;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import org.apache.commons.lang.StringUtils;


/**
 * Created by chenlw on 2019/06/10  19:11.
 * 编码工具类
 * 实现aes加密、解密
 */
public class EncryptUtils {

    /**
     * 密钥
     */
    //private static final String KEY = "abcdefgabcdefg12";
    //private static String KEY = String.valueOf((new Date()).getTime());

    /**
     * 加解密算法/工作模式/填充方式
     * 算法
     */
    //private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    public static void main(String[] args) throws Exception {
        test1();
        //test2();
    }


    public static String aesEncryptParams(String paramValue,String timestamp) throws Exception{
        final String encryptKey = getKey(timestamp);
        return aesEncrypt(paramValue,encryptKey);
    }


    /**
     *
     * @throws Exception
     */
    public static void test1() throws Exception {
        String timestamp = String.valueOf((new Date()).getTime());
        final String KEY = getKey(timestamp);
        String content = "http://192.168.0.178:8080/t2";
        System.out.println("加密前：" + content);

        System.out.println("加密密钥和解密密钥：" + KEY);

        String encrypt = aesEncrypt(content, KEY);
        System.out.println("加密后：" + encrypt);

        String decrypt = aesDecrypt(encrypt, KEY);
        System.out.println("解密后：" + decrypt);
    }


    public static void test2() throws Exception {
        String content = "http://192.168.0.178:8080/t2";
        System.out.println("Base64编码前：" + content);
        String base64 = base64Encode(content.getBytes());
        System.out.println("Base64编码后：" + base64);
        System.out.println("Base64解码：" + new String(base64Decode(base64)));
    }

    /**
     * AES加密算法的KEY为16位或者32位
     *
     * @return
     */
    private static String getKey(String timestamp) {
        // AES加密算法的KEY为16位或者32位
        int keyLength = 16;
        // 使用当前时间作为种子
        //String base = String.valueOf((new Date()).getTime());
        Random random = new Random();
        StringBuffer key = new StringBuffer();
        for (int i = 0; i < keyLength; i++) {
            int number = random.nextInt(timestamp.length());
            key.append(timestamp.charAt(number));
        }
        return key.toString();
    }

    /**
     * 将byte[]转为各种进制的字符串
     *
     * @param bytes byte[]
     * @param radix 可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
     * @return 转换后的字符串
     */
    private static String binary(byte[] bytes, int radix) {
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
    }

    /**
     * Base64编码
     *
     * @param bytes 待编码的byte[]
     * @return 编码后的base 64 code
     */
    public static String base64Encode(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    /**
     * Base64解码
     *
     * @param base64Code 待解码的base 64 code
     * @return 解码后的String
     * @throws Exception
     */
    public static byte[] base64Decode(String base64Code) throws Exception {
        //return StringUtils.isEmpty(base64Code) ? null : new BASE64Decoder().decodeBuffer(base64Code);
        return StringUtils.isEmpty(base64Code) ? null : Base64.decodeBase64(base64Code);

    }


    /**
     * AES加密
     *
     * @param content    待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的byte[]
     * @throws Exception
     */
    private static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));

        return cipher.doFinal(content.getBytes("utf-8"));
    }


    /**
     * AES加密为base 64 code
     *
     * @param content    待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的base 64 code
     * @throws Exception
     */
    private static String aesEncrypt(String content, String encryptKey) throws Exception {
        return base64Encode(aesEncryptToBytes(content, encryptKey));
    }

    /**
     * AES解密
     *
     * @param encryptBytes 待解密的byte[]
     * @param decryptKey   解密密钥
     * @return 解密后的String
     * @throws Exception
     */
    private static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);

        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);

        return new String(decryptBytes);
    }


    /**
     * 将base 64 code AES解密
     *
     * @param encryptStr 待解密的base 64 code
     * @param decryptKey 解密密钥
     * @return 解密后的string
     * @throws Exception
     */
    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
        return StringUtils.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
    }

}
