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
    
    <title>My JSP 'signInPage.jsp' starting page</title>
    
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
    <form action="${pageContext.request.contextPath}/user/user_signIn.action" method="post" onsubmit="return check(this)">
    	${requestScope.messageInfo} <br/>
    	 用户名: <input type="text" name="userName" value="${cookie.userName.value}"/><br/>
    	 密码: <input type="password" name="logonPassword" value="${cookie.logonPassword.value}"/><br/>
    	 记住密码:<input type="checkbox" name="remember" checked="checkec"/>
    	 <input type="submit" value="登录"/>
    </form>
    
    <script type="text/javacript">
		function check(obj) {
			
		}
	</script>
    
  </body>
</html>
