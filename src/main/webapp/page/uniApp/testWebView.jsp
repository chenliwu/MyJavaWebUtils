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
<title>测试web-view</title>
<%@ include file="./../global/mylinks.jsp" %>
<body>

<button onclick="sendMessageToUniApp()">发送消息到uni-app</button>

</body>

<!-- uni 的 SDK -->
<script type="text/javascript" src="https://js.cdn.aliyun.dcloud.net.cn/dev/uni-app/uni.webview.1.5.1.js"></script>

<script>


    function sendMessageToUniApp() {
        console.log('sendMessageToUniApp');
        uni.postMessage({
            data: {
                action: 'postMessage',
                msg: 'HTML发送的消息'
            }
        });
    }

    document.addEventListener('UniAppJSBridgeReady', function () {
        uni.postMessage({
            data: {
                action: 'postMessage',
                msg: 'HTML发送的消息'
            }
        });
        uni.getEnv(function (res) {
            console.log('当前环境：' + JSON.stringify(res));
        });
    });


</script>

</html>
