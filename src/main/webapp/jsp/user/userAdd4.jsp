<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2021/4/6
  Time: 2:17
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

<form method="post" id="addForm" action="${pageContext.request.contextPath}/test/userAdd3.html">
    用户编码：<input type="text" name="userCode"/><span></span>
    <br>
    用户名称：<input type="text" name="userName"/>
    <br>
    <button id="submit">提交</button>

</form>

<script>
    var $userCode=$("input[name=userCode]");
    var $submit=$("#submit");
    var $addForm=$("#addForm");
    var path="${pageContext.request.contextPath}";

    $userCode.bind("blur",function (){
        var userCodeVal=$(this).val().trim();
        if(userCodeVal==""){
            $(this).next().html("您尚未填写信息").css({"color":"red"});
            $(this).attr("validateStatus","false");
        }else{
            $.ajax({
                url:path+"/test/userCodeExists.html",
                type:"post",
                data:{userCode:userCodeVal},
                dataType:"json",
                success:function (data){
                    console.log(data+"--"+data.userCode)
                    //data=eval("("+data+")");
                    data=JSON.parse(data);
                    console.log(data+"--"+data.userCode)
                    if(data.userCode=="exist"){
                        $userCode.next().html("用户编码已存在，不可注册").css({"color":"red"});
                        $userCode.attr("validateStatus","false");
                    }else{
                        $userCode.next().html("用户编码不存在，可以注册").css({"color":"green"});
                        $userCode.attr("validateStatus","true");
                    }
                },
                error:function (data){
                    $(this).next().html("未找到页面").css({"color":"red"});
                    $(this).attr("validateStatus","false");
                }
            })
        }

    })

    $submit.click(function (){
        if($userCode.attr("validateStatus")!="true"){
            $userCode.blur();
            return false;
        }else{
            $addForm.submit();
        }
    })

</script>

</body>
</html>
