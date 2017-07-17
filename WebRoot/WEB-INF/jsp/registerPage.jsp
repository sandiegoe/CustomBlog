<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'registerPage.jsp' starting page</title>
    
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
    <form action="${pageContext.request.contextPath}/user/user_register.action" method="post">
    	用户名:<input type="text" name="userName"/><br/>
    	密码: <input type="password" name="logonPassword"/><br/>
    	重复密码: <input type="password" name="confimPassword"/><br/>
    	昵称:<input type="text" name="userNickName"/><br/>
    	生日日期:<input type="text" name="birthdate"/><br/>
    	性别 ： <input type="text" name="sex"/><br/>
    	地址: <input type="text" name="address"/><br/>
    	联系电话: <input type="text" name="contactTel"/><br/>
    	邮箱 : <input type="text" name="email"/><br/>
    	手机号 : <input type="text" name="telphone"/><br/>
    	
    	<input type="submit" value="注册"/>
    </form>
  </body>
</html>
