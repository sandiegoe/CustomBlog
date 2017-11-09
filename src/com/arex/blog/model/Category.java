package com.arex.blog.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Category")
public class Category {

	private String categoryId;
	private String categoryContent;

	public Category(String categoryId, String categoryContent) {
		super();
		this.categoryId = categoryId;
		this.categoryContent = categoryContent;
	}

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryContent() {
		return categoryContent;
	}

	public void setCategoryContent(String categoryContent) {
		this.categoryContent = categoryContent;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryContent="
				+ categoryContent + "]";
	}

}
