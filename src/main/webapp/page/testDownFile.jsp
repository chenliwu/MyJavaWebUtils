<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/27
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试下载文件</title>
    <%@ include file="common/commonLink.jsp" %>
</head>
<body>

<div>
    <h3>超链接下载文件</h3>
    <a href="<%=request.getContextPath()%>/api/files/downFile">超链接下载文件</a>
</div>
<div>
    <h3>按钮下载文件</h3>
    <button id="downFileElement" type="button">按钮下载文件</button>
</div>

<script>
    $(function () {
        $("#downFileElement").click(function () {
            location.href = basePath + "/api/files/downFile";
        });
    });
</script>
</body>
</html>
