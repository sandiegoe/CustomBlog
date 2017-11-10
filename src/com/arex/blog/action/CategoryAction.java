package com.arex.blog.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.arex.blog.dto.CategoryDTO;
import com.arex.blog.service.BlogService;
import com.arex.blog.service.CategoryService;

@Component(value = "categoryAction")
@Scope(value = "prototype")
public class CategoryAction extends CommonAction<CategoryDTO> {

	@Resource(name = "categoryServiceImpl")
	private CategoryService categoryService;
	@Resource(name="blogServiceImpl")
	private BlogService blogService;

	// 添加种类
	public String add() {

		if (super.getModel().getCategoryContent() != null
				&& !"".equals(super.getModel().getCategoryContent())) {
			CategoryDTO categoryDTO = categoryService
					.searchCategoryByCategoryContent(super.getModel()
							.getCategoryContent());
			if (categoryDTO != null) {
				return "add";
			}
		}
		categoryService.saveCategory(super.getModel());
		List<CategoryDTO> categoryDTOList = categoryService.searchAllCategory();
		for (CategoryDTO categoryDTO : categoryDTOList) {
			int counts = blogService.searchBlogCountsByCategoryId(categoryDTO.getCategoryId());
			categoryDTO.setCounts(counts);
		}

		// 将categoryDTOList设置到application中
		application.setAttribute("categoryDTOList", categoryDTOList);

		return "add";
	}

	public String delete() {

		List<CategoryDTO> categoryDTOList = categoryService.searchAllCategory();

		// 将categoryDTOList设置到application中
		application.setAttribute("categoryDTOList", categoryDTOList);
		return "";
	}

}
