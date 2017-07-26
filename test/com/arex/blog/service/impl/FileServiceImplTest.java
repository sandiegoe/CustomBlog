package com.arex.blog.service.impl;

import static org.junit.Assert.*;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.AfterClass;
import org.junit.Test;

public class FileServiceImplTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testGetServerHost() {
		ResourceBundle bundle = ResourceBundle.getBundle("deploy",Locale.CHINA);
		String serverHost = bundle.getString("serverhost");
		System.out.println(serverHost);
	}

}
