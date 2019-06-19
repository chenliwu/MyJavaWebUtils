package com.charlie.ssm.demo.utils;

import cn.hutool.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

/**
 * Created by chenlw on 2019/06/11  9:55.
 */
public class AESUtils {

//
//    private static final String IV_STRING = "sdf4ddfsFD86Vdf2";
//    private static final String encoding = "UTF-8";
//
//    public static String encryptAES(String content, String key)
//            throws InvalidKeyException, NoSuchAlgorithmException,
//            NoSuchPaddingException, UnsupportedEncodingException,
//            InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
//        byte[] byteContent = content.getBytes(encoding);
//        // 注意，为了能与 iOS 统一
//        // 这里的 key 不可以使用 KeyGenerator、SecureRandom、SecretKey 生成
//        byte[] enCodeFormat = key.getBytes(encoding);
//        SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
//        byte[] initParam = IV_STRING.getBytes(encoding);
//        IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
//        // 指定加密的算法、工作模式和填充方式
//        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
//        byte[] encryptedBytes = cipher.doFinal(byteContent);
//        // 同样对加密后数据进行 base64 编码
//        String base64 = new Base64().encodeToString(encryptedBytes);
//        //进行url编码 去掉= ? &
//        return URLEncoder.encode(base64, encoding);
//    }
//
//    public static String decryptAES(String content, String key)
//            throws InvalidKeyException, NoSuchAlgorithmException,
//            NoSuchPaddingException, InvalidAlgorithmParameterException,
//            IllegalBlockSizeException, BadPaddingException, IOException {
//        //URL解码
//        content = URLDecoder.decode(content, encoding);
//        // base64 解码
//        byte[] encryptedBytes = Base64.decodeBase64(content);
//        byte[] enCodeFormat = key.getBytes(encoding);
//        SecretKeySpec secretKey = new SecretKeySpec(enCodeFormat, "AES");
//        byte[] initParam = IV_STRING.getBytes(encoding);
//        IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
//        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
//        byte[] result = cipher.doFinal(encryptedBytes);
//        return new String(result, encoding);
//    }
//
//    /**
//     * AES加密算法的KEY为16位或者32位
//     *
//     * @return
//     */
//    public static String getKey(String timestamp) {
//        // AES加密算法的KEY为16位或者32位
//        int keyLength = 16;
//        // 使用当前时间作为种子
//        //String base = String.valueOf((new Date()).getTime());
//        Random random = new Random();
//        StringBuffer key = new StringBuffer();
//        for (int i = 0; i < keyLength; i++) {
//            int number = random.nextInt(timestamp.length());
//            key.append(timestamp.charAt(number));
//        }
//        return key.toString();
//    }
//
//    public static void main(String[] args) throws Exception {
//        //String key = "djadiKJdj49dFJLd";
//        String timestamp =String.valueOf((new Date()).getTime());
//        String key = getKey(timestamp);
//        String content = "http://192.168.0.178:8080/t2";
//        System.out.println("加密前：" + content);
//
//        System.out.println("加密密钥和解密密钥：" + key);
//        String encrypt = encryptAES(content, key);
//        System.out.println("加密后：" + encrypt);
//        String decrypt = decryptAES(encrypt, key);
//        System.out.println("解密后：" + decrypt);
//    }


    private static final Logger logger = Logger.getLogger(AESUtils.class);
    private static final String CHARSET_UTF8 = "UTF-8";
    private static final String KEY_AES = "AES";

    /**
     * 签名算法
     */
    public static final String SIGN_ALGORITHMS = "SHA1PRNG";

    //private static final String KEY = "123456";


    public static void main(String[] args) throws Exception {
        final String KEY = "123456";
        String content = "chenlw";
        System.out.println("加密前：" + content);
        System.out.println("加密密钥和解密密钥：" + KEY);
        String encrypt = encrypt(content, KEY);
        System.out.println("加密后：" + encrypt);
        String decrypt = decrypt(encrypt, KEY);
        System.out.println("解密后：" + decrypt);
    }

    /**
     * 加密
     *
     * @param data 需要加密的内容
     * @param key  加密密码
     * @return
     */
    public static String encrypt(String data, String key) {
        return doAES(data, key, Cipher.ENCRYPT_MODE);
    }

    /**
     * 解密
     *
     * @param data 待解密内容
     * @param key  解密密钥
     * @return
     */
    public static String decrypt(String data, String key) {
        return doAES(data, key, Cipher.DECRYPT_MODE);
    }

    /**
     * 加解密
     *
     * @param data 待处理数据
     * @param key  密钥
     * @param mode 加解密mode
     * @return
     */
    private static String doAES(String data, String key, int mode) {
        try {
            if (StringUtils.isBlank(data) || StringUtils.isBlank(key)) {
                return null;
            }
            //判断是加密还是解密
            boolean encrypt = mode == Cipher.ENCRYPT_MODE;
            byte[] content;
            //true 加密内容 false 解密内容
            if (encrypt) {
                content = data.getBytes(CHARSET_UTF8);
            } else {
                content = parseHexStr2Byte(data);
            }
            //1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator kgen = KeyGenerator.getInstance(KEY_AES);
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组,防止linux下 随机生成key
            SecureRandom random = SecureRandom.getInstance(SIGN_ALGORITHMS);
            random.setSeed(key.getBytes(CHARSET_UTF8));
            kgen.init(128, random);

            //kgen.init(128, new SecureRandom(key.getBytes()));
            //3.产生原始对称密钥
            SecretKey secretKey = kgen.generateKey();
            //4.获得原始对称密钥的字节数组
            byte[] enCodeFormat = secretKey.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat, KEY_AES);
            //6.根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance(KEY_AES);// 创建密码器
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(mode, keySpec);// 初始化
            byte[] result = cipher.doFinal(content);
            if (encrypt) {
                //将二进制转换成16进制
                return parseByte2HexStr(result);
            } else {
                return new String(result, CHARSET_UTF8);
            }
        } catch (Exception e) {
            logger.error("AES 密文处理异常", e);
        }
        return null;
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }


}
