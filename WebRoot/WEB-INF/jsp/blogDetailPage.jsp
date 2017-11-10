<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <link href="${pageContext.request.contextPath}/img/favicon.ico" rel="shortcut icon" type="image/vnd.microsoft.icon"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <!--[if IE]>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<![endif]-->
    <title>Blog</title>
    <!-- BOOTSTRAP CORE STYLE -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" />
    <!-- FONT AWESOME ICON STYLE -->
    <link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet" />
    <!-- CUSTOM STYLE CSS -->
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" />
    <!-- 自定义样式部分 -->
   	<link href="${pageContext.request.contextPath}/css/mystyle.css" rel="stylesheet"/>
</head>
<body>
    <div id="header">
        <div class="overlay">
            <div class="container">
                <div class="row">
                    <div class="col-md-4 logo-div">
                        <div class="logo-inner text-center">
                            <div class="logo-name">
                              <!-- 判断如果用户没有登录则不显示用户头像 -->
			                	<c:if test="${not empty sessionScope.loginUser}">
			                    	 <a href="${pageContext.request.contextPath}/user/Menu_personalPage.action">
                                    	<img alt="个人头像" src="${sessionScope.loginUser.avatarURL}" class="img-circle"/>
                                	</a>
			                    </c:if>
                            </div>

                        </div>

                    </div>
                   <div class="col-md-8 header-text-top " id="about">
                        <h1>追求极致.</h1>
						为您提供始终如一最完美的体验是我们不变的追求.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
                    <div class="menu">欢迎 <strong>${sessionScope.loginUser.userName}</strong>
	                	<a href="${pageContext.request.contextPath}/user/Menu_home.action">首页</a>
	                	<a href="${pageContext.request.contextPath}/user/Menu_photo.action">我的图片</a>
	                	<a href="${pageContext.request.contextPath}/user/Menu_blog.action">我的博客</a>
	                	<a href="${pageContext.request.contextPath}/user/Menu_message.action">消息</a>
	                    <c:if test="${not empty sessionScope.loginUser}">
	                    	<a href="${pageContext.request.contextPath}/user/Menu_personalPage.action">个人中心</a>
	                    	<a href="${pageContext.request.contextPath}/user/User_signOut.action">退出</a>
	                    </c:if> 
	                    <!-- 判断如果用户已经登录则不显示用户登录链接 -->
	                	<c:if test="${empty sessionScope.loginUser}">
	                    	<a href="${pageContext.request.contextPath}/user/Menu_signInPage.action">登录</a>
	                    </c:if>
	                    <a href="${pageContext.request.contextPath}/user/Menu_registerPage.action">注册</a>
	                </div>
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
    <!--END INFO SECTION-->
    <div class="container">

        <div class="row">


            <div class="col-md-8 ">
                <div class="blog-post">
                    <h2>${requestScope.blogDTO.blogTitle}</h2>
                     <h4><a href="#">${blogDTO.userName}</a> &nbsp;发表于&nbsp; ${blogDTO.blogCreateDate} </h4>
                    <p>
						${requestScope.blogDTO.blogContent}
                    </p>
                </div>
                <br/>
               <h4></h4> 
                <div class="wrapper">
                	
                	<!-- 负责遍历每一条评论 -->
					 <c:forEach items="${requestScope.commentDTOListWithLevel}" var="commentDTOList">	
					 	
					 	<!-- 展现每一条的评论 -->
					 	<c:forEach items="${commentDTOList}" var="commentDTO" varStatus="status">
							<div class="ds-post-main" style="padding-left:${status.index*50}px;">
									<div class="ds-avatar">
										<a title="设计达人" href="http://www.shejidaren.com" target="_blank"><img alt="设计达人" src="http://tp3.sinaimg.cn/1750584642/50/5612846168/1"></a>
									</div>
									<div class="ds-comment-body">
										<a title="设计达人" href="http://www.shejidaren.com" target="_blank" class="user-name">${commentDTO.userName}</a>
										<p>回复${commentDTO.parentName}<c:if test="${empty commentDTO.parentName}">${blogDTO.userName}</c:if> : 
										${commentDTO.commentContent}</p>
										<!-- 在最后一条消息上添加回复，删除等 -->
										<c:if test="${status.index == (fn:length(commentDTOList)-1) && sessionScope.loginUser!=null}"> 
											<a class="cmt_btn reply" href="#reply" title="回复" onclick="reply('${commentDTO.commentId}');">[回复]</a>
											<!-- 判断消息发出者是否是当前登录用户 -->
											<c:if test="${commentDTO.userId==sessionScope.loginUser.userId}">
												<a class="cmt_btn reply" href="${pageContext.request.contextPath}/user/Comment_delete?commentId=${commentDTO.commentId}&blogId=${requestScope.blogDTO.blogId}" title="删除" onclick="confirm('确认删除!')">[删除]</a>
											</c:if>
										</c:if>
									</div>
							</div>
						</c:forEach>
						<hr/>
			 		</c:forEach> 			                
                </div>
                
                
                <c:if test="${not empty sessionScope.loginUser}">
	                <form action="${pageContext.request.contextPath}/user/Comment_add.action" method="post">
	                	用户名 : ${sessionScope.loginUser.userName}<br/>
	                	<input type="hidden" name="userId" value="${sessionScope.loginUser.userId}"/>
	                	<input type="hidden" name="blogId" value="${requestScope.blogDTO.blogId}"/>
	                	<input id="parentId" type="hidden" name="parentId" value=""/>
	                	评论内容 : <textarea id="commentContent" rows="7" cols="40" name="commentContent"></textarea><br/>
	                	<input type="submit" value="提交"/>
	                </form>
                </c:if>
            </div>
            <div class="col-md-1"></div>
            <div class="col-md-3" style="padding-top: 30px;">
				<div class="row">
                <ul class="list-group">
                    <li class="list-group-item"><strong>CATEGORIES</strong></li>
                    <c:forEach items="${categoryDTOList}" var="categoryDTO">
                        <li class="list-group-item"><a href="user/Category_searchBlog?categoryId=${categoryDTO.categoryId}" style="color:#555; text-decoration: none">${categoryDTO.categoryContent}</a> <span style="color: red">${categoryDTO.counts}</span></li>
                    </c:forEach>
                </ul>
                </div>
				<div class="row">
				<h3></h3>
				

				</div>
            </div>

        </div>


    </div>

    <!--END HOME PAGE SECTION-->
    <div class="footer-sec" style="margin-top: 0px;">
        <div class="container">
            <div class="row">
                <div class="col-md-12 foo-inner" style="text-align:center;font-size:18px;">
                    &copy;&nbsp;&nbsp;CopyRight 2017-2018 arex.com,&nbsp;&nbsp;Inc.All Rights Reserved. 
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
		function reply(commentId) {
			//alert("commentId : " + commentId);
			var parentInput = document.getElementById("parentId");
			//alert("parentInput : " + parentInput.value);
			parentInput.value = commentId;
			//alert(parentInput.value);
			var commentContentInput = document.getElementById("commentContent");
			//alert(commentContentInput.innerHTML);
			commentContentInput.innerHTML = commentId + "\n";
			//alert(document.getElementById("commentContent").innerHTML);
		}
	</script>
	
</body>
</html>
