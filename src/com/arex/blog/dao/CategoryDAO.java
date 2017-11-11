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

	/**
	 * 查找所有分类和Blog表关联
	 * @return
	 */
	List<Category> searchAllCategory();

	/**
	 * 查询指定用户所有已经删除的分类
	 * @param userId
	 * @return
	 */
	List<Category> searchAllDeletedCategoryByUserId(String userId);

}
