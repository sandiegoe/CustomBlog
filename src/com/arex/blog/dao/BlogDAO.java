package com.arex.blog.dao;

import java.util.List;

import com.arex.blog.model.Blog;

public interface BlogDAO extends CommonDAO<Blog>{

//	List<Blog> searchAllBlog();
	public void updateBlog(Blog blog);

}
