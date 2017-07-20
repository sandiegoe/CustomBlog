package com.arex.blog.service;

import java.util.List;

import com.arex.blog.dto.BlogDTO;

public interface BlogService {

	public List<BlogDTO> searchAllBlog();
	public List<BlogDTO> searchAllBlogByUserId(String userId);
	public List<BlogDTO> searchAllBlogByUserName(String userName);
	public BlogDTO searchBlogByBlogId(String blogId);
	public void saveBlog(BlogDTO BlogDTO);
}
