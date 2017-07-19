package com.arex.blog.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.arex.blog.dto.LoginDTO;
import com.arex.blog.model.Login;
import com.arex.blog.service.LoginService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:beans.xml")
public class LoginServiceImplTest {

	@Resource(name="loginServiceImpl")
	private LoginService loginService;
	
	@Test
	public void testAddLoginRecord() {
		LoginDTO l1 = new LoginDTO();
		l1.setLoginDate(new Date());
		l1.setLoginIp("50.45.92.58");
		l1.setUserId("ff8081815d4feaa1015d4fed21190000");
		
		/*LoginDTO l2 = new LoginDTO();
		l2.setLoginDate(new Date());
		l2.setLoginIp("20.45.92.58");
		l2.setUserId("ff8081815d4feaa1015d4fed21190000");*/
		
		/*LoginDTO l3 = new LoginDTO();
		l3.setLoginDate(new Date());
		l3.setLoginIp("128.5.55.45");
		l3.setUserId("ff8081815d4fe5da015d4fe620eb0000");*/
		
		/*LoginDTO l4 = new LoginDTO();
		l4.setLoginDate(new Date());
		l4.setLoginIp("25.45.92.28");
		l4.setUserId("ff8081815d4fe5da015d4fe620eb0000");*/
		
		loginService.addLoginRecord(l1);
//		loginService.addLoginRecord(l2);
//		loginService.addLoginRecord(l3);
//		loginService.addLoginRecord(l4);
	}
	
	@Test
	public void testSearchLoginByUserId() {
		String userId = "ff8081815d4fe5da015d4fe620eb0000";
		List<LoginDTO> loginDTOList = loginService.searchLoginByUserId(userId);
		for (int i=0; loginDTOList!=null && i<loginDTOList.size(); ++i) {
			LoginDTO login = loginDTOList.get(i);
			System.out.println(login.getLoginId() + "    " + login.getUserId() + "    " + login.getLoginIp() + "     " + login.getLoginDate());
		}
	}
	
	@Test
	public void testSearchLoginByUserName() {
		String userName = "谢波";
		List<LoginDTO> loginDTOList = loginService.searchLoginByUserName(userName);
		for (int i=0; loginDTOList!=null && i<loginDTOList.size(); ++i) {
			LoginDTO login = loginDTOList.get(i);
			System.out.println(login.getLoginId() + "    " + login.getUserId() + "    " + login.getLoginIp() + "     " + login.getLoginDate());
		}
	}
	
	@Test
	public void testSearchLastLoginDateByUserName() {
		String userName = "谢波";
		Date date = loginService.searchLastLoginDateByUserName(userName);
		System.out.println(date);
	}
	
	@Test
	public void testSearchLastLoginDateByUserId() {
		String userId = "ff8081815d4fe5da015d4fe620eb0000";
		Date date = loginService.searchLastLoginDateByUserId(userId);
		System.out.println(date);
	}
}
