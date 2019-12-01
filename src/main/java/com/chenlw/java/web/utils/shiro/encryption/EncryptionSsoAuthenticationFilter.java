package com.chenlw.java.web.utils.shiro.encryption;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author chenlw
 * @date 2019/11/30
 */
public class EncryptionSsoAuthenticationFilter extends AuthenticatingFilter {

    public static final String USERNAME_PARAM = "username";
    public static final String EN_CODE_PARAM = "enCode";
    public static final String TS_PARAM = "ts";

    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String username = httpServletRequest.getParameter(USERNAME_PARAM);
        String enCode = httpServletRequest.getParameter(EN_CODE_PARAM);
        String ts = httpServletRequest.getParameter(TS_PARAM);
        return new EncryptionSsoToken(username, enCode, ts);
    }

    /**
     * onAccessDenied：表示当访问拒绝时是否已经处理了；如果返回 true 表示需要继续处理；如果返回 false 表示该拦截器实例已经处理了，将直接返回即可。
     *
     * @param servletRequest  HTTP请求
     * @param servletResponse HTTP响应
     * @return 是否要继续处理该请求
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        if (this.isLoginRequest(servletRequest, servletResponse)) {
            // 判断是否为单点登录的请求，如果是，则执行登录
            return this.executeLogin(servletRequest, servletResponse);
        } else {
            Subject subject = this.getSubject(servletRequest, servletResponse);
            if (subject.getPrincipal() == null) {
                this.saveRequestAndRedirectToLogin(servletRequest, servletResponse);
                return false;
            } else {
                return true;
            }
        }
    }

    protected boolean isLoginRequest(ServletRequest request, ServletResponse response) {
        return StringUtils.isNotBlank(request.getParameter(USERNAME_PARAM))
                && StringUtils.isNotBlank(request.getParameter(EN_CODE_PARAM))
                && StringUtils.isNotBlank(request.getParameter(TS_PARAM));
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        String errorMsg = e.getMessage();
        if (e instanceof UnknownAccountException) {
        } else if (e instanceof LockedAccountException) {
        }
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            httpServletRequest.setAttribute("errorMessage", errorMsg);
            httpServletRequest.getRequestDispatcher("/error/loginFailure").forward(request, response);
        } catch (Exception var7) {
        }
        super.onLoginFailure(token, e, request, response);
        return false;
    }


}
