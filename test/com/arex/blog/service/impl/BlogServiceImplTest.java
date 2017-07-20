package com.arex.blog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.arex.blog.dto.BlogDTO;
import com.arex.blog.service.BlogService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:beans.xml")
public class BlogServiceImplTest {
	
	@Resource(name="blogServiceImpl")
	private BlogService blogService;

	public static void main(String[] args) {

	}
	
	@Test
	public void testSearchAllBlogByUserId() {
		String userId = "ff8081815d503cf6015d503d04840000";
		
		List<BlogDTO> userDTOList = blogService.searchAllBlogByUserId(userId);
		System.out.println(userDTOList.size());
		for (BlogDTO blogDTO : userDTOList) {
			System.out.println(blogDTO.getBlogTitle() + "    " + blogDTO.getBlogContent() + "    "+ blogDTO.getUserId());
		}
		
	}
	
	@Test
	public void testSearchAllBlogByUserName() {
		
		String userName = "沈召权";
		List<BlogDTO> userDTOList = blogService.searchAllBlogByUserName(userName);
		for (BlogDTO blogDTO : userDTOList) {
			System.out.println(blogDTO.getBlogTitle() + "    " + blogDTO.getBlogContent() + "    "+ blogDTO.getUserId());
		}
		
	}

	@Test
	public void testSearchBlogByBlogId() {
		String blogId = "ff8081815d58dd11015d58dd657a0000";
		BlogDTO blogDTO = blogService.searchBlogByBlogId(blogId);
		System.out.println(blogDTO.getBlogTitle() + "    " + blogDTO.getBlogContent() + "    "+ blogDTO.getUserId());
	}
	
	@Test
	public void testSearchAllBlog() {
		List<BlogDTO> userDTOList = blogService.searchAllBlog();
		for (BlogDTO blogDTO : userDTOList) {
			System.out.println(blogDTO.getBlogTitle() + "    " + blogDTO.getBlogContent() + "    "+ blogDTO.getUserId());
		}
	}
	
	@Test
	public void testDeleteBlogByBlogId() {
		String blogId = "ff8081815d5b9d42015d5ba118e50004";
		BlogDTO blogDTO = new BlogDTO();
		blogDTO.setBlogId(blogId);
		
		blogService.deleteBlogByBlogId(blogDTO);
	}
}
