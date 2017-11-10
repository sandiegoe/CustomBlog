package com.arex.blog.dao;

import java.util.LinkedHashMap;
import java.util.List;

import com.arex.blog.model.Blog;
import com.arex.blog.utils.PageInfo;

public interface BlogDAO extends CommonDAO<Blog>{

//	List<Blog> searchAllBlog();
	public void updateBlog(Blog blog);
	public void halfwayDeleteBlog(Blog blog);
	public void halfwayDeleteBlogByBlogId(String blogId);
	public void restoreBlog(Blog blog);
	public void restoreBlogByBlogId(String blogId);
}
