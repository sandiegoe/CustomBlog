package com.arex.blog.model;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Blog")
public class Blog {

	private String blogId;
	private String kindId;
	private String blogTitle;
	private Blob blogContent;
	private String blogDescription;
	private Date blogCreateDate;
	private Date lastModifieDate;
	private int blogReadCounts;
	private int blogCommentCounts;
	private String userId;
	private int deleteSign =0 ;

	public int getDeleteSign() {
		return deleteSign;
	}

	public void setDeleteSign(int deleteSign) {
		this.deleteSign = deleteSign;
	}

	private Blob blogContentText;

	public Blog() {
		super();
	}

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
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

	@Lob
	@Basic(fetch = FetchType.EAGER)
	@Column(name = "blogContent", columnDefinition = "BLOB")
	public Blob getBlogContent() {
		return blogContent;
	}

	public void setBlogContent(Blob blogContent) {
		this.blogContent = blogContent;
	}

	public String getBlogDescription() {
		return blogDescription;
	}

	public void setBlogDescription(String blogDescription) {
		this.blogDescription = blogDescription;
	}

	@Temporal(value = TemporalType.TIMESTAMP)
	public Date getBlogCreateDate() {
		return blogCreateDate;
	}

	public void setBlogCreateDate(Date blogCreateDate) {
		this.blogCreateDate = blogCreateDate;
	}

	@Temporal(value = TemporalType.TIMESTAMP)
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

	@Lob
	@Basic(fetch = FetchType.EAGER)
	@Column(name = "blogContentText", columnDefinition = "BLOB")
	public Blob getBlogContentText() {
		return blogContentText;
	}

	public void setBlogContentText(Blob blogContentText) {
		this.blogContentText = blogContentText;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
