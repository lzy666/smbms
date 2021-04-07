<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2021/4/4
  Time: 21:09
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
    <script type="text/javascript" src="${pageContext.request.contextPath }/calendar/WdatePicker.js"></script>

</head>
<body>

<form method="post"
      action="${pageContext.request.contextPath}/test/userAdd23.html"
      enctype="multipart/form-data">
    用户编码：<input type="text" name="userCode"/><br>
    用户名称：<input type="text" name="userName"/><br>
    用户密码：<input type="password" name="userPassword"><br>
    用户生日:<input type="text" name="birthday" readonly="true"
                   onclick="WdatePicker()" class="Wdate" /><br>
    用户地址:<input type="text" name="address"/><br>
    联系电话:<input type="text" name="phone"/><br>
    用户角色:<input type="radio" name="userRole" value="1"/>系统管理员
    <input type="radio"  name="userRole" value="2"/>经理
    <input  type="radio" name="userRole" value="3" checked="true"/>普通用户
    <br>

    证件照:<input type="file" name="attaches" id="a_idPicPaht">
    <font color="red">${uploadFileError}</font>
    <br>
    工作照:<input type="file" name="attaches"  id="a_workPicPaht">
    <font color="red">${uploadWkError}</font>
    <br>
    <input type="submit" value="保存">

</form>
</body>
</html>
