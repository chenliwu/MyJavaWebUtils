package com.chenlw.java.web.utils.shiro.encryption;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.InitializingBean;

/**
 * 凭证匹配器
 *
 * @author chenlw
 * @date 2019/11/30
 */
public class EncryptionCredentialsMatcher extends HashedCredentialsMatcher implements InitializingBean {

    /**
     * 重写凭证验证规则
     *
     * @param token
     * @param info
     * @return
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
