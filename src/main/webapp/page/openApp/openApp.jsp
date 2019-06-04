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
    <div>
        <button onclick="submitFn()">打开APP》》</button>
    </div>
</body>

<script>

    var appScheme = "myrnlinkdemo://";
    var androidDownUrl = "https://appstore.huawei.com/app/C100444219";
    var iosDownUrl = "https://itunes.apple.com/cn/app/id1253355672?mt=8";

    function submitFn() {
        console.log('open app');
        //判断浏览器
        var u = navigator.userAgent;
        if (/MicroMessenger/gi.test(u)) {
            // 引导用户在浏览器中打开
            alert('请在移动浏览器中打开');
            return;
        }
        var d = new Date();
        var t0 = d.getTime();
        if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {
            //Android
            if (openApp(appScheme)) {
                openApp(appScheme);
            } else {
                //由于打开需要1～2秒，利用这个时间差来处理－－打开app后，返回h5页面会出现页面变成app下载页面，影响用户体验
                var delay = setInterval(function () {
                    var d = new Date();
                    var t1 = d.getTime();
                    if (t1 - t0 < 3000 && t1 - t0 > 2000) {
                        alert('请下载APP');
                        window.location.href = androidDownUrl;
                    }
                    if (t1 - t0 >= 3000) {
                        clearInterval(delay);
                    }
                }, 1000);
            }
        } else if (u.indexOf('iPhone') > -1) {
            //IOS
            if (openApp(appScheme)) {
                openApp(appScheme);
            } else {
                var delay = setInterval(function () {
                    var d = new Date();
                    var t1 = d.getTime();
                    if (t1 - t0 < 3000 && t1 - t0 > 2000) {
                        alert('请下载APP');
                        window.location.href = iosDownUrl;
                    }
                    if (t1 - t0 >= 3000) {
                        clearInterval(delay);
                    }
                }, 1000);
            }
        }
    }

    function openApp(src) {
        // 通过iframe的方式试图打开APP，如果能正常打开，会直接切换到APP，并自动阻止a标签的默认行为
        // 否则打开a标签的href链接
        var ifr = document.createElement('iframe');
        ifr.src = src;
        ifr.style.display = 'none';
        document.body.appendChild(ifr);
        window.setTimeout(function () {
            document.body.removeChild(ifr);
        }, 2000);
    }
</script>

</html>