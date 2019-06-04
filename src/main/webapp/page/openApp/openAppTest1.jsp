<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>H5唤醒APP-1</title>
</head>

<body>
<h1>尝试打开APP的DEMO</h1>
<div class="custom-border border-color">

    <div>
        <br>
        <button onclick="openApp()">打开APP》》</button>
    </div>
    <br>
</div>
</body>

<script type="text/javascript">

    // app Scheme
    var appScheme = "myrnlinkdemo://index";
    // Android端下载地址
    var androidDownUrl = "https://appstore.huawei.com/app/C100444219";
    // IOS端下载地址
    var iosDownUrl = "https://itunes.apple.com/cn/app/id1253355672?mt=8";

    // 由安卓APP提供
    var AppConfig = {
        "scheme": "myrnlinkdemo",
        "package": "com.xxx.aos.aosdemo",
        "action": "android.intent.action.VIEW",
        "category": "android.intent.category.BROWSABLE",
        "host": ""
    };


    function openApp() {
        // 判断浏览器
        var Navigator = navigator.userAgent;
        var ifChrome = Navigator.match(/Chrome/i) != null && Navigator.match(/Version\/\d+\.\d+(\.\d+)?\sChrome\//i) == null ? true : false;
        var ifAndroid = (Navigator.match(/(Android);?[\s\/]+([\d.]+)?/)) ? true : false;
        var ifiPad = (Navigator.match(/(iPad).*OS\s([\d_]+)/)) ? true : false;
        var ifiPhone = (!ifiPad && Navigator.match(/(iPhone\sOS)\s([\d_]+)/)) ? true : false;
        var ifSafari = (ifiPhone || ifiPad) && Navigator.match(/Safari/);
        var version = 0;
        ifSafari && (version = Navigator.match(/Version\/([\d\.]+)/));
        // safari浏览器版本
        version = parseFloat(version[1], 10);
        // 是否从微信打开
        var ifWeixin = navigator.userAgent.indexOf("MicroMessenger") >= 0; // weixin

        var loadTimer;

        // android打开app 地址
        function getAndroidSchema() {
            var schemaStr = 'www.xxx.com/open?a=test';
            if (ifChrome) {
                schemaStr = "intent://" + schemaStr + "#Intent;" +
                    "scheme=" + AppConfig.scheme + ";" +
                    "package=" + AppConfig.package + ";" +
                    "category=" + AppConfig.category + ";" +
                    "S.browser_fallback_url=" + encodeURIComponent(AppConfig.FAILBACK_ANDROID) + ";" +
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
                alert('微信无法打开APP，请切换其他浏览器');
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
                    if (Date.now() - start <= 3000 + 200) {
                        window.location.href = androidDownUrl;
                    } else {
                    }
                }, 3000);
            }
        }

        if (ifiPhone) {
            var universalUrl = "https://itunes.apple.com/cn/app/id1253355672?mt=8";
            if (ifWeixin) {
                // 微信屏蔽了APP Scheme，在微信当中无法直接打开APP，因此要通过universalUrl。
                // 如果APP没有universalUrl，则提示用户无法在微信当中打开APP
                //window.location.href = universalUrl;
                alert('微信无法打开APP，请切换其他浏览器');
                return;
            }

            if (ifSafari && version >= 9) {
                // 判断IOS版本 如果大于9
                setTimeout(function () {
                    // 必须要使用settimeout
                    var ar = document.createElement("a");
                    ar.setAttribute("href", appScheme);
                    ar.style.display = "none";
                    document.body.appendChild(ar);
                    var av = document.createEvent("HTMLEvents");
                    av.initEvent("click", !1, !1);
                    ar.dispatchEvent(av);
                }, 0)
            } else {
                window.location.href = appScheme;
            }
            // 如果页面在后台运行返回，如果超过3秒没有打开APP，则没有安装，跳转到IOS应用市场
            loadTimer = setTimeout(function () {
                if (document.hidden || document.webkitHidden) {
                    return;
                }
                if (Date.now() - start > 3000 + 200) {

                } else {
                    window.location.href = iosDownUrl;
                }
            }, 3000);
        }

        // 当页面在后台运行时清空定时器防止页面跳转到下载页
        var visibilitychange = function () {
            var tag = document.hidden || document.webkitHidden;
            tag && window.clearTimeout(loadTimer);
        };
        document.addEventListener('visibilitychange', visibilitychange, false);
        document.addEventListener("webkitvisibilitychange", visibilitychange, false);
        window.addEventListener("pagehide", function () {
            clearTimeout(loadTimer);
        }, false);

    }
</script>

</html>