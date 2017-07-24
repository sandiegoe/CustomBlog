package com.arex.blog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.arex.blog.dto.MessageDTO;
import com.arex.blog.service.MessageService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:beans.xml")
public class MessageServiceImplTest {

	@Resource(name="messageServiceImpl")
	private MessageService messageService;
	
	@Test
	public void testSearchAllMessageByReceiverId() {
		String receiverId = "ff8081815d5e15de015d5e3df7840001";
		List<MessageDTO> messageDTOList = messageService.searchAllMessageByReceiverId(receiverId);
		System.out.println(messageDTOList);
	}
}
