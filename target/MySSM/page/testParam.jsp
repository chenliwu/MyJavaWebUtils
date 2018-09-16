<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/13
  Time: 23:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试参数传递</title>
    <%@ include file="common/commonLink.jsp" %>
</head>
<body>

<button id="test1">GET方式传递参数</button>


<script>

    $(function () {

        $("#test1").click(function () {
            //(1)通过URL传递出参数
//            $.get(basePath + "/param/testGet?paramName=123", {}, function (data) {
//                alert("后台返回来的参数："+data);
//            });

            //AJAX封装参数
            var data = {};
            data.paramName="123";
            $.get(basePath + "/param/testGet",data, function (data) {
                alert("后台返回来的参数："+data);
            });
        });

    });


</script>

</body>
</html>
