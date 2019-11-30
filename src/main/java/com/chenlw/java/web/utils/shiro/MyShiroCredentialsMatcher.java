package com.chenlw.java.web.utils.shiro;

import com.chenlw.java.web.utils.entity.UserEntity;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.InitializingBean;

/**
 * 凭证匹配器
 *
 * @author chenlw
 * @date 2019/11/30
 */
public class MyShiroCredentialsMatcher extends HashedCredentialsMatcher implements InitializingBean {

    /**
     * 重写凭证验证规则
     *
     * @param token
     * @param info
     * @return
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String password = String.valueOf(usernamePasswordToken.getPassword());
        String credentials = (String) info.getCredentials();
        if (!StringUtils.isEmpty(password)) {
            return password.equals(credentials);
        }
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
