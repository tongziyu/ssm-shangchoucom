<%--
  Created by IntelliJ IDEA.
  User: 仝子瑜
  Date: 2021/1/11
  Time: 1:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
    <script src="jquery/jquery-3.3.1.js"></script>
    <script src="layer/layer.js"></script>
</head>
<body>

    <a href="test.html">测试</a>

    <a href="${pageContext.request.contextPath}/admin/all.html">查询所有管理元</a>

    <button id="btn1">send [1,22,33] ONE</button>
    <button id="btn2">send [1,22,33] TWO</button>
    <button id="btn3">send [1,22,33] THREE</button>
    <button id="btn4">show Admin id 1</button>
    <a href="admin/to/login/page.html"><button>点我管理员登录</button></a>
    <button id="btn5">点我弹框</button>
    <script>
        $(function(){
            // 编写jQuery相关代码
            $("#btn1").click(function () {
                $.ajax({
                    url: "send/array/one.json",
                    data: {
                        "array": [1, 22, 33]
                    },
                    dataType:"text",
                    success: function (response) {
                        alert(response)
                    }
                })
            })
            $("#btn2").click(function () {
                $.ajax({
                    url: "send/array/two.json",
                    data: {
                        "array[0]": 1,
                        "array[1]": 22,
                        "array[2]": 33
                    },
                    dataType:"text",
                    success: function (response) {
                        alert(response)
                    }
                })
            })


            $("#btn3").click(function () {
                // 准备好要发送到服务器端的数据
                var array = [1, 22, 33];

                //将JSON数组转换成JSON字符串
                var requestBody = JSON.stringify(array);

                $.ajax({
                    url: "send/array/three.json",
                    type:"post",
                    data: requestBody,
                    contentType:"application/json;charset=utf-8", //请求体的内容类型
                    dataType:"text",   //如何对待服务端返回的数据
                    success: function (response) {
                        alert(response)
                    }
                })
            })

            $("#btn4").click(function () {
                $.ajax({
                    url: "show/admin.json",
                    data:"",
                    contentType:"application/json;charset=utf-8", //请求体的内容类型
                    dataType:"json",   //如何对待服务端返回的数据
                    success: function (response) {
                        console.log(response);
                    }
                })
            })
            $("#btn5").click(function () {
                layer.msg("aadfa");
            })
        });
    </script>
</body>
</html>
