package com.arex.blog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.arex.blog.dto.BlogDTO;
import com.arex.blog.dto.CommentDTO;
import com.arex.blog.service.BlogService;
import com.arex.blog.service.CommentService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:beans.xml")
public class CommentServiceImplTest {
	
	@Resource(name="commentServiceImpl")
	private CommentService commentService;
	
	@Test
	public void testSearchAllCommentByBlogId() {
		String blogId = "ff8081815d656b54015d657231d30005";
		List<CommentDTO> commentDTOList = commentService.searchAllCommentByBlogId(blogId);
		System.out.println(commentDTOList.size());
	}
}
