package com.arex.blog.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.arex.blog.dto.MenuDTO;
import com.arex.blog.dto.UserDTO;
import com.arex.blog.service.UserService;
import com.arex.blog.service.impl.UserServiceImpl;

@Component(value="menuAction")
@Scope(value="prototype")
public class MenuAction extends CommonAction<MenuDTO> {

	@Resource(name="userServiceImpl")
	private UserService userService;
	

	public String home() {
		
		return "home";
	}
	
	public String signInPage() {
		return "signInPage";
	}
	
	public String registerPage() {
		return "registerPage";
	}
	
	public String photo() {
		return "photo";
	}
	
	public String blog() {
		return "blog";
	}
	
	public String message() {
		return "message";
	}
	
	public String changePasswordPage() {
		return "changePasswordPage";
	}
	
	public String personalPage() {
		return "personalPage";
	}
	
	/*
	 * 跳转到添加博客页面
	 * 需要判断是否登录，如果没有登录，则跳转到用户登录页面，让用户选择登录，如果已经登录，则直接跳转到博客添加页面
	 */
	public String blogAddPage() {
		//判断用户是否已经登录
		boolean isLogin = checkUserIsAlreadyLogin(session);
		
		if (!isLogin) {
			//没有登录，跳转到signInPage.jsp
			return "signInPage";
		} 
		
		return "blogAddPage";
	}
	
	public static boolean checkUserIsAlreadyLogin(HttpSession session) {
		
		UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
		if (loginUser == null) {
			return false;
		} else {
			return true;
		}
	}
}
