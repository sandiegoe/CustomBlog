package com.arex.blog.dao;

import java.util.Date;

import com.arex.blog.model.User;

public interface UserDAO extends CommonDAO<User>{

	public void changePassword(String loginPassword, String userId);
	public void setNewLastLogonDate(String userId, Date lastLogonDate);
}
