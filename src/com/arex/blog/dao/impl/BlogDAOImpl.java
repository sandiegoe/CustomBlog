package com.arex.blog.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.arex.blog.dao.BlogDAO;
import com.arex.blog.model.Blog;

@Component(value="blogDAOImpl")
public class BlogDAOImpl extends CommonDAOImpl<Blog> implements BlogDAO {

	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;

	/*@Override
	public List<Blog> searchAllBlog() {
		
		hibernateTemplate.execute(new HibernateCallback<Blog>() {

			@Override
			public Blog doInHibernate(Session session) throws HibernateException {
				
				Query query = session.createSQLQuery("select * from Blog");
				
				return null;
			}
		});
		
		return null;
	}*/
	
	
}
