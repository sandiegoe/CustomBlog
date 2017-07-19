package com.arex.blog.dao.impl;

import javax.annotation.Resource;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.arex.blog.dao.BlogDAO;
import com.arex.blog.model.Blog;

@Component(value="blogDAOImpl")
public class BlogDAOImpl extends CommonDAOImpl<Blog> implements BlogDAO {

	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	
}
