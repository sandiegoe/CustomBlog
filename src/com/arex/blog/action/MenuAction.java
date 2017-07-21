package com.arex.blog.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.arex.blog.dto.BlogDTO;
import com.arex.blog.dto.MenuDTO;
import com.arex.blog.dto.PhotoDTO;
import com.arex.blog.dto.UserDTO;
import com.arex.blog.service.BlogService;
import com.arex.blog.service.LoginService;
import com.arex.blog.service.PhotoService;
import com.arex.blog.service.UserService;
import com.arex.blog.utils.LoginUtils;

@Component(value = "menuAction")
@Scope(value = "prototype")
public class MenuAction extends CommonAction<MenuDTO> {

	@Resource(name = "userServiceImpl")
	private UserService userService;
	@Resource(name = "loginServiceImpl")
	private LoginService loginService;
	@Resource(name = "blogServiceImpl")
	private BlogService blogService;
	@Resource(name="photoServiceImpl")
	private PhotoService photoService;

	public String home() {

		List<BlogDTO> blogDTOList = blogService.searchAllBlog();
		request.setAttribute("blogDTOList", blogDTOList);

		return "home";
	}

	public String signInPage() {
		return "signInPage";
	}

	public String registerPage() {
		return "registerPage";
	}

	//判断是否已经登录
	//如果登录，显示个人照片
	//如果没有登录，则跳到登录页面
	public String photo() {
		
		boolean isLogin = LoginUtils.checkUserIsAlreadyLogin(session);
		if (!isLogin) {
			request.setAttribute("message", "请登录.");
			return "signInPage";
		}
		
		//查询当前用户所有图片
		List<PhotoDTO> photoDTOList = photoService.searchAllPhotoByUserId(((UserDTO)session.getAttribute("loginUser")).getUserId());
		//设置到request中去
		request.setAttribute("photoDTOList", photoDTOList);
		
		return "photo";
	}

	// 查询所有用户的博客
	// 跳转到博客主页面
	public String blog() {
		
		List<BlogDTO> blogDTOList = null;

		//判断是否登录
		boolean isLogin = LoginUtils.checkUserIsAlreadyLogin(session);
		if (isLogin) {
			//已经登录，显示个人博客列表
			blogDTOList = blogService.searchAllBlogByUserId(((UserDTO)session.getAttribute("loginUser")).getUserId());
		} else {
			//没有登录，显示所有博客列表
			blogDTOList = blogService.searchAllBlog();
		}
		
		//将blogDTOList设置到request中
		request.setAttribute("blogDTOList", blogDTOList);

		return "blog";
	}

	public String message() {
		return "message";
	}

	public String changePasswordPage() {

		return "changePasswordPage";
	}

	// 个人中心
	// 添加上次登录时间
	public String personalPage() {

		// 判断用户是否已经登录
		boolean isLogin = LoginUtils.checkUserIsAlreadyLogin(session);
		if (!isLogin) {
			// 没有登录，跳转到signInPage.jsp
			return "signInPage";
		}
		UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
		Date lastLoginDate = loginService.searchLastLoginDateByUserId(loginUser
				.getUserId());
		request.setAttribute("lastLoginDate", lastLoginDate);
		return "personalPage";
	}

	/*
	 * 跳转到添加博客页面 需要判断是否登录，如果没有登录，则跳转到用户登录页面，让用户选择登录，如果已经登录，则直接跳转到博客添加页面
	 */
	public String blogAddPage() {
		// 判断用户是否已经登录
		boolean isLogin = LoginUtils.checkUserIsAlreadyLogin(session);

		if (!isLogin) {
			// 没有登录，跳转到signInPage.jsp
			return "signInPage";
		}

		return "blogAddPage";
	}

	public String blogEditPage() {

		// 获取列表页面传递的blogId
		String blogId = super.getModel().getBlogId();

		// 调用blogService查询指定blogId
		// 如果指定的blogId不存在，跳转到列表页面
		if (blogId == null || "".equals(blogId)) {
			request.setAttribute("messageInfo", "当前Blog不存在");
			return "toBlog";
		}
		BlogDTO blogDTO = blogService.searchBlogByBlogId(blogId);
		// 判断查询的blog是否存在
		if (blogDTO == null) {
			request.setAttribute("messageInfo", "当前Blog不存在");
			return "toBlog";
		}

		// 设置到request中"blogDTO" : blogDTO
		request.setAttribute("blogDTO", blogDTO);

		return "blogEditPage";
	}
	
	public String blogDetailPage() {
		
		//获取列表页面传递的blogId
		String blogId = super.getModel().getBlogId();
		
		//调用blogService查询指定blogId
		//如果指定的blogId不存在，跳转到列表页面
		if (blogId==null || "".equals(blogId)) {
			request.setAttribute("messageInfo", "当前Blog不存在");
			return "toBlog";
		}
		BlogDTO blogDTO = blogService.searchBlogByBlogId(blogId);
		//判断查询的blog是否存在
		if (blogDTO == null) {
			request.setAttribute("messageInfo", "当前Blog不存在");
			return "toBlog";
		}
		
		//设置到request中"blogDTO" : blogDTO
		request.setAttribute("blogDTO", blogDTO);
		
		return "blogDetailPage";
	}
	
	public String uploadPhotoPage() {
		return "uploadPhotoPage";
	}
}
