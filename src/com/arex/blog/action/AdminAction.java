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
		for (CategoryDTO categoryDTO : categoryDTOList) {
			int counts = blogService.searchBlogCountsByCategoryId(categoryDTO.getCategoryId());
			categoryDTO.setCounts(counts);
		}
		// 将categoryDTOList设置到application中
		application.setAttribute("categoryDTOList", categoryDTOList);
		request.setAttribute("blogDTOList", blogDTOList);
		
		
		return "categoryManagerPage";
	}
	
	public String home() {
		
		return "home";
	}
	
	public String categoryPage() {
		
		List<CategoryDTO> categoryDTOList = categoryService.searchAllCategory();
		request.setAttribute("categoryDTOList", categoryDTOList);
		
		return "categoryPage";
	}
	
	public String categoryAddPage() {
		
		return "categoryAddPage";
	}
	
	public String categoryUpdatePage() {
		CategoryDTO categoryDTO = super.getModel();
		CategoryDTO searchCategoryDTO = categoryService.searchCategoryByCategoryId(categoryDTO.getCategoryId());
		request.setAttribute("categoryDTO", searchCategoryDTO);
		
		return "categoryUpdatePage";
	}
	
	public String categoryAdd() {
		
		CategoryDTO categoryDTO = super.getModel();
		categoryDTO.setCounts(0);
		categoryService.saveCategory(categoryDTO);
		
		return categoryPage();
	}
	
	public String categoryUpdate() {
	
		CategoryDTO categoryDTO = super.getModel();
		categoryService.updateCategory(categoryDTO);
		
		return categoryPage();
	}
	
	public String categoryDel() {
		
		CategoryDTO categoryDTO = super.getModel();
		categoryService.deleteCategory(categoryDTO.getCategoryId());
		
		return categoryPage();
	}
}
