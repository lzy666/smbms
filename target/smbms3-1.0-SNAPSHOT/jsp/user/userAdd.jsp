<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2021/4/4
  Time: 17:27
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
    <style>
        .fm-error{
            display: block;
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>
<fm:form
         method="post" modelAttribute="user"
        enctype="multipart/form-data">
    用户编码:<fm:input path="userCode" /><br>
    <fm:errors path="userCode" cssClass="fm-error"/>
    用户名称:<fm:input path="userName"/><br>
    <fm:errors path="userName" cssClass="fm-error"/>
    用户密码:<fm:password path="userPassword"/><br/>
    <fm:errors path="userPassword" cssClass="fm-error"/>
    用户生日:<fm:input path="birthday" readonly="true"
                   onclick="WdatePicker()" cssClass="Wdate" /><br>
    <fm:errors  path="birthday" cssClass="fm-error"/>
    用户地址:<fm:input path="address"/><br>
    联系电话:<fm:input path="phone"/><br>
    <fm:errors  path="phone" cssClass="fm-error"/>
    用户角色:<fm:radiobutton path="userRole" value="1"/>系统管理员
    <fm:radiobutton path="userRole" value="2"/>经理
    <fm:radiobutton path="userRole" value="3" checked="true"/>普通用户
    <br>
    证件照:<input type="file" name="idPicPath">
    <font color="red">${uploadFileError}</font>
    <br>
    <input type="submit" value="保存">

</fm:form>
</body>
</html>
