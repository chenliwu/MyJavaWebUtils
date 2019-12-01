package com.chenlw.java.web.utils.shiro.encryption;

import com.chenlw.java.web.utils.common.utils.MD5Utils;
import com.chenlw.java.web.utils.entity.UserEntity;
import com.chenlw.java.web.utils.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 加密单点登录身份验证和授权处理realm
 *
 * @author chenlw
 * @date 2019/12/01
 */
public class EncryptionSsoRealm extends AuthorizingRealm {

    @Resource
    private IUserService userService;

    private static String encryptionKey = "chenlw";


    public static void main(String[] args) {
        String username = "clw";
        String ts = String.valueOf(new Date().getTime());
        String enCode1 = MD5Utils.md5(username + encryptionKey + ts);
        String enCode2 = MD5Utils.md5(enCode1);
        System.out.println("ts:" + ts);
        System.out.println("enCode2:" + enCode2);
        String show = String.format("localhost:8050/WebUtils/enSso?username=clw&enCode=%s&ts=%s", enCode2, ts);
        System.out.println(show);
    }

    /**
     * 判断当前realm是否处理该请求
     *
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        if (token instanceof EncryptionSsoToken) {
            return true;
        }
        return super.supports(token);
    }

    /**
     * 身份验证
     *
     * @param authenticationToken token
     * @return 身份验证信息
     * @throws AuthenticationException 验证失败则抛出异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        EncryptionSsoToken encryptionSsoToken = (EncryptionSsoToken) authenticationToken;

        String enCode = encryptionSsoToken.getEnCode();
        if (StringUtils.isEmpty(enCode)) {
            throw new AuthenticationException("用户无权限访问！");
        }

        String username = encryptionSsoToken.getUsername();
        if (username == null) {
            throw new AccountException("用户名不能为空");
        }
        UserEntity user = userService.getUserByUsername(username);
        if (user == null) {
            throw new UnknownAccountException("用户不存在");
        }

        // 验证加密串是否正确，如果正确，则允许登录
        // 加密规则，MD5(用户名 + 固定串 + 时间戳)   加密两次
        String ts = encryptionSsoToken.getTs();
        String enCode1 = MD5Utils.md5(username + encryptionKey + ts);
        String enCode2 = MD5Utils.md5(enCode1);
        if (enCode.equals(enCode2)) {
            return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        } else {
            throw new AuthenticationException("用户无权限访问！");
        }
    }

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

}
