<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>H5打开APP</title>
</head>

<body>
<h1>H5打开APP</h1>
<div class="custom-border border-color">

    <br><br>
    <div>
        <br>

        <div>
            <a id="openAppLinkElement1"
               onclick="openApp()"
               href="#">
                打开APP（URL Scheme未携带登录名）
            </a>
        </div>


        <br>
        <div>
            <a id="openAppLinkElement2"
               onclick="openApp()"
               href="#">
                打开APP（URL Scheme携带了登录名）
            </a>
        </div>
        <br>

        <div>
            <a onclick="openApp()"
               href="bytter-bfs-app://index/sso?appServerAddress=1212313213&username=1232323">
                打开APP
            </a>
        </div>


    </div>


</div>
</body>
<script type="text/javascript">

    // app Scheme
    var appScheme = "bytter-bfs-app://";

    // 资金管理APP  华为应用商城下载地址
    var androidDownAppUrl = "https://appstore.huawei.com/app/C100444219";

    // 资金管理APP IOS  app store下载地址
    var iosDownAppUrl = "https://itunes.apple.com/cn/app/id1253355672?mt=8";

    var timeout = 2000;


    function openApp() {
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

        var loadTimer;

        if (ifAndroid) {
            var androidScheme = appScheme;
            if (ifWeixin) {
                // 微信无法打开APP，跳转到下载页面
                // window.location.href = androidDownUrl;
                alert('在微信中无法打开APP，请切换其他浏览器。');
                return;
            } else {
                // 其他浏览器3秒内没打开则跳转到下载链接
                window.location.href = androidScheme;
                var start = Date.now();
                // 如果页面在后台运行返回，如果超过3秒没有打开APP，则没有安装，跳转到应用市场
                loadTimer = setTimeout(function () {
                    if (document.hidden || document.webkitHidden) {
                        return;
                    }
                    if (Date.now() - start <= timeout + 200) {
                        window.location.href = androidDownAppUrl;
                    } else {
                    }
                }, timeout);
            }
        } else if (ifiPhone) {
            var universalUrl = iosDownAppUrl;
            if (ifWeixin) {
                // 微信屏蔽了APP Scheme，在微信当中无法直接打开APP，因此要通过universalUrl。
                // 如果APP没有universalUrl，则提示用户无法在微信当中打开APP
                //window.location.href = universalUrl;
                alert('在微信中无法打开APP，请切换其他浏览器。');
                return;
            }

            if (ifSafari && version >= 9) {
                // 判断IOS版本 如果大于9
                // IOS端问题记录：使用QQ打开页面时，即使APP已经安装了，页面却跳转到了下载页面。
                setTimeout(function () {
                    // 必须要使用setTimeout
                    var ar = document.createElement("a");
                    ar.setAttribute("href", appScheme);
                    ar.style.display = "none";
                    document.body.appendChild(ar);
                    var av = document.createEvent("HTMLEvents");
                    av.initEvent("click", !1, !1);
                    ar.dispatchEvent(av);
                }, 0);

            } else {
                window.location.href = appScheme;
            }
            var start = Date.now();
            // 如果页面在后台运行返回，如果超过3秒没有打开APP，则没有安装，跳转到IOS应用市场
            loadTimer = setTimeout(function () {
                if (document.hidden || document.webkitHidden) {
                    return;
                }
                if (Date.now() - start > timeout + 200) {

                } else {
                    window.location.href = iosDownAppUrl;
                }
            }, timeout);
        } else {
            alert('请在移动端（IOS/Android）浏览器打开。');
            return;
        }

        // 当页面在后台运行时清空定时器防止页面跳转到下载页
        var visibilitychange = function () {
            var tag = document.hidden || document.webkitHidden;
            tag && window.clearTimeout(loadTimer);
        };
        document.addEventListener('visibilitychange', visibilitychange, false);
        document.addEventListener("webkitvisibilitychange", visibilitychange, false);
        window.addEventListener("pagehide", function () {
            window.clearTimeout(loadTimer);
        }, false);

    }


    window.onload = function (ev) {
        jsEncodeUrlSchemeParams();
    };

    /**
     * JS端对URL Scheme参数加密
     */
    function jsEncodeUrlSchemeParams() {
        var appServerAddress = 'http://192.168.0.178:8080/t2';
        var token = 'test4';
        var username = 'test4';

        var timestamp = (new Date()).getTime().toString();

        var openAppURLScheme1 = getOpenAppUrlSchemeByToken(appServerAddress, token, timestamp);
        var openAppURLScheme2 = getOpenAppUrlSchemeByUsername(appServerAddress, username, timestamp);

        console.log('加密后的openAppURLScheme1：', openAppURLScheme1);
        console.log('加密后的openAppURLScheme2：', openAppURLScheme2);

        document.getElementById('openAppLinkElement1').href = openAppURLScheme1;
        document.getElementById('openAppLinkElement2').href = openAppURLScheme2;

    }


    /**
     * 获取通过token实现单点登录的URL Scheme
     */
    function getOpenAppUrlSchemeByToken(appServerAddress, token, timestamp) {
        var openAppURLScheme = 'bytter-bfs-app://index/sso?appServerAddress=' + base64Encode(timestamp + appServerAddress) +
            '&token=' + base64Encode(timestamp + token) + '&timestamp=' + timestamp;
        return openAppURLScheme;
    }

    /**
     * 获取通过username实现单点登录的URL Scheme
     */
    function getOpenAppUrlSchemeByUsername(appServerAddress, username, timestamp) {
        var openAppURLScheme = 'bytter-bfs-app://index/sso?appServerAddress=' + base64Encode(timestamp + appServerAddress) +
            '&username=' + base64Encode(timestamp + username) + '&timestamp=' + timestamp;
        return openAppURLScheme;
    }


    /**
     * base64编码
     * @param str
     */
    function base64Encode(str) {
        return btoa(encodeURIComponent(str));
    }

    /**
     * base64解码
     * @param str
     * @returns {string}
     */
    function base64Decode(str) {
        return decodeURIComponent(atob(str));
    }


</script>

</html>
