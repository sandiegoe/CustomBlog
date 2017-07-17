package com.arex.blog.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.arex.blog.dto.MenuDTO;
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
}
