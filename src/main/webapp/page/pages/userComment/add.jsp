<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/12
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="<%=request.getContextPath()%>"/>
<html>
<head>
    <title>测试用户评论功能</title>
    <%@ include file="../../global/mylinks.jsp" %>
</head>
<body>

<div class="layui-container">

    <div class="layui-row">
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">评论</label>
            <div class="layui-input-block">
                <textarea id="inputCommentElement" placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
        </div>
        <button type="button" id="addCommentElement" class="layui-btn">添加评论</button>
    </div>

</div>

<script type="text/javascript" src="<%=request.getContextPath()%>/page/js/jquery-2.1.0.min.js"></script>
<script>

    $(function () {

        $("#addCommentElement").click(function () {
            addParentComment();
        });


    });

    function addParentComment() {
        var comment = $("#inputCommentElement").val();
        if (comment.length === 0) {
            showMessage("请输入评论");
            $("#inputCommentElement").focus();
            return;
        }

        $.ajax({
            type: "POST",
            url: "${ctx}/api/userComment/add",   //请求URL地址
            dataType: "json",           //服务器返回数据类型
            data: {
                ownerId: '7152da125453460eb3c2c7f98a76a20c',
                commenterId: '0a98493f8632477d898de5f9e247f86d',
                content: comment
            }, //请求参数
            success: function (res) {
                showMessage(res.message);
            }, //请求成功回调方法
            error: function (msg) {
                showMessage(msg);
            }      //请求失败回调方法
        });
    }

    //显示消息
    function showMessage(msg) {
        layer.msg(msg);
    }


</script>


</body>
</html>
