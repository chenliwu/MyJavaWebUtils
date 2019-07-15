package com.charlie.ssm.demo.utils;

import java.util.Random;

/**
 * @author chenlw
 */
public class StringUtils {

    public static void main(String[]args){
        String str12len = getRandomString(12);
        String str35len = getRandomString(35);
        System.out.println("12位随机字符串："+str12len);
        System.out.println("length："+str12len.length());
        System.out.println("35位随机字符串："+str35len);
        System.out.println("length："+str35len.length());
    }

    /**
     * 生成指定长度的随机字符串
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) {

        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(3);
            long result = 0;
            switch (number) {
                case 0:
                    result = Math.round(Math.random() * 25 + 65);
                    sb.append(String.valueOf((char) result));
                    break;
                case 1:
                    result = Math.round(Math.random() * 25 + 97);
                    sb.append(String.valueOf((char) result));
                    break;
                case 2:
                    sb.append(String.valueOf(new Random().nextInt(10)));
                    break;
            }
        }
        return sb.toString();
    }

}
