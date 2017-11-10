package com.arex.blog.utils;

import org.junit.Test;

import com.arex.blog.model.Blog;

public class PropertyUtilsTest {

	@Test 
	public void testSetPropertyValue() {
		Blog blog = new Blog();
		PropertyUtils.setPropertyValue(blog, "kindId", "20", "10");
		System.out.println(blog.getKindId());
	}
}
