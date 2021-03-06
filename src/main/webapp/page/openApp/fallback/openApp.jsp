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
               href="${openAppUrlScheme}">
                打开APP（测试正常单点登录：URL Scheme携带token）
            </a>

        </div>


        <br>
        <div>
            <a id="openAppLinkElement2"
               onclick="openApp()"
               href="">
                打开APP（测试正常单点登录，URL Scheme携带username）
            </a>
        </div>


        <br>
        <div>
            <a onclick="openApp()"
               href="bytter-bfs-app://index?appServerAddress=http://192.168.0.178:808/t2&tokenServerAddress=http://192.168.0.178:8088/webUtils/api/user/getUsernameByToken&token=4">
                打开APP（测试不正常单点登录）
            </a>
        </div>

        <div>
            <h4>问题记录</h4>
            <p>1、IOS端在QQ内点击唤醒APP的连接，如果不是通过超链接的href属性，则无法打开APP，会跳转到APP下载页面；用Safari浏览器打开，则会先弹出一个询问对话框，确认后才会打开APP。</p>
            <p>2、IOS端在QQ浏览器点击唤醒APP的连接，通过超链接的href属性无法打开APP，会跳转到APP下载页面。</p>

        </div>

    </div>
    <br><br>


    <div>
        <h4>H5打开APP的方法</h4>
        <div>
            <p>
                在H5页面打开APP的方法一般有两种，在IOS 9以前，一般使用的技术是URL Scheme。
                这种方式虽然可自定义程度很高，能够巧妙地实现很多跳转，但弊端也很明显：我们只能通过 scheme://example
                这种格式的链接来实现跳转，而且现在苹果还对这种方式的跳转加了一个提示框：“是否打开XXX”。
            </p>
            <p>
                对于对Web和原生App交互的场景需求量很大的产品来说，这样的跳转方式显然是步骤冗杂的，用户体验并不好。
                iOS 9 以后，Universal Links 的出现完美的解决了这个问题。它所提供的直接、顺畅、无缝衔接的跳转能够让用户体验提升一个很大的级别。
                用户可以点击开发者指定的类似于 https://example.com/t 的URL直接唤醒App，而不需要在浏览器打开再点击其他按钮，实现真上的一键直达，无缝链接。
            </p>

        </div>
    </div>


</div>
</body>
<script type="text/javascript">

    // app Scheme
    //var appScheme = "myrnlinkdemo://index";
    var appScheme = "bytter-bfs-app://index?appUrl=http://192.168.0.178:8080/t2&sourceUrl=http://192.168.0.178:8088/webUtils/api/user/getUsernameByToken&token=4";
    // 资金管理APP  华为应用商城下载地址
    var androidDownAppUrl = "https://appstore.huawei.com/app/C100444219";
    // 资金管理APP IOS  app store下载地址
    var iosDownAppUrl = "https://itunes.apple.com/cn/app/id1253355672?mt=8";

    // 由安卓APP提供
    var AppConfig = {
        "scheme": "myrnlinkdemo",
        "package": "com.xxx.aos.aosdemo",
        "action": "android.intent.action.VIEW",
        "category": "android.intent.category.BROWSABLE",
        "host": ""
    };

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

        // android打开app 地址
        function getAndroidSchema() {
            var schemaStr = 'www.xxx.com/open?a=test';
            if (ifChrome) {
                schemaStr = "intent://" + schemaStr + "#Intent;" +
                    "scheme=" + AppConfig.scheme + ";" +
                    "package=" + AppConfig.package + ";" +
                    "category=" + AppConfig.category + ";" +
                    "S.browser_fallback_url=" + encodeURIComponent(androidDownAppUrl) + ";" +
                    "end";
            } else {
                schemaStr = appScheme;
            }
            return schemaStr;
        }


        if (ifAndroid) {
            var androidScheme = getAndroidSchema();
            if (ifChrome) {
                // chrome会自动识别S.browser_fallback_url如果没有安装则打开下载链接地址
                window.location.href = androidScheme;
            } else if (ifWeixin) {
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
            var universalUrl = "https://itunes.apple.com/cn/app/id1253355672?mt=8";
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
