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
    <title>用户登录</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/page/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/page/bootstrapvalidator-0.4.5/dist/css/bootstrapValidator.min.css" />
</head>
<body>

<div class="content">
    <div class="box box-primary">
        <div class="box-header">
            <h3 class="box-title">用户登录</h3>
        </div>
        <div class="box-body">
            <form id="loginForm" class="form-horizontal">

                <div class="col-sm-8">
                    <div class="form-group">
                        <label for="usernameElement" class="col-sm-2 control-label">登录账号</label>
                        <div class="col-sm-10">
                            <input type="text" maxlength="30" required class="form-control" data-bv-notempty-message="登录账号不能为空" id="usernameElement"
                                   name="username" placeholder="登录账号">
                        </div>
                    </div>
                </div>

                <div class="col-sm-8">
                    <div class="form-group">
                        <label for="passwordElement" class="col-sm-2 control-label">登录账号</label>
                        <div class="col-sm-10">
                            <input type="password" required class="form-control" data-bv-notempty-message="登录密码不能为空" id="passwordElement" name="password"
                                   placeholder="登录密码">
                        </div>
                    </div>
                </div>

                <div class="col-md-8">
                    <div class="text-center">
                        <button type="button" id="login" class="btn btn-primary">登录</button>
                        <button type="reset" class="btn btn-default">重置</button>
                    </div>
                </div>


            </form>
        </div>
    </div>

</div>

<script type="text/javascript" src="<%=request.getContextPath()%>/page/js/jquery-2.1.0.min.js"></script>
<script src="<%=request.getContextPath()%>/page/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<script src="<%=request.getContextPath()%>/page/bootstrapvalidator-0.4.5/dist/js/bootstrapValidator.min.js"></script>
<script>

    $(function () {
        initForm();
        $("#login").click(function(){

            //校验数据
            var bv = $("#loginForm").data('bootstrapValidator');
            bv.validate();
            if (!bv.isValid()) {
                return;
            }

            var username = $("#usernameElement").val();
            var password = $("#passwordElement").val();
            $.post(
                "<%=request.getContextPath()%>/api/user/login1",
                {username:username,password:password},
                function(data,textStatus){
                    console.log(data);
                    if(data){
                        $(window).attr('location', '${ctx}/list');
                    }else{
                        alert("登录失败");
                    }
                }
            )
        });

    });

    //初始化表格
    function initForm() {
        $('#loginForm').bootstrapValidator({
            message: '输入值不合法',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {

            }
        });
    }

</script>


</body>
</html>
