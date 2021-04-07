<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2021/4/5
  Time: 16:27
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

    <style>
        .cc{
            color: red;
            font-weight: bold;
        }
    </style>

</head>
<body>
<%--spring的表单标签--%>
<fm:form method="post"
        modelAttribute="user" enctype="multipart/form-data">
    用户编码:<fm:input path="userCode" />
    <fm:errors path="userCode" cssClass="cc"/>
    <br>
    用户名称:<fm:input path="userName"  />
    <fm:errors path="userName" cssClass="cc"/>
    <br>
    用户密码:<fm:password path="userPassword" id="userPassword"/>
    <fm:errors path="userPassword" cssClass="cc"/>
    <br>
    确认密码:<input type="password" name="userPassword2" id="userPassword2">
    <br>
    用户性别:<fm:radiobutton path="gender" value="1"/>男
    <fm:radiobutton path="gender" value="2"/>女
    <fm:errors path="gender" cssClass="cc"/>
    <br>
    出生日期:<fm:input path="birthday" readonly="readonly"
                   class="Wdate" id="birthday"
                   onclick="WdatePicker();"/>
    <br>
    用户电话:<fm:input path="phone"/>
    <fm:errors path="phone"/>
    <br>
    用户地址:<fm:input path="address"  cssClass="cc"/>
    <br>
    用户角色:<fm:select path="userRole">
        <fm:option value="1">系统管理员</fm:option>
        <fm:option value="2">经理</fm:option>
        <fm:option value="3">普通员工</fm:option>
    </fm:select>
    <br>
    证件照:<input type="file" name="attaches" id="a_idPicPath"/><span style="color: red;font-weight: bold;">${idPicPathError}</span>
    <br>
    工作照:<input type="file" name="attaches"  id="a_workPicPath"/><span style="color: red;font-weight: bold;">${workPicPathError}</span>
    <br>
    <input type="submit" value="提交"/>
</fm:form>

<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/calendar/WdatePicker.js"></script>

</body>
</html>
