package com.chenlw.java.web.utils.utils;

import org.apache.commons.codec.binary.Base64;

import java.util.Date;

/**
 * Created by chenlw on 2019/06/11
 */
public class BytterBfsAppUrlSchemeUtils {

    /**
     * 资金管理APP单点登录的URL Scheme（该字段的值不能修改）
     */
    private static final String appSSOUrlScheme = "bytter-bfs-app://index/sso";


    public static void main(String[] args) {
        // 这些参数值用于测试，需要根据情况修改
        String appServerAddress = "http://192.168.0.178:8080/t2";
        String token = "test4";
        String username = "test4";

        String timestamp = String.valueOf((new Date()).getTime());

        String openAppURLScheme1 = getOpenAppUrlSchemeByToken(appServerAddress, token, timestamp);
        System.out.println("未携带登录名的URL Scheme（参数加密）：" + openAppURLScheme1);
        String openAppURLScheme2 = getOpenAppUrlSchemeByUsername(appServerAddress, username, timestamp);
        System.out.println("携带了登录名的URL Scheme（参数加密）：" + openAppURLScheme2);
    }


    /**
     * 获取通过Token实现单点登录的URL Scheme
     *
     * @param appServerAddress 资金管理系统访问地址
     * @param token            身份令牌（用于获取登录名）
     * @return
     */
    public static String getOpenAppUrlSchemeByToken(String appServerAddress, String token, String timestamp) {
        String base64Address = Base64.encodeBase64String((timestamp + appServerAddress).getBytes());
        String base64Token = Base64.encodeBase64String((timestamp + token).getBytes());
        String openAppUrlScheme = String.format("%s?appServerAddress=%s&token=%s&timestamp=%s",
                appSSOUrlScheme, base64Address, base64Token, timestamp);
        return openAppUrlScheme;
    }

    /**
     * 获取通过username实现单点登录的URL Scheme
     *
     * @param appServerAddress 资金管理系统访问地址
     * @param username         登录名
     * @return
     */
    public static String getOpenAppUrlSchemeByUsername(String appServerAddress, String username, String timestamp) {
        String base64Address = Base64.encodeBase64String((timestamp + appServerAddress).getBytes());
        String base64Username = Base64.encodeBase64String((timestamp + username).getBytes());
        String openAppUrlScheme = String.format("%s?appServerAddress=%s&username=%s&timestamp=%s",
                appSSOUrlScheme, base64Address, base64Username, timestamp);
        return openAppUrlScheme;
    }

}
