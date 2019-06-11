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
            <a id="openAppLinkElement"
               onclick="openApp()"
               href="${openAppUrlScheme}">
                打开APP（测试正常单点登录：URL Scheme携带token）
            </a>

        </div>


        <br>

    </div>


</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/page/crypto-js-3.1.9-1/crypto-js.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/page/crypto-js-3.1.9-1/aes.js"></script>
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


    ////////////测试URL参数加密

    var openAppUrlScheme = "${openAppUrlScheme}";

    window.onload = function (ev) {
        var params = getUrlParamsToJSON(openAppUrlScheme);
        console.log('openAppUrlScheme:', openAppUrlScheme);
        var appServerAddress;
        if (params && params['appServerAddress'] && params['timestamp']) {
            appServerAddress = params['appServerAddress'];
            var timestamp = params['timestamp'];
            console.log('timestamp:', timestamp);
            console.log('解密前的appServerAddress：', appServerAddress);
            appServerAddress = decryptParam(base64Decode(appServerAddress), timestamp);
            console.log('解密后的appServerAddress：', appServerAddress);
       }

        //testAES();
        //testBase64Encode();
    };

    /**
     * URL参数使用AES加密
     */
    function testAES() {
        var address = 'app server address';
        //timestamp作为加密和解密的密匙
        var timestamp = (new Date()).getTime().toString();
        //AES加密
        var encryptAddress = encryptParam(address, timestamp);

        var href = 'myrnlinkdemo://index?address=' + encryptAddress+'&timestamp='+timestamp;
        document.getElementById('openAppLinkElement').href = href;
    }

    /**
     * URL参数使用base64编码
     */
    function testBase64Encode() {
        var address = 'app server address';

        //base 64转码
        var base64Address = base64Encode(address);
        var href = 'myrnlinkdemo://index?address=' + base64Address;
        document.getElementById('openAppLinkElement').href = href;
    }


    // 获取URL的查询参数
    // 将参数转化成JSON对象：如果URL没有携带参数，则JSON对象为{}
    function getUrlParamsToJSON(url) {
        var params = new Map();
        //去除所有空格
        url = url.replace(/\s/ig, '');
        //正则表达式匹配
        url.replace(/([^?&=]+)=([^&]+)/g, function (_, key, value) {
            params[key] = value;
        });
        return params;
    }

    function base64Encode(str) {
        return btoa(encodeURIComponent(str));
    }

    function base64Decode(str) {
        return decodeURIComponent(atob(str));
    }


    // 加密
    function encryptParam(param, AESKey) {
        var ciphertext = CryptoJS.AES.encrypt(param, AESKey).toString();
        return ciphertext;
    }


    // 解密
    function decryptParam(ciphertext, AESKey) {
        var bytes = CryptoJS.AES.decrypt(ciphertext, AESKey);
        var originalText = bytes.toString(CryptoJS.enc.Utf8);
        return originalText;
    }

</script>

</html>
