package com.arex.blog.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.arex.blog.dto.BlogDTO;
import com.arex.blog.dto.CategoryDTO;
import com.arex.blog.service.BlogService;
import com.arex.blog.service.CategoryService;
import com.arex.blog.utils.LoginUtils;

@Component(value = "blogAction")
@Scope(value = "prototype")
public class BlogAction extends CommonAction<BlogDTO> {

	@Resource(name = "blogServiceImpl")
	private BlogService blogService;
	@Resource(name = "categoryServiceImpl")
	private CategoryService categoryService;
	
	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String add() {

		BlogDTO blogDTO = super.getModel();

		// 判断blogAddPage.jsp中从sessionshahi的userId是否为空
		// 如果为空则跳转到登录页面
		// 如果不为空，则将BlogDTO保存到数据库中
		if (blogDTO == null || blogDTO.getUserId() == null
				|| "".equals(blogDTO.getUserId())) {
			request.setAttribute("messageInfo", "请重新登录");
			return "signInPage";
		}

		blogService.saveBlog(blogDTO);
		List<CategoryDTO> categoryDTOList = categoryService.searchAllCategory();
		for (CategoryDTO categoryDTO : categoryDTOList) {
			int counts = blogService.searchBlogCountsByCategoryId(categoryDTO
					.getCategoryId());
			categoryDTO.setCounts(counts);
		}

		// 将categoryDTOList设置到application中
		application.setAttribute("categoryDTOList", categoryDTOList);

		return "add";
	}

	// 博客编辑
	public String edit() {

		BlogDTO blogDTO = super.getModel();

		// 判断blogDTO，userId， blogId
		if (blogDTO == null) {
			request.setAttribute("messageInfo", "未获取提交修改的数据.");
			return "toBlog";
		}
		// 判断当前用户是否登录
		// 单独定义LoginUtils类提供对用户登录情况的检查(原方式是依靠提交表单中的userId判断，如果没有添加userId，则获取为null，重复登录，现通过session判断)
		if (!LoginUtils.checkUserIsAlreadyLogin(session)) {
			request.setAttribute("messageInfo", "未登录，请登录后再编辑...");
			return "signInPage";
		}
		BlogDTO searchBlogDTO = blogService.searchBlogByBlogId(blogDTO
				.getBlogId());
		if (searchBlogDTO == null) {
			request.setAttribute("messageInfo", "当前博客不存在...");
			return "toBlog";
		}

		blogService.updateBlog(blogDTO);
		List<CategoryDTO> categoryDTOList = categoryService.searchAllCategory();
		for (CategoryDTO categoryDTO : categoryDTOList) {
			int counts = blogService.searchBlogCountsByCategoryId(categoryDTO
					.getCategoryId());
			categoryDTO.setCounts(counts);
		}

		// 将categoryDTOList设置到application中
		application.setAttribute("categoryDTOList", categoryDTOList);

		return "edit";
	}

	// 删除博客
	public String delete() {

		BlogDTO blogDTO = super.getModel();

		// 判断blogDTO，userId， blogId
		if (blogDTO == null) {
			request.setAttribute("messageInfo", "未获取提交修改的数据.");
			return "toBlog";
		}
		// 判断当前用户是否登录
		if (!LoginUtils.checkUserIsAlreadyLogin(session)) {
			request.setAttribute("messageInfo", "未登录，请登录后再编辑...");
			return "signInPage";
		}
		BlogDTO searchBlogDTO = blogService.searchBlogByBlogId(blogDTO
				.getBlogId());
		if (searchBlogDTO == null) {
			request.setAttribute("messageInfo", "当前博客不存在...");
			return "toBlog";
		}

		blogService.deleteBlogByBlogId(blogDTO);
		List<CategoryDTO> categoryDTOList = categoryService.searchAllCategory();
		for (CategoryDTO categoryDTO : categoryDTOList) {
			int counts = blogService.searchBlogCountsByCategoryId(categoryDTO
					.getCategoryId());
			categoryDTO.setCounts(counts);
		}

		// 将categoryDTOList设置到application中
		application.setAttribute("categoryDTOList", categoryDTOList);

		return "delete";
	}

	public String halfwayDelete() {

		BlogDTO blogDTO = super.getModel();

		BlogDTO searchBlogDTO = blogService.searchBlogByBlogId(blogDTO
				.getBlogId());
		if (searchBlogDTO == null) {
			request.setAttribute("messageInfo", "当前博客不存在...");
			return "toBlog";
		}

		blogService.halfwayDeleteBlog(blogDTO);
		List<CategoryDTO> categoryDTOList = categoryService.searchAllCategory();
		for (CategoryDTO categoryDTO : categoryDTOList) {
			int counts = blogService.searchBlogCountsByCategoryId(categoryDTO
					.getCategoryId());
			categoryDTO.setCounts(counts);
		}

		// 将categoryDTOList设置到application中
		application.setAttribute("categoryDTOList", categoryDTOList);

		return "halfwayDelete";
	}

	public String restore() {

		BlogDTO blogDTO = super.getModel();

		// 判断blogDTO，userId， blogId
		BlogDTO searchBlogDTO = blogService.searchBlogByBlogId(blogDTO
				.getBlogId());
		if (searchBlogDTO == null) {
			request.setAttribute("messageInfo", "当前博客不存在...");
			return "toBlog";
		}

		blogService.restoreBlog(blogDTO);
		List<CategoryDTO> categoryDTOList = categoryService.searchAllCategory();
		for (CategoryDTO categoryDTO : categoryDTOList) {
			int counts = blogService.searchBlogCountsByCategoryId(categoryDTO
					.getCategoryId());
			categoryDTO.setCounts(counts);
		}

		// 将categoryDTOList设置到application中
		application.setAttribute("categoryDTOList", categoryDTOList);
		return "restore";
	}
	
	public String changeBlogCategory() throws UnsupportedEncodingException {
		
		String blogId = super.getModel().getBlogId();
		String categoryId = super.getModel().getCategoryId();
		String result = "";
		
		BlogDTO blogDTO = blogService.searchBlogByBlogId(blogId);
		if (blogDTO == null) {
			result += "博客不存在";
		} else {
			blogDTO.setCategoryId(categoryId);
			blogService.updateBlog(blogDTO);
			result += "博客分类更新成功！";
		}
		inputStream = new ByteArrayInputStream(result.getBytes("utf-8"));
		return "changeBlogCategory";
	}

}
