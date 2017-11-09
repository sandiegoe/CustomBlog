package com.arex.blog.service;

import java.util.List;

import com.arex.blog.dto.CategoryDTO;

public interface CategoryService {

	public void saveCategory(CategoryDTO categoryDTO);
	public List<CategoryDTO> searchAllCategory();
	public CategoryDTO searchCategoryByCategoryContent(String categoryContent);
	public CategoryDTO searchCategoryByCategoryId(String categoryId);
	public void updateCategory(CategoryDTO categoryDTO);
	public void deleteCategory(String categoryId);
}
