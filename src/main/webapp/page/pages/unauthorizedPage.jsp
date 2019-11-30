<%--
  Created by IntelliJ IDEA.
  User: stl
  Date: 2018/1/19
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>用户登录</title>
    <%@ include file="./../global/mylinks.jsp" %>
    <style>
        *{
            margin: 0;padding: 0;list-style-type: none;
        }
        header{
            margin: 0 auto;
            color: white;
            width: 100%;
            height: 100px;
            line-height: 100px;
            text-align: center;     /*文本居中显示*/

        }
        header h1{
            font-size: 25px;
        }
        .login-body{
            width: 300px;
            height: 100px;
            line-height: 100px;     /*line-height和height搭配使用，让文本居中*/
            text-align: center;     /*文本居中显示*/
            margin: 100px auto;     /*盒子居中*/
        }
        .login-body *{
            margin-bottom: 10px;
        }

    </style>
</head>

<body>
<header class="layui-bg-green"><h1>当前用户未得到授权</h1></header>
<script language="javascript">

</script>

</body>
</html>
