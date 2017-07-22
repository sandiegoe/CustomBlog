package com.arex.blog.action;

import javax.annotation.Resource;

import org.eclipse.jdt.internal.compiler.ast.SuperReference;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.arex.blog.dao.BlogDAO;
import com.arex.blog.dto.BlogDTO;
import com.arex.blog.service.BlogService;
import com.arex.blog.utils.LoginUtils;

@Component(value="blogAction")
@Scope(value="prototype")
public class BlogAction extends CommonAction<BlogDTO> {

	@Resource(name="blogServiceImpl")
	private BlogService blogService;
	
	public String add() {
		
		//判断blogAddPage.jsp中从sessionshahi的userId是否为空
		//如果为空则跳转到登录页面
		//如果不为空，则将BlogDTO保存到数据库中
System.out.println(super.getModel().getBlogTitle());
		if (super.getModel()==null || super.getModel().getUserId()==null || "".equals(super.getModel().getUserId())) {
			request.setAttribute("messageInfo", "请重新登录");
			return "signInPage";
		}
		
		blogService.saveBlog(super.getModel());
		
		return "add";
	}
	
	//博客编辑
	public String edit() {
		
		BlogDTO blogDTO = super.getModel();
		
		//判断blogDTO，userId， blogId
		if (blogDTO==null) {
			request.setAttribute("messageInfo", "未获取提交修改的数据.");
			return "toBlog";
		}
		//判断当前用户是否登录
		//单独定义LoginUtils类提供对用户登录情况的检查(原方式是依靠提交表单中的userId判断，如果没有添加userId，则获取为null，重复登录，现通过session判断)
		if (!LoginUtils.checkUserIsAlreadyLogin(session)) {
			request.setAttribute("messageInfo", "未登录，请登录后再编辑...");
			return "signInPage";
		}
		BlogDTO searchBlogDTO = blogService.searchBlogByBlogId(blogDTO.getBlogId());
		if (searchBlogDTO == null) {
			request.setAttribute("messageInfo", "当前博客不存在...");
			return "toBlog";
		}
		
		blogService.updateBlog(blogDTO);
		
		return "edit";
	}
	
	//删除博客
	public String delete() {
		
		BlogDTO blogDTO = super.getModel();
		
		//判断blogDTO，userId， blogId
		if (blogDTO == null) {
			request.setAttribute("messageInfo", "未获取提交修改的数据.");
			return "toBlog";
		}
		//判断当前用户是否登录
		if (!LoginUtils.checkUserIsAlreadyLogin(session)) {
			request.setAttribute("messageInfo", "未登录，请登录后再编辑...");
			return "signInPage";
		}
		BlogDTO searchBlogDTO = blogService.searchBlogByBlogId(blogDTO.getBlogId());
		if (searchBlogDTO == null) {
			request.setAttribute("messageInfo", "当前博客不存在...");
			return "toBlog";
		}
		
		blogService.deleteBlogByBlogId(blogDTO);
		
		return "delete";
	}
	
public String halfwayDelete() {
		
		BlogDTO blogDTO = super.getModel();
		
		//判断blogDTO，userId， blogId
		if (blogDTO == null) {
			request.setAttribute("messageInfo", "未获取提交修改的数据.");
			return "toBlog";
		}
		//判断当前用户是否登录
		if (!LoginUtils.checkUserIsAlreadyLogin(session)) {
			request.setAttribute("messageInfo", "未登录，请登录后再编辑...");
			return "signInPage";
		}
		BlogDTO searchBlogDTO = blogService.searchBlogByBlogId(blogDTO.getBlogId());
		if (searchBlogDTO == null) {
			request.setAttribute("messageInfo", "当前博客不存在...");
			return "toBlog";
		}
		
		blogService.halfwayDeleteBlog(blogDTO);
		
		return "halfwayDelete";
	}
}
