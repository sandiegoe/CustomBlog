package com.arex.blog.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.arex.blog.dto.BlogDTO;

@Component(value="blogAction")
@Scope(value="prototype")
public class BlogAction extends CommonAction<BlogDTO> {

	
	
	
}
