package com.arex.blog.dto;

import java.util.Date;

public class BlogDTO {

	private String blogId;
	private String kindId;
	private String blogTitle;
	private String blogContent;
	private String blogDescription;
	private Date blogCreateDate;
	private Date lastModifieDate;
	private int blogReadCounts;
	private int blogCommentCounts;
	private String userId;
	
	private String blogContentText;

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

	public void setBlogContentText(String blogContentText) {
		this.blogContentText = blogContentText;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
