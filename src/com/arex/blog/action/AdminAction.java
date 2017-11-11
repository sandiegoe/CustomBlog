package com.arex.blog.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.arex.blog.dto.BlogDTO;
import com.arex.blog.dto.CategoryDTO;
import com.arex.blog.service.BlogService;
import com.arex.blog.service.CategoryService;

@Component(value="adminAction")
@Scope(value = "prototype")
public class AdminAction extends CommonAction<CategoryDTO> {

	@Resource(name = "categoryServiceImpl")
	private CategoryService categoryService;
	@Resource(name="blogServiceImpl")
	private BlogService blogService;

	public String categoryManagerPage() {

		List<BlogDTO> blogDTOList = blogService.searchAllBlog();
		List<CategoryDTO> categoryDTOList = categoryService.searchAllCategory();
		request.setAttribute("blogDTOList", blogDTOList);
		request.setAttribute("categoryDTOList", categoryDTOList);
		
		return "categoryManagerPage";
	}
}
