<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2021/4/6
  Time: 14:07
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
   <%-- <script src="../js/jquery-1.8.3.min.js"></script>--%>
</head>
<body>

<form method="post"
      action="${pageContext.request.contextPath}/test2/updateUser.html">
    用户编码:<input type="text" name="userCode" readonly value="${user.userCode}"/>
    <br/>
    用户名称:<input type="text" name="userName" value="${user.userName}"/>
    <br>

    <input type="hidden" name="id" value="${user.id}"/>
    <input type="submit" value="修改">
</form>

</body>
</html>
