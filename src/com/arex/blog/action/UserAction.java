package com.arex.blog.action;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.arex.blog.dto.UserDTO;
import com.arex.blog.service.UserService;

@Component(value="userAction")
@Scope(value="prototype")
public class UserAction extends CommonAction<UserDTO> {

	@Resource(name="userServiceImpl")
	private UserService userService;
	
	public String signIn() {
		
		
		String userName = super.getModel().getUserName();
		String logonPassword = super.getModel().getLogonPassword();
		UserDTO userDTO = userService.searchUserByUserName(super.getModel());
		
		//判断
		if (userDTO == null) {
			request.setAttribute("messageInfo", "用户名输入错误，没有该用户.");
			return "error";
		}
		if (logonPassword==null || "".equals(logonPassword)) {
			request.setAttribute("messageInfo", "请输入密码.");
			return "error";
		}
		if (!logonPassword.equals(userDTO.getLogonPassword())) {
			request.setAttribute("messageInfo", "密码输入错误.");
			return "error";
		}
		
	
		//将userDTO保存到session中
		session.setAttribute("loginUser", userDTO);
			
		//记住密码部分逻辑
		//生成用户名cookie和密码cookie
		Cookie userNameCookie = new Cookie("userName", userDTO.getUserName());
		Cookie logonPasswordCookie = new Cookie("logonPassword", userDTO.getLogonPassword());
		//甚至Cookie的作用域
		userNameCookie.setPath(((HttpServletRequest)request).getContextPath() + "/");
		logonPasswordCookie.setPath(((HttpServletRequest)request).getContextPath() + "/");
		String remember = super.getModel().getRemember();
		if (remember!=null && "on".equals(remember)) {
			//设置有效期为一周
			userNameCookie.setMaxAge(7*24*60*60*1000);
			logonPasswordCookie.setMaxAge(7*24*60*60*1000);
		} else {
			//remember没有勾选，有效期为0
			userNameCookie.setMaxAge(00);
			logonPasswordCookie.setMaxAge(0);
		}
		//保存cookie
		((HttpServletResponse)response).addCookie(userNameCookie);
		((HttpServletResponse)response).addCookie(logonPasswordCookie);
		
		return "signIn";
	}
	
	public String register() {
		userService.saveUserDTO(super.getModel());
		return "register";
	}
	
	public String signOut() {
		
		UserDTO userDTO = (UserDTO) session.getAttribute("loginUser");
		if (userDTO != null) {
			session.removeAttribute("loginUser");
		}
		
		return "signOut";
	}
}
