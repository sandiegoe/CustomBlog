package com.arex.blog.dao.impl;

import javax.annotation.Resource;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.arex.blog.dao.UserDAO;
import com.arex.blog.model.User;

@Component("userDAOImpl")
public class UserDAOImpl extends CommonDAOImpl<User> implements UserDAO {

	@Resource(name = "hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	
	
	@Override
	public User searchUserByUserName(String userName) {
		
		
		
		return null;
	}

}
