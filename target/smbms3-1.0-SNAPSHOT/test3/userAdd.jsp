<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2021/4/6
  Time: 17:09
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
    <script src="<%=request.getContextPath()%>/js/jquery-1.8.3.min.js"></script>
</head>
<body>

<form method="post" action="" id="addForm">
    用户编码：<input type="text" name="userCode"/><span></span>
    <br>
    用户名称:<input type="text" name="userName"/><span></span>
    <br>
    <button onclick="addUser()">添加</button>

</form>

<script>
    function addUser(){

    }

    //为输入框绑定失去焦点事件
    var $userCode=$("input[name=userCode]");
    $userCode.bind("blur",function (){
        //发起ajax请求
        $.ajax({
            url:"${pageContext.request.contextPath}/test3/validUser.html",
            type:"post",
            data:{userCode:$userCode.val()},
            dataType:"json",
            success:function (data){
                data=JSON.parse(data);//将JSON型字符串解析为对象
                if(data.userCode=="exist"){
                    $userCode.next().html("用户编码已存在，不可以重复添加").css({"color":"red"});
                }else{
                    $userCode.next().html("用户编码不存在，可以放心添加").css({"color":"green"});
                }
            },
            error:function (data){
                alert("ajax请求失败"+data);
            }
        })
    })

</script>


</body>
</html>
