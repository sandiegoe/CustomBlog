package com.arex.blog.dao;

import com.arex.blog.model.User;

public interface UserDAO extends CommonDAO<User>{

	public void changePassword(String loginPassword, String userId);
}
