package com.arex.blog.dao;

import java.util.Date;

import com.arex.blog.model.Blog;
import com.arex.blog.model.User;

public interface UserDAO extends CommonDAO<User>{

	public void changePassword(String loginPassword, String userId);
	public void setNewLastLogonDate(String userId, Date lastLogonDate);
	public void updateUser(User user);
	public void updateUserAvatarURL(User user);
	public void halfwayDeleteUser(User user);
	public void halfwayDeleteUserByUserId(String userId);
}
