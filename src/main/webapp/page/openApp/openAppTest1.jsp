<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>H5唤醒APP</title>
</head>

<body>
<h1>尝试打开APP的DEMO</h1>
<div class="custom-border border-color">

    <div>
        <br>
        <div>
            <a onclick="openApp()" href="javascript:;">打开APP（通过点击事件打开）</a>
        </div>
        <div>
            <h4>问题记录</h4>
            <div>
                <p>1、IOS端在QQ浏览器点击唤醒APP的连接，通过点击事件无法打开APP，会跳转到APP下载页面。</p>
            </div>

        </div>

    </div>

    <br><br>
    <div>
        <br>

        <div>
            <a onclick="openApp()"
               href="bytter-bfs-app://index?appUrl=http://192.168.0.178:8080/t2&sourceUrl=http://192.168.0.178:8088/webUtils/api/user/getUsernameByToken&token=4">
                打开APP（通过超链接的href属性打开A）
            </a>
        </div>
        <div>
            <h4>问题记录</h4>
            <p>1、IOS端在QQ内点击唤醒APP的连接，如果不是通过超链接的href属性，则无法打开APP，会跳转到APP下载页面；用Safari浏览器打开，则会先弹出一个询问对话框，确认后才会打开APP。</p>
            <p>2、IOS端在QQ浏览器点击唤醒APP的连接，通过超链接的href属性无法打开APP，会跳转到APP下载页面。</p>

        </div>

    </div>
    <br>
</div>
</body>

<script type="text/javascript">

    // app Scheme
    //var appScheme = "myrnlinkdemo://index";
    var appScheme = "bytter-bfs-app://index?appUrl=http://192.168.0.178:8080/t2&sourceUrl=http://192.168.0.178:8088/webUtils/api/user/getUsernameByToken&token=4";
    // Android端下载地址
    var androidDownAppUrl = "https://appstore.huawei.com/app/C100444219";
    // IOS端下载地址
    var iosDownAppUrl = "https://itunes.apple.com/cn/app/id1253355672?mt=8";

    // 由安卓APP提供
    var AppConfig = {
        "scheme": "myrnlinkdemo",
        "package": "com.xxx.aos.aosdemo",
        "action": "android.intent.action.VIEW",
        "category": "android.intent.category.BROWSABLE",
        "host": ""
    };

    var timeout = 3000;


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

//                var ifr = document.createElement('iframe');
//                ifr.src = appScheme;
//                ifr.style.display = 'none';
//                document.body.appendChild(ifr);
//                window.setTimeout(function () {
//                    document.body.removeChild(ifr);
//                }, timeout);

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
</script>

</html>
