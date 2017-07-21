package com.arex.blog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.arex.blog.dto.PhotoDTO;
import com.arex.blog.service.PhotoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:beans.xml")
public class PhotoServiceImplTest {

	@Resource(name="photoServiceImpl")
	private PhotoService photoService;
	
	@Test
	public void testSearchAllPhotoByUserId() {
		String userId = "ff8081815d5a31fe015d5a35c7270001";
		List<PhotoDTO> photoDTOList = photoService.searchAllPhotoByUserId(userId);
		for (PhotoDTO photoDTO : photoDTOList) {
			//debug
			System.out.println(photoDTO.getFileFileName());
		}
	}
}
