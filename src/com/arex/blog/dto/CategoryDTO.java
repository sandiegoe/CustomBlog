package com.arex.blog.dto;

public class CategoryDTO {

	private String categoryId;
	private String categoryContent;
	private int counts;

	public CategoryDTO() {
		super();
	}

	public CategoryDTO(String categoryId, String categoryContent) {
		super();
		this.categoryId = categoryId;
		this.categoryContent = categoryContent;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public int getCounts() {
		return counts;
	}

	public void setCounts(int counts) {
		this.counts = counts;
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
		return "CategoryDTO [categoryId=" + categoryId + ", categoryContent="
				+ categoryContent + ", counts=" + counts + "]";
	}

}
