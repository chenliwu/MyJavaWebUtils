package com.chenlw.java.web.utils.shiro.filter;

import com.chenlw.java.web.utils.common.entity.ResultEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.MediaType;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author chenlw
 * @date 2019/11/30
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {


//    @Override
//    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
//        if (!StringUtils.isEmpty(getSuccessUrl())) {
//            // getSession(false)：如果当前session为null,则返回null,而不是创建一个新的session
//            Session session = subject.getSession(false);
//            if (session != null) {
//                session.removeAttribute("shiroSavedRequest");
//            }
//        }
//        return super.onLoginSuccess(token, subject, request, response);
//    }


    @Override
    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
        super.issueSuccessRedirect(request, response);
        System.out.println();
        // WebUtils.issueRedirect(request, response, getSuccessUrl(), null, true);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        ResultEntity resultEntity = new ResultEntity();
        if (e instanceof UnknownAccountException) {
            resultEntity.setMessage("登录失败！账号或密码错误");
        }else{
            resultEntity.setMessage("登录失败！原因：" + e.getMessage());
        }
        sendResultEntity(response, resultEntity);
        return true;
    }

    /**
     * 发送登录成功或失败时的AJAX信息
     *
     * @param response
     * @param resultEntity
     */
    protected void sendResultEntity(ServletResponse response, ResultEntity resultEntity) {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);   // 避免乱码
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            writer = response.getWriter();
            writer.write(objectMapper.writeValueAsString(resultEntity));
        } catch (IOException e) {

        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
