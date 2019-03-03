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
    <title>创建用户</title>
    <%@ include file="../../global/mylinks.jsp" %>
    <style>
        * {
            margin: 0;
            padding: 0;
            list-style-type: none;
        }


    </style>
</head>

<body>

<table id="demo" lay-filter="test"></table>


<script language="javascript">


    $(function () {

        layui.use('table', function () {
            var table = layui.table;

            //第一个实例
            table.render({
                elem: '#demo'
                , height: 312
                , url: "${ctx}/api/userInfo/list"   //请求URL地址
                , page: true //开启分页
                , cols: [[ //表头
                    {field: 'id', title: 'ID', width: 80, sort: true, fixed: 'left'}
                    , {field: 'username', title: '用户名', width: 80}
                ]]
                , parseData: function (res) { //res 即为原始返回的数据
                    return {
                        "count": res.length, //解析数据长度
                        "data": res.data.item //解析数据列表
                    };
                }
            });

        });
    });


    //显示消息
    function showMessage(msg) {
        layer.msg(msg);
    }


</script>

</body>
</html>
