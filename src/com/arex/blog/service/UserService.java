package com.arex.blog.service;

import com.arex.blog.dto.UserDTO;
import com.arex.blog.model.User;

public interface UserService {
	
	public abstract UserDTO searchUserByUserName(UserDTO userDTO);
	public void saveUserDTO(UserDTO userDTO);
	public void changePassword(UserDTO userDTO);
	public UserDTO searchUserByUserName(String userName);
	public abstract void setNewLastLogonDate(UserDTO userDTO);
	public UserDTO searchUserByUserId(UserDTO userDTO);
	public UserDTO searchUserByUserId(String userId);
	public abstract void updateUser(UserDTO userDTO);
	public abstract void updateUserAvatarURL(UserDTO userDTO);
	public abstract boolean isExistsUserByUserId(String userId);
	public void halfwayDeleteUser(UserDTO userDTO);
}
