<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2021/4/7
  Time: 8:32
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
    .infoDIv{
        width: 300px;
        height: 200px;
        background-color: green;
        display: none;
        position: relative;
    }
    .backBtn{
        display: block;
        position: absolute;
        bottom: 10px;
        left: 45%;
    }
</style>
</head>
<body>
    <p>
        <a href="javascript:showUserById(7);">点击查看id=7的用户</a>
    </p>

<div class="infoDIv">
    <button class="backBtn" onclick="goBack()">返回</button>
</div>


    用户名：<input type="text" name="userCode"/>
    <br>
    密码:<input type="password" name="userPassword"/>
    <br>
    <p style="width: 100px;height: 30px;background-color: green;color:white;text-align: center;" onclick="login()">登录</p>


<script>

    function login(){
        var userCode=$("input[name=userCode]").val().trim();
        var userPassword=$("input[name=userPassword]").val().trim();
        if(userCode=="" || userPassword==""){
            alert("用户名及密码不能为空");
            return false;
        }
        $.ajax({
            url:"${pageContext.request.contextPath}/t0407/login.html",
            method: "post",
            data:{userCode:userCode,userPassword:userPassword},
            success:function (data){
                data=data.replace(/\"/g,'');
                alert(data)
                if(data=="ok"){
                    alert("登录成功");
                    location="${pageContext.request.contextPath}/t0407/userInfo.jsp";
                }else{
                    alert("登录失败");
                }
            }
        })
    }


    function showUserById(id){
        $.ajax({
            url:"${pageContext.request.contextPath}/t0407/showUserById/"+id,
            method:"get",
            dataType:"json",
            success:function (data){
                console.log(data.address);
                var str=`<p>${"${data.address}"}</p>`;
                $(str).prependTo($(".infoDIv"));
                $(".infoDIv").show();
            }
        })
    }

    function goBack(){
        $(".infoDIv").hide();
        $(".backBtn").siblings().remove();
    }

</script>

</body>
</html>
