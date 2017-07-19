<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addPaexit
    ge.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
  </head>
  		
  <body>
  		<form action="${pageContext.request.contextPath}/user/Blog_add.action" method="post">
    	文章标题: <input type="text" name="blogTitle"/><br/>
  		文章内容:<textarea name="blogContent" id="blogContent"></textarea>
  		<script type="text/javascript">
  			CKEDITOR.replace('blogContent', {"filebrowserUploadUrl" : "${pageContext.request.contextPath}/user/File_uploadPhoto.action"});
  		</script>
  		<input type="hidden" name="blogContentText" id="blogContentText"/>
  		<input type="hidden" name="userId" value="${sessionScope.loginUser.userId}"/>
  		<input type="submit" value="提交" id="sb" onclick="return getText();"/>
  		</form>
  		
  	
  	<script type="text/javascript">
  	
  		function getText() {
  			
  			var blogContentText = document.getElementById("blogContentText");
  			blogContentText.value = "124";
  			alert('asdfasdf');
  		
  			
  			
  			 return false;
  		}
  		
  	</script>
  	
  </body>
</html>
