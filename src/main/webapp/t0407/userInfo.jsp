<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2021/4/7
  Time: 9:26
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

</head>
<body>
    <p>
        欢迎您，${session_userLogin}
    </p>
    <h1>可以修改密码：</h1>
    旧密码:<input type="password" name="userPassword"/><span></span>
    <br>
    新密码:<input type="password" name="userPassword1"/><span></span>
    <br>
    确认密码:<input type="password" name="userPassword2"/><span></span>
    <br>
    <button onclick="savePwd()">保存</button>

<script>
    var oldPassword="${session_userLogin.userPassword}";
    var id="${session_userLogin.id}";

    var $userPassword=$("input[name=userPassword]");
    var $userPassword1=$("input[name=userPassword1]");
    var $userPassword2=$("input[name=userPassword2]");

    var flag=false;

    $userPassword.focus(function (){
        $(this).next().html("*请输入密码").css({"color":"gray"});
    })
    $userPassword.blur(function (){
        if($(this).val()==""){
            $(this).next().html("*请输入旧密码").css({"color":"red"});
        }else if($(this).val()!=oldPassword){
            $(this).next().html("*旧密码不正确").css({"color":"red"});
        }else{
            $(this).next().html("√").css({"color":"green"});
        }
    })
    var regexp=/\w{6,20}/;

    $userPassword1.focus(function (){
        $(this).next().html("*密码长度必须大于6位小于20位").css({"color":"gray"});
    })
    $userPassword1.blur(function (){
        if(!regexp.test($(this).val())){
            $(this).next().html("*密码输入不符合规范,请重新输入").css({"color":"red"});
        }else if($(this).val()==oldPassword){
            $(this).next().html("*与旧密码雷同,请重新输入").css({"color":"red"});

        }else{
            $(this).next().html("√").css({"color":"green"});
        }
    })
    $userPassword2.focus(function (){
        $(this).next().html("*请输入与上面一致的密码").css({"color":"gray"});
    })
    $userPassword2.blur(function (){
        var userPassword1=$("input[name=userPassword1]");
        if(!regexp.test($(this).val()) &&
            $userPassword2.val()!=$userPassword1.val()){
            $(this).next().html("*两次密码输入不一致,请重新输入").css({"color":"red"});
        }else{
            $(this).next().html("√").css({"color":"green"});
            flag=true;
        }
    })

    function savePwd(){
        var userPassword2=$("input[name=userPassword2]").val();
        if(flag){
            $.ajax({
                url:"${pageContext.request.contextPath}/t0407/modifyPwd.html",
                method:"post",
                data:{id:id,
                    userPassword2:userPassword2},
                success:function (data){
                    data=data.replace(/\"/g,'');
                    if(data=="ok"){
                        alert("密码修改成功");
                    }else{
                        alert("密码修改失败");
                    }
                }
            })
        }
    }

</script>



</body>
</html>
