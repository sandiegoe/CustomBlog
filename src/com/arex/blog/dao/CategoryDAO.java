package com.arex.blog.dao;

import java.util.List;

import com.arex.blog.model.Category;

public interface CategoryDAO extends CommonDAO<Category>{

	/**
	 * 查询指定用户的所有分类
	 * @param userId
	 * @return
	 */
	List<Category> searchAllCategoryByUserId(String userId);

}
