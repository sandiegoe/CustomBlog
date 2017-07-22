package com.arex.blog.dao.impl;

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
	
	@Override
	public void updateBlog(final Blog blog) {
		
		hibernateTemplate.execute(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				session.createQuery("update Blog blog set blog.blogContent=:blogContent, blog.blogContentText=:blogContentText, blog.blogTitle=:blogTitle where blog.blogId=:blogId")
					.setParameter("blogContent", blog.getBlogContent())
					.setParameter("blogContentText", blog.getBlogContentText())
					.setParameter("blogTitle", blog.getBlogTitle())
					.setParameter("blogId", blog.getBlogId())
					.executeUpdate();
				return null;
			}
		});
	}


	@Override
	public void halfwayDeleteBlog(final Blog blog) {

		hibernateTemplate.execute(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				session.createQuery("update Blog blog set blog.deleteSign = 1 where blog.blogId=:blogId")
					.setParameter("blogId", blog.getBlogId())
					.executeUpdate();
				return null;
			}
		});
	}


	@Override
	public void halfwayDeleteBlogByBlogId(String blogId) {
		Blog blog = new Blog();
		blog.setBlogId(blogId);
		this.halfwayDeleteBlog(blog);
	}

}
