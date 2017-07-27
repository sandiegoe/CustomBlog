<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
    
     <script type="text/javascript">
    
    	function check(temp) {
    		var sex = document.getElementsByName("sex")[0];
    		//alert(sex.value == '女');
    		if (sex.value == '男') {
    			sex.value = 0;
    			//alert(document.getElementsByName("sex")[0].value);
    			return true;
    		}
			 if (sex.value == '女'){
				 sex.value = 1;
    		//	alert(document.getElementsByName("sex")[0].value);
    			return true;
    		} else {
    			sex.focus();
    			alert("请填入正确的性别.");
    			return false;
    		}
    	}
    
    /* 	function changeAvatar() {
    		var avatarContainer = document.getElementById('changeAvatarContainer');
    		
    		
    		var script ="<s:form action='user/Photo_upload.action' theme='simple' enctype='multipart/form-data'>选择图片<s:file name='file'></s:file><s:submit value='取消'></s:submit>" + 
    		"<s:submit value='上传'></s:submit></s:form>";
    		
    		
    		avatarContainer.innerHTML = script;
    	} */
    </script>
    
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
						为您提供始终如一最完美的体验是我们不变的追求.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--- 
						designed by arex.<br />
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
              
          <h3>个人中心页面</h3><br/>
    <form action="${pageContext.request.contextPath}/user/User_edit.action" method="post">    
    
     <table>
    	<tr>	
    		<td> 用户Id :</td>
    		<td><input type="text" name="userId" readonly value="${sessionScope.loginUser.userId}"/><br/></td>
    	</tr>
    	<tr>
    		<td>头像：</td>
    		<td>
    			<div class="logo-name">
		          	<a href="${pageContext.request.contextPath}/user/Menu_personalPage.action">
		            	<img alt="个人头像" src="${sessionScope.loginUser.avatarURL}" class="img-circle"/>
		            </a>
     			</div>
     			<c:if test="${requestScope.kind==''}">
    				<!-- <a href="#" onclick="return changeAvatar();">修改头像</a> -->
    				<a href="${pageContext.request.contextPath}/user/Menu_changeAatarPage.action">修改头像</a>
    				<div class="changeAvatar" id="changeAvatarContainer"></div>
    			</c:if>
     		</td>
    	</tr>
    	<tr>
    		<td> 用户名 : </td>
    		<td> <input type="text" name="userName" readonly value="${sessionScope.loginUser.userName }"/></td>
    	</tr>
    	<tr>
    		<td>用户昵称 : </td>
    		<td><input type="text" name="userNickName" ${requestScope.kind} value="${sessionScope.loginUser.userNickName}"/></td>
    	</tr>
    	<tr>
    		<td>性别：</td>
    		<td><input type="text" name="sex" ${requestScope.kind} value="${sessionScope.loginUser.sex==0 ? '男' : '女'}"/></td>
    	</tr>
    	<tr>
    		<td>生日日期：</td>
    		<td><input type="text" name="birthdate" ${requestScope.kind} value="${sessionScope.loginUser.birthdate }"/></td>
    	</tr>
    	<tr>
    		<td>地址：</td>
    		<td> <input type="text" name="address" ${requestScope.kind} value="${sessionScope.loginUser.address }"/></td>
    	</tr>
    	<tr>
    		<td> 联系电话：</td>
    		<td><input type="text" name="contactTel" ${requestScope.kind} value="${sessionScope.loginUser.contactTel }"/></td>
    	</tr>
    	<tr>
    		<td>邮箱：</td>
    		<td><input type="text" name="email" ${requestScope.kind} value="${sessionScope.loginUser.email }"/></td>
    	</tr>
    	<tr>
    		<td> 手机号：</td>
    		<td><input type="text" name="telphone" ${requestScope.kind} value="${sessionScope.loginUser.telphone }"/></td>
    	</tr>
    	<tr>
    		<td>上次登录时间：</td>
    		<td><input type="text" name="lastLoginDate" disabled value="${requestScope.lastLoginDate}"/></td>
    	</tr>
    	<tr style="text-align:center;">
    		 <!-- ${requestScope.kind==''} 表示启用编辑 -->
    		 <c:if test="${requestScope.kind==''}">
	    		<td>
	    			<input type="reset" value="取消"/>
	    		</td>
	    		<td>
	    			<input type="submit" value="确认修改" onclick="return check(this.form);"/>
	    		</td>
    		</c:if>
    	</tr>
    </table>
     </form>
  
                
            </div>
            <c:if test="${requestScope.kind=='disabled'}">
            	<div class="col-md-1"><a href="${pageContext.request.contextPath}/user/Menu_personalPage.action?kind=edit">启用编辑</a></div>
            </c:if>
             <c:if test="${requestScope.kind==''}">
    			<a href="${pageContext.request.contextPath}/user/Menu_changePasswordPage.action">修改密码</a> 
    			<a href="${pageContext.request.contextPath}/user/User_halfwayDelete.action?userId=${sessionScope.loginUser.userId}" onclick="return confirm('确认删除.');">删除用户</a>
    		</c:if>
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
    
</body>
</html>
