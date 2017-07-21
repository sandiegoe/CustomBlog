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
                   
                   
                    <a href="${pageContext.request.contextPath}/user/Menu_home.action">首页</a>
                    <!-- 判断如果用户已经登录则不显示用户登录链接 -->
                    <c:if test="${empty sessionScope.loginUser}">
                    	<a href="${pageContext.request.contextPath}/user/Menu_signInPage.action">登录</a>
                    </c:if>
                    <a href="${pageContext.request.contextPath}/user/Menu_registerPage.action">注册</a>
                    <a href="${pageContext.request.contextPath}/user/Menu_photo.action">我的图片</a>
                    <a href="${pageContext.request.contextPath}/user/Menu_blog.action">我的博客</a>
                    <a href="${pageContext.request.contextPath}/user/Menu_message.action">消息</a>
                    
                    
                    
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

            <div class="col-md-8 ">
            
            	  <c:forEach items="${requestScope.blogDTOList}" var="blogDTO">
	                <div class="blog-post">
	                    <h2>${blogDTO.blogTitle}</h2>
	                    <h4>Posted by <a href="#">${blogDTO.userName}</a> on ${blogDTO.blogCreateDate} </h4>
	                    <p>
	                        ${blogDTO.blogContentText}
	                    </p>
	                    <a href="${pageContext.request.contextPath}/user/Menu_blogDetailPage.action?blogId=${blogDTO.blogId}" class="btn btn-default btn-lg ">Read More <i class="fa fa-angle-right"></i></a>
	                </div>
                </c:forEach> 

                <br />
                <nav>
                    <ul class="pagination">
                        <li>
                            <a href="#" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <li><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li>
                            <a href="#" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
            <div class="col-md-1"></div>
            <div class="col-md-3" style="padding-top: 30px;">
				<div class="row">
                <ul class="list-group">
                    <li class="list-group-item"><strong>CATEGORIES</strong></li>
                    <li class="list-group-item">Dapibus ac facilisis in</li>
                    <li class="list-group-item">Morbi leo risus</li>
                    <li class="list-group-item">Porta ac consectetur ac</li>
                    <li class="list-group-item">Vestibulum at eros</li>
                    <li class="list-group-item">Dapibus ac facilisis in</li>
                    <li class="list-group-item">Morbi leo risus</li>
                    <li class="list-group-item">Porta ac consectetur ac</li>
                    <li class="list-group-item">Vestibulum at eros</li>
                </ul>
				</div>
				<div class="row">
				<h3>Advertising</h3>
				

				</div>
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

</body>
</html>
