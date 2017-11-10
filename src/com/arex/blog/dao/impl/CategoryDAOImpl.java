package com.arex.blog.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.arex.blog.dao.CategoryDAO;
import com.arex.blog.model.Category;

@Component
public class CategoryDAOImpl extends CommonDAOImpl<Category> implements
		CategoryDAO {

	@Resource(name = "hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public List<Category> searchAllCategoryByUserId(String userId) {
		List<Category> categoryList = (List<Category>) hibernateTemplate.find("select distinct category from Blog blog left outer join Category category on "
				+ "(blog.categoryId=category.categoryId) where blog.userId=? and blog.categoryId is not null and blog.deleteSign!=1 ", userId);
		return categoryList;
	}

	@Override
	public List<Category> searchAllCategory() {
		List<Category> categoryList = (List<Category>) hibernateTemplate.find("select distinct category from Blog blog left outer join Category category on "
				+ "(blog.categoryId=category.categoryId) where blog.categoryId is not null and blog.deleteSign!=1 ");
		return categoryList;
	}

	

}
