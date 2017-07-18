<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'personalPage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <h3>个人中心页面</h3><br/>
    用户Id : ${sessionScope.loginUser.userId}<br/>
    用户名 : ${sessionScope.loginUser.userName }<br/>
    用户昵称：${sessionScope.loginUser.userNickName}<br/>
    性别：${sessionScope.loginUser.sex }<br/>
    生日日期：${sessionScope.loginUser.birthdate }<br/>
    地址：${sessionScope.loginUser.address }<br/>
    联系电话：${sessionScope.loginUser.contactTel }<br/>
    邮箱：${sessionScope.loginUser.email }<br/>
    手机号：${sessionScope.loginUser.telphone }<br/>
    上次登录时间：${sessionScope.loginUser.lastLogonDate }<br/>
  </body>
</html>
