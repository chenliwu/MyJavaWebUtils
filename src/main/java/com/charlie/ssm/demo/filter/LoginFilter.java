package com.charlie.ssm.demo.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录请求过滤器：如果用户没有登录，让其跳转到登录界面
 */
public class LoginFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        if(null==request.getSession().getAttribute("user")){
            String path = request.getContextPath();
            //如果操作员没有登录则跳转到登录页面
            response.sendRedirect(path+"/login");
        }else{
            //操作员已经登录，正常跳转
            filterChain.doFilter(request,response);
        }
    }

    public void destroy() {

    }
}
