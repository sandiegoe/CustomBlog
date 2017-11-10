package com.arex.blog.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.arex.blog.dto.BlogDTO;

public interface BlogService {

	public List<BlogDTO> searchAllBlog();
	public List<BlogDTO> searchAllBlogByUserId(String userId);
	public List<BlogDTO> searchAllBlogByUserName(String userName);
	public BlogDTO searchBlogByBlogId(String blogId);
	public void saveBlog(BlogDTO BlogDTO);
	public void updateBlog(BlogDTO blogDTO);
	public void deleteBlogByBlogId(BlogDTO blogDTO);
	public void halfwayDeleteBlog(BlogDTO blogDTO);
	public List<BlogDTO> searchAllDeletedBlogByUserId(String userId);
	public void restoreBlog(BlogDTO blogDTO);
	public List<BlogDTO> searchAllBlogByUserId(HttpServletRequest request, String userId);
	public List<BlogDTO> searchAllBlog(HttpServletRequest request);
	public int searchBlogCountsByCategoryId(String categoryId);
	public List<BlogDTO> searchBlogByCategoryId(String categoryId);
	public List<BlogDTO> searchBlogByCategoryId(HttpServletRequest request, String categoryId);
	public List<BlogDTO> searchBlogByCategoryIdAndUserId(HttpServletRequest request, String categoryId, String userId);
	public List<BlogDTO> searchBlogByCategoryIdAndUserId(String categoryId, String userId);
	public int searchBlogCountsByCategoryIdAndUserId(String categoryId, String userId);
}
