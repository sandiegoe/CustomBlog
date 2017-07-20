<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <!--[if IE]>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<![endif]-->
    <title>Nice responsive template for blogger</title>
    <!-- BOOTSTRAP CORE STYLE -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" />
    <!-- FONT AWESOME ICON STYLE -->
    <link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet" />
    <!-- CUSTOM STYLE CSS -->
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
</head>
<body>
<br/>
    <div id="header">
        <div class="overlay">
            <div class="container">
                <div class="row">
                    <div class="col-md-4 logo-div">
                        <div class="logo-inner text-center">
                            <div class="logo-name">
                                <a href="index.html">
                                    <img src="${pageContext.request.contextPath}/img/me.jpg" class="img-circle" />
                                </a>
                            </div>

                        </div>

                    </div>
                    <div class="col-md-8 header-text-top " id="about">



                        <h1>Nice responsive template for blogger.</h1>
						This blogging template use bootstrap and html to create a very nice blogging page with great responsive. <br /> 
						Here you can write a general notes about your blog.<br />
						<h2><strong>Who I am ? </strong></h2>
                        <i>I am Jhon Deo </i>
						
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--END HEADER SECTION-->
    <div class="info-sec">
        <div class="container">
            <div class="row">
                <div class="col-md-10">
                    Say hello to me at <strong>hello</strong>@yourdomain.com
                    
                    
                    
                
                  <c:if test="${not empty sessionScope.loginUser}">
                    	  Hi : ${sessionScope.loginUser.userName}
                    	<a href="${pageContext.request.contextPath}/user/User_signOut.action">signOut</a>
                    	<a href="${pageContext.request.contextPath}/user/Menu_changePasswordPage.action">修改密码</a>
                    	<a href="${pageContext.request.contextPath}/user/Menu_personalPage.action">个人中心</a>
                    </c:if> 
                   
                    
                    <!-- 判断如果用户已经登录则不显示用户登录链接 -->
                    <c:if test="${empty sessionScope.loginUser}">
                    	<a href="${pageContext.request.contextPath}/user/Menu_signInPage.action">Sign</a>
                    </c:if>
                    <a href="${pageContext.request.contextPath}/user/Menu_registerPage.action">register</a>
                    <a href="${pageContext.request.contextPath}/user/Menu_photo.action">photo</a>
                    <a href="${pageContext.request.contextPath}/user/Menu_blog.action">blog</a>
                    <a href="${pageContext.request.contextPath}/user/Menu_message.action">message</a>
                    
                    
                    
                </div>
                <div class="col-md-2">
                    <div class="social-link">
                        <a href="#" class="btn btn-default btn-xs"><i class="fa fa-facebook fa-2x"></i></a>
                        <a href="#" class="btn btn-default btn-xs"><i class="fa fa-linkedin fa-2x"></i></a>
                        <a href="#" class="btn btn-default btn-xs"><i class="fa fa-google-plus fa-2x"></i></a>
                    </div>

                </div>

            </div>
        </div>
    </div>
    <div class="copyrights">Collect from <a href="http://www.cssmoban.com/"  title="网站模板">网站模板</a></div>
    <!--END INFO SECTION-->
    <div class="container">

        <div class="row">

            <div class="col-md-12 ">
              
          <form action="${pageContext.request.contextPath}/user/Blog_edit.action" method="post">
          <input type="hidden" name="blogId" value="${requestScope.blogDTO.blogId}"/>
    	文章标题: <input type="text" name="blogTitle" value="${requestScope.blogDTO.blogTitle}" style="width:742px;"/><br/>
  		文章内容:<textarea name="blogContent" id="blogContent" rows="50">${requestScope.blogDTO.blogContent}</textarea>
  		<script type="text/javascript">
  			CKEDITOR.replace('blogContent', {"filebrowserUploadUrl" : "${pageContext.request.contextPath}/user/File_uploadPhoto.action", height:"500px", width:"800px"});
  		</script>
  		<input type="hidden" name="blogContentText" id="blogContentText"/>
  		<input type="hidden" name="userId" value="${sessionScope.loginUser.userId}"/>
  		<input type="submit" value="提交" id="sb" onclick="return getText();"/>
  		</form>
                
            </div>
           

        </div>


    </div>

    <!--END HOME PAGE SECTION-->
    <div class="footer-sec" style="margin-top: 0px;">
        <div class="container">
            <div class="row">
                <div class="col-md-12 foo-inner">
                    &copy; 2015 Yourdomain.com | More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> - Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a>
                </div>
            </div>
        </div>
    </div>
    <!-- END FOOTER SECTION -->
    <!-- JAVASCRIPT FILES PLACED AT THE BOTTOM TO REDUCE THE LOADING TIME -->
    <!-- CORE JQUERY -->
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.js"></script>
    <!-- BOOTSTRAP SCRIPTS -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script type="text/javascript">
  	
  		function getText() {
  			//获取隐藏域的元素
  			var blogContentText = document.getElementById("blogContentText");
  			//获取html内容
  			var data = CKEDITOR.instances.blogContent.getData();
  			//获取纯文本内容
  			var stemTxt=CKEDITOR.instances.blogContent.document.getBody().getText();
  			//设置到隐藏域blogContentText中，提交
  			blogContentText.value = stemTxt;
  			
  			 return true;
  		}
  	</script>
</body>
</html>

