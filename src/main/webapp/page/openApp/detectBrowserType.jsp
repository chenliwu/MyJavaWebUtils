<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>识别浏览器类型</title>
</head>

<body>
<h1>访问设备的浏览器类型</h1>
<div class="custom-border border-color">
    <div id="showBrowserTypeElement"></div>
</div>
</body>

<script type="text/javascript">

    window.onload = function (ev) {
        // 判断浏览器
        var userAgent = navigator.userAgent;
        var ifChrome = userAgent.match(/Chrome/i) != null && userAgent.match(/Version\/\d+\.\d+(\.\d+)?\sChrome\//i) == null ? true : false;
        var ifAndroid = (userAgent.match(/(Android);?[\s\/]+([\d.]+)?/)) ? true : false;
        var ifiPad = (userAgent.match(/(iPad).*OS\s([\d_]+)/)) ? true : false;
        var ifiPhone = (!ifiPad && userAgent.match(/(iPhone\sOS)\s([\d_]+)/)) ? true : false;
        var ifSafari = (ifiPhone || ifiPad) && userAgent.match(/Safari/);
        var version = 0;
        ifSafari && (version = userAgent.match(/Version\/([\d\.]+)/));
        // safari浏览器版本
        version = parseFloat(version[1], 10);
        // 是否从微信打开
        var ifWeixin = userAgent.indexOf("MicroMessenger") >= 0;

        document.getElementById('showBrowserTypeElement').innerHTML = JSON.stringify(userAgent);
    };

</script>

</html>
