<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2021/4/7
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fm" uri="http://www.springframework.org/tags/form" %>
<%
    String path = request.getContextPath();
    String basePath = request.getServerName() + ":" + request.getServerPort() + path + "/";
    String baseUrlPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    pageContext.setAttribute("baseUrlPath", baseUrlPath);
%>
<html>
<head>
    <title>Title</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <script src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
    <script>

        $(function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/t0407/getRoleList.html",
                method: "post",
                dataType: "json",
                success: function (data) {
                    console.log(data)
                    data = JSON.parse(data);
                    $("select[name=userRole]").append(`<option value="0">请选择</option>`);

                    console.log(data.length)
                    for (var i in data) {
                        var id = data[i].id;
                        var roleName = data[i].roleName;
                        console.log(id + "--" + roleName);
                        if (!isNaN(id)) {
                            $("select[name=userRole]").append(`<option value="${"${id}"}">${"${roleName}"}</option>`);

                        }
                    }
                }
            })
        })

    </script>
</head>
<body>

<form method="post" action="${pageContext.request.contextPath}/t0407/">
    用户名称:<input type="text" name="userName"/>
    <br>
    用户名称:<input type="password" name="userPassword"/>
    <br>
    角色名称:<select name="userRole"></select>
    <br>
    <button onclick="addUser()">添加</button>

</form>


</body>
</html>
