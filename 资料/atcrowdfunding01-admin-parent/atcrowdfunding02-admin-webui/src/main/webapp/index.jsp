<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>首页</title>
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
    <script src="jquery/jquery-2.1.1.min.js"></script>
    <script src="layer/layer.js"></script>
    <script>
        $(function () {
            $('#btnArr').click(function () {
                var array = [5, 8, 12];
                var requestBody = JSON.stringify(array);
                $.ajax({
                    url: 'send/array.html',
                    type: 'post',
                    data: requestBody,
                    contentType: 'application/json;charset=utf-8',
                    dataType: 'text',
                    success: function (data) {
                        alert(data)
                    },
                    error: function (error) {
                        alert(error)
                    }
                });
            });

            $("#btnObj").click(function () {
                // 准备要发送的数据
                var student = {
                    "stuId": 5,
                    "stuName": "tom",
                    "address": {
                        "province": "广东",
                        "city": "深圳",
                        "street": "后瑞"
                    },
                    "subjectList": [
                        {
                            "subjectName": "JavaSE",
                            "subjectScore": 100
                        }, {
                            "subjectName": "SSM",
                            "subjectScore": 99
                        }
                    ],
                    "map": {
                        "k1": "v1",
                        "k2": "v2"
                    }
                };

                // 将JSON对象转换为JSON字符串
                var requestBody = JSON.stringify(student);

                // 发送Ajax请求
                $.ajax({
                    "url": "send/obj.json",
                    "type": "post",
                    "data": requestBody,
                    "contentType": "application/json;charset=UTF-8",
                    "dataType": "json",
                    "success": function (response) {
                        console.log(response);
                    },
                    "error": function (response) {
                        console.log(response);
                    }
                });
            });

            $('#btnWin').click(function () {
                    layer.msg('layer的弹窗');
                }
            );
        })
    </script>
</head>
<body>
<a href="test/json.json">测试json</a>
<a href="test/ssm.html">测试SSM</a>
<br>
<br>
<button id="btnArr">send [5,8,12] One</button>
<br>
<br>
<button id="btnObj">send Student</button>
<br>
<br>
<button id="btnWin">点我弹窗</button>
</body>
</html>
