package com.arex.blog.dao.impl;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

import com.arex.blog.dao.UserDAO;

public class UserDAOImplTest {


	@Test
	public void test() {
		UserDAO userDAO = new UserDAOImpl();
		userDAO.deleteById(10);
	}

}
