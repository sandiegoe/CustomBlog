package com.arex.blog.dto;

import java.util.Date;

public class BlogDTO {

	public int getDeleteSign() {
		return deleteSign;
	}

	public void setDeleteSign(int deleteSign) {
		this.deleteSign = deleteSign;
	}

	private String categoryId;
	private String blogId;
	private String kindId; //博客的种类
	private String blogTitle;
	private String blogContent;
	private String blogDescription;
	private Date blogCreateDate;
	private Date lastModifieDate;
	private int blogReadCounts;
	private int blogCommentCounts;
	private String userId;
	private int deleteSign = 0;
	
	private String blogContentText;
	
	private String userName;

	public BlogDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getBlogId() {
		return blogId;
	}

	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}

	public String getKindId() {
		return kindId;
	}

	public void setKindId(String kindId) {
		this.kindId = kindId;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getBlogContent() {
		return blogContent;
	}

	public void setBlogContent(String blogContent) {
		this.blogContent = blogContent;
	}

	public String getBlogDescription() {
		return blogDescription;
	}

	public void setBlogDescription(String blogDescription) {
		this.blogDescription = blogDescription;
	}

	public Date getBlogCreateDate() {
		return blogCreateDate;
	}

	public void setBlogCreateDate(Date blogCreateDate) {
		this.blogCreateDate = blogCreateDate;
	}

	public Date getLastModifieDate() {
		return lastModifieDate;
	}

	public void setLastModifieDate(Date lastModifieDate) {
		this.lastModifieDate = lastModifieDate;
	}

	public int getBlogReadCounts() {
		return blogReadCounts;
	}

	public void setBlogReadCounts(int blogReadCounts) {
		this.blogReadCounts = blogReadCounts;
	}

	public int getBlogCommentCounts() {
		return blogCommentCounts;
	}

	public void setBlogCommentCounts(int blogCommentCounts) {
		this.blogCommentCounts = blogCommentCounts;
	}

	public String getBlogContentText() {
		return blogContentText;
	}

	/**
	 * 设置博客的章摘要  截取博客纯文本内容的前90个字符
	 * @param blogContentText 博客的纯文本内容
	 * @author arex
	 * @Time2012-11-20 15:02:29
	 */
	public void setBlogContentText(String blogContentText) {
		String  summary = "";
		if (blogContentText.length() > 90) {
			summary = blogContentText.substring(0, 90);
		} else {
			summary = blogContentText;
		}
		this.blogContentText = summary + "...";
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

}
