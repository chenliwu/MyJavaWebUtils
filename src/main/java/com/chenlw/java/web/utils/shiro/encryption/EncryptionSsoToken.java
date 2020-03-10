package com.chenlw.java.web.utils.shiro.encryption;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 加密单点登录token类
 *
 * @author chenlw
 * @date 2019/12/01
 */
public class EncryptionSsoToken implements AuthenticationToken {

    private String username;
    private String enCode;
    private String ts;

    public EncryptionSsoToken(String username, String enCode, String ts) {
        this.username = username;
        this.enCode = enCode;
        this.ts = ts;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public Object getCredentials() {
        return enCode;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEnCode() {
        return enCode;
    }

    public void setEnCode(String enCode) {
        this.enCode = enCode;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }
}
