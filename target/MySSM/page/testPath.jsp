<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/15
  Time: 22:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="baseURL" value="${request.getContextPath()}"/>
<html>
<head>
    <title>测试服务器路径</title>
</head>
<body>


<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div>

    "request.getContextPath()的值是   "<%=request.getContextPath()%><br/>
    "pageContext.request.contextPath的值是"${pageContext.request.contextPath}<br/>
    <br>
    basePath = <%=basePath%>
    <br>
    <br>
    baseURL = ${baseURL}
</div>

</body>
</html>
