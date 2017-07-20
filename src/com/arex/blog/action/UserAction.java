package com.arex.blog.action;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.arex.blog.dto.LoginDTO;
import com.arex.blog.dto.UserDTO;
import com.arex.blog.service.LoginService;
import com.arex.blog.service.UserService;

@Component(value="userAction")
@Scope(value="prototype")
public class UserAction extends CommonAction<UserDTO> {

	@Resource(name="userServiceImpl")
	private UserService userService;
	@Resource(name="loginServiceImpl")
	private LoginService loginService;
	
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
		
		//lastLogonDate不用了
		//用Login表记录用户登录情况
		//更新上次登录时间
		// 设置新的lastLogonDate时间
		//userService.setNewLastLogonDate(super.getModel());
		
		//添加一条用户登录记录
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setLoginDate(new Date());
		loginDTO.setUserId(userDTO.getUserId());
		loginDTO.setLoginIp(request.getRemoteAddr());
		loginService.addLoginRecord(loginDTO);
			
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
			session.invalidate();
		}
		
		return "signOut";
	}
		
	public String changePassword() {
		UserDTO userDTO = super.getModel();
		//检查密码
		if(userDTO==null || userDTO.getOlderPassword()==null || "".equals(userDTO.getOlderPassword())) {
			request.setAttribute("messageInfo", "原密码不能为空.");
			return "changePasswordError";
		}
		if (userDTO.getLogonPassword()==null || "".equals(userDTO.getLogonPassword())) {
			request.setAttribute("messageInfo", "新密码不能为空.");
			return "changePasswordError";
		}
		if (userDTO.getConfimPassword()==null || "".equals(userDTO.getConfimPassword())) {
			request.setAttribute("messageInfo", "重复密码不能为空.");
			return "changePasswordError";
		}
		if (!userDTO.getLogonPassword().equals(userDTO.getConfimPassword())) {
			request.setAttribute("messageInfo", "两次输入密码不一致");
			return "changePasswordError";
		}
		
		//获取当前用户的密码
		UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
		if (userDTO == null) {
			request.setAttribute("messageInfo", "请登录....");
			return "changePasswordError";
		}
		loginUser = userService.searchUserByUserName(loginUser);
		session.setAttribute("loginUser", loginUser);
		if (!userDTO.getOlderPassword().equals(loginUser.getLogonPassword())) {
			request.setAttribute("messageInfo", "原密码输入错误.");
			return "changePasswordError";
		}
		
		//更新密码
		userService.changePassword(userDTO);
		
		return "changePassword";
	}
}
