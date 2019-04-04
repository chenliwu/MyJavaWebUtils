<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/13
  Time: 23:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试参数传递</title>
    <%@include file="./common/commonLink.jsp" %>
</head>
<body>
<div>
    <div>GET请求方式传递参数</div>
    <button onclick="testGet1()">GET请求方式传递参数</button>
    <button onclick="testGet2()">URL传递一个参数</button>
    <button onclick="testGet2_1()">URL传递多个参数</button>
    <button onclick="testGet3()">SpringMVC自动匹配参数</button>
    <button onclick="testGet4()">SpringMVC自动装箱</button>
    <button onclick="testGet5()">后台map接收请求参数</button>
</div>
<br>
<div>
    <div>POST请求方式传递参数</div>
    <button onclick="testPost1()">AJAX传递请求参数</button>
    <button onclick="testPost2()">URL传递一个参数</button>
    <button onclick="testPost3()">SpringMVC自动匹配参数</button>
    <button onclick="testPost4()">SpringMVC自动装箱</button>
    <button onclick="testPost5()">后台map接收请求参数</button>
</div>


<script>

    $(function () {

    });


    /*****************************  GET请求方方式传递参数 start   ***********************************/

    /**
     * GET请求方式：请求参数会附在URL后面传递到后台。
     *
     */

    /**
     * GET请求方式传递参数
     *
     * @author: chenlw
     * @date 2018/10/2  23:41
     **/
    function testGet1() {
        $.get(basePath + "/api/params/testGet1?paramName=123", {}, function (data) {
            alert("GET请求方式传递参数---后台返回来的参数：" + data);
        });

        ////AJAX传递请求参数（GET请求方式）
//        var data = {};
//        data.paramName = "111";
//        data.paramName1 = "222";
//        $.get(basePath + "/api/params/testGet2", data, function (data) {
//            alert("AJAX传递请求参数（GET请求方式）---后台返回来的参数：" + data);
//        });
    }

    /**
     * URL传递一个参数
     *
     * @author: chenlw
     * @date 2018/10/2  23:45
     **/
    function testGet2() {
        ///只传递一个参数
        $.get(basePath + "/api/params/testGet2/111112333", {}, function (data) {
            alert(" URL传递参数---后台返回来的参数：" + data);
        });
    }
    /**
     * URL传递多个参数
     *
     * @author: chenlw
     * @date 2018/10/2  23:45
     **/
    function testGet2_1() {
        ///传递两个参数
        $.get(basePath + "/api/params/testGet2_1/11111/22222", {}, function (data) {
            alert(" URL传递参数---后台返回来的参数：" + data);
        });
    }





    /**
     * SpringMVC自动匹配参数（GET请求方式）
     *
     * @author: chenlw
     * @date 2018/10/2  23:40
     **/
    function testGet3() {
        $.get(basePath + "/api/params/testGet3?paramName=111&paramName1=222", {}, function (data) {
            alert("SpringMVC自动匹配参数---后台返回来的参数：" + data);
        });

    }

    /**
     * SpringMVC自动装箱（GET请求方式）
     *
     * @author: chenlw
     * @date 2018/10/2  23:40
     **/
    function testGet4() {
        var data = {};
        data.param1 = "123";
        data.param2 = "2233";
        $.get(basePath + "/api/params/testGet4", data, function (data) {
            alert("SpringMVC自动装箱---后台返回来的参数：" + data);
        });

        ///使用GET请求方式传递参数
        //SpringMVC自动装箱也可以把GET请求的参数自动装箱，将参数值装箱到实体类对应的属性上。
//        $.get(basePath + "/api/params/test4?param1=11111&param2=22222", {}, function (data) {
//            alert("后台返回来的参数：" + data);
//        });
    }


    /*****************************  GET请求方方式传递参数 end   ***********************************/



    /*****************************  POST请求方方式传递参数 start   ***********************************/

    /**
     *  POST请求方式下：所有的参数不会像GET请求方式一样附在URL后面传递到后台，所有请求参数都会放到HTTP请求的请求正文当中。
     *
     *  因此，外部无法看到请求的参数值，POST请求的安全性比GET请求高。
     *
     */

    /**
     * AJAX传递请求参数（POST请求方式）
     *
     * @author: chenlw
     * @date 2018/10/2  23:45
     **/
    function testPost1() {
        var data = {};
        data.paramName = "111";
        $.post(basePath + "/api/params/testPost1", data, function (data) {
            alert("AJAX传递请求参数（POST请求方式）---后台返回来的参数：" + data);
        });
    }

    /**
     * URL传递一个参数（POST请求方式）
     *
     * @author: chenlw
     * @date 2018/10/2  23:45
     **/
    function testPost2() {
        ///只传递一个参数
        $.post(basePath + "/api/params/testPost2/111112333", {}, function (data) {
            alert(" URL传递一个参数（POST请求方式）---后台返回来的参数：" + data);
        });
    }

    /**
     * SpringMVC自动匹配参数（POST请求方式）
     *
     * @author: chenlw
     * @date 2018/10/2  23:40
     **/
    function testPost3() {
        $.post(basePath + "/api/params/testPost3?paramName=111&paramName1=222", {}, function (data) {
            alert("SpringMVC自动匹配参数---后台返回来的参数：" + data);
        });

    }

    /**
     * SpringMVC自动装箱（POST请求方式）
     *
     * @author: chenlw
     * @date 2018/10/2  23:40
     **/
    function testPost4() {
        var data = {};
        data.param1 = "123";
        data.param2 = "2233";
        $.post(basePath + "/api/params/testPost4", data, function (data) {
            alert("SpringMVC自动装箱---后台返回来的参数：" + data);
        });
    }


    /*****************************  POST请求方方式传递参数 end   ***********************************/

</script>

</body>
</html>
