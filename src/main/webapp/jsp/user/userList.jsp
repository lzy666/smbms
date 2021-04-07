<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2021/4/6
  Time: 3:42
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
<style>
    #userInfo{
        position: absolute;
        top: 100px;
        left: 100px;
        border: 1px solid gray;
        display: none;
    }


</style>
</head>
<body>

<a href="javascript:showInfo(1)">查看</a>
<hr/>
<a href="${pageContext.request.contextPath}/test/showInfo.json?id=1">查看2</a>
<hr/>
<a href="${pageContext.request.contextPath}/test/showInfo.html?id=1">查看3</a>


<div id="userInfo"></div>

<script>

    function showInfo(id){
        $.ajax({
            url:"${pageContext.request.contextPath}/test/showInfo/"+id,
            method:"get",
            dataType:"json",
            success:function (data){
                console.log(data);
                $("#userInfo").html(`<p>用户编码：${"${data.userCode}"}</p>
                                    <p>用户名称：${"${data.userName}"}</p>
                                    <button id="backBtn" onclick="hideInfo()">返回</button>`).show();

            },
            error:function (data){

            }
        })
    }

    function hideInfo(){
        $("#userInfo").html("");
    }

</script>

</body>
</html>
