package com.arex.blog.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.arex.blog.dto.BlogDTO;
import com.arex.blog.service.BlogService;

@Component(value="blogAction")
@Scope(value="prototype")
public class BlogAction extends CommonAction<BlogDTO> {

	@Resource(name="blogServiceImpl")
	private BlogService blogService;
	
	public String add() {
		
		//判断blogAddPage.jsp中从sessionshahi的userId是否为空
		//如果为空则跳转到登录页面
		//如果不为空，则将BlogDTO保存到数据库中
		if (super.getModel()==null || super.getModel().getUserId()==null || "".equals(super.getModel().getUserId())) {
			request.setAttribute("messageInfo", "请重新登录");
			return "signInPage";
		}
		
		blogService.saveBlog(super.getModel());
		
		return "add";
	}
	
	public String detailPage() {
		
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
		
		return "detailPage";
	}
}
