package com.arex.blog.service.impl;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.arex.blog.dto.UserDTO;
import com.arex.blog.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:beans.xml")
public class UserServiceImplTest {

	@Resource(name="userServiceImpl")
	private UserService userService;
	
	@Test
	public void testSearchUserByUserId() {
		
		/*String userId = "ff8081815d5a31fe015d5a35c7270001";
		UserDTO userDTO = userService.searchUserByUserId(userId);
		System.out.println(userDTO.getUserName());*/
		
		String userId = "ff8081815d5a31fe015d5a35c7270001";
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(userId);
		System.out.println(userService.searchUserByUserId(userDTO).getUserName());
	}
	
	@Test
	public void testUpdateUserAvatarURL() {
		String userId = "ff8081815d5a31fe015d5a35c7270001";
		String avatarURL = "http://localhost:8080/Blog/img/me.jpg";
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(userId);
		userDTO.setAvatarURL(avatarURL);
		userService.updateUserAvatarURL(userDTO);
	}
}
