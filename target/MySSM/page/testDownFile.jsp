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

    <div>
        <button id="downFileElement1">跨域下载文件</button>
    </div>


</div>

<script>
    $(function () {
        $("#downFileElement").click(function () {
            /**
             * 使用location.href方式下载文件
             * （1）谷歌浏览器：直接下载文件。
             * （2）IE浏览器：直接下载文件。
             * （3）火狐浏览器：打开保存对话框
             *
             */
            //location.href = basePath + "/api/files/downFile";

            /**
             * 2018-10-24
             * 使用window.open方式下载文件
             * （1）谷歌浏览器：使用window.open会打开一个新窗口，快速关闭后下载文件。
             * （2）IE浏览器：会打开一个下载文件对话框。
             * （3）火狐浏览器：打开保存对话框
             */
            window.open(basePath + "/api/files/downFile");

        });

        $("#downFileElement1").click(function () {
            location.href = basePath + "/api/files/downFile1";
        });

    });
</script>
</body>
</html>
