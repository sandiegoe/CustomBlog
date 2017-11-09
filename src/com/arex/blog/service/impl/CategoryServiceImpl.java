package com.arex.blog.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.arex.blog.dao.CategoryDAO;
import com.arex.blog.dto.CategoryDTO;
import com.arex.blog.model.Category;
import com.arex.blog.service.CategoryService;

@Component
public class CategoryServiceImpl implements CategoryService {
	
	@Resource(name="categoryDAOImpl")
	private CategoryDAO categoryDAO;

	@Override
	public void saveCategory(CategoryDTO categoryDTO) {
		Category category = this.convertCategoryVO2PO(categoryDTO);
		categoryDAO.save(category);
	}

	private Category convertCategoryVO2PO(CategoryDTO categoryDTO) {
		Category category = null;
		if (categoryDTO != null) {
			category = new Category();
			category.setCategoryContent(categoryDTO.getCategoryContent());
		}
		
		return category;
	}

	@Override
	public List<CategoryDTO> searchAllCategory() {
		String hqlWhere = " where 1=1 ";
		List<Object> paramList = new ArrayList<Object>();

		Object[] objects = null;
		LinkedHashMap<String, String> orderby = null;


		List<Category> categoryList = categoryDAO.searchCollectionByConditionNoPage(
				hqlWhere, objects, orderby);
		List<CategoryDTO> categoryDTOList = this.convertCategoryPO2VOList(categoryList);

		return categoryDTOList;
	}

	private List<CategoryDTO> convertCategoryPO2VOList(List<Category> categoryList) {
		List<CategoryDTO> categoryDTOList = new ArrayList<CategoryDTO>();
		for (int i=0; categoryList!=null && i<categoryList.size(); ++i) {
			Category category = categoryList.get(i);
			CategoryDTO categoryDTO = new CategoryDTO();
			categoryDTO.setCategoryId(category.getCategoryId());
			categoryDTO.setCategoryContent(category.getCategoryContent());
			categoryDTOList.add(categoryDTO);
		}
		return categoryDTOList;
	}

	@Override
	public CategoryDTO searchCategoryByCategoryId(String categoryId) {
		String hqlWhere = " where 1=1  ";
		List<Object> paramList = new ArrayList<Object>();
		if (categoryId != null && !"".equals(categoryId)) {
			hqlWhere += " and o.categoryId = ? ";
			paramList.add(categoryId);
		}
		Object[] objects = paramList.toArray();
		LinkedHashMap<String, String> orderby = null;

		List<Category> cateogryList = categoryDAO.searchCollectionByConditionNoPage(
				hqlWhere, objects, orderby);
		if (cateogryList!=null && cateogryList.size()>0) {
			CategoryDTO categoryDTO = this.convertCategoryPO2VO(cateogryList.get(0));
			return categoryDTO;
		}

		return null;
	}

	private CategoryDTO convertCategoryPO2VO(Category category) {
		CategoryDTO categoryDTO = null;
		if (category != null) {
			categoryDTO.setCategoryId(category.getCategoryId());
			categoryDTO.setCategoryContent(category.getCategoryContent());
		}
		return categoryDTO;
	}

	@Override
	public void updateCategory(CategoryDTO categoryDTO) {
		Category category = this.convertCategoryVO2PO(categoryDTO);
		categoryDAO.update(category);
	}

	@Override
	public void deleteCategory(String categoryId) {
		categoryDAO.deleteById(categoryId);
	}

	@Override
	public CategoryDTO searchCategoryByCategoryContent(String categoryContent) {
		String hqlWhere = " where 1=1  ";
		List<Object> paramList = new ArrayList<Object>();
		if (categoryContent != null && !"".equals(categoryContent)) {
			hqlWhere += " and o.categoryContent = ? ";
			paramList.add(categoryContent);
		}
		Object[] objects = paramList.toArray();
		LinkedHashMap<String, String> orderby = null;

		List<Category> cateogryList = categoryDAO.searchCollectionByConditionNoPage(
				hqlWhere, objects, orderby);
		CategoryDTO categoryDTO = null;
		if (cateogryList!=null && cateogryList.size()>0) {
			Category category = cateogryList.get(0);
			categoryDTO = this.convertCategoryPO2VO(category);
		}

		return categoryDTO;
	}

}
