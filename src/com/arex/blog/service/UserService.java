package com.arex.blog.service;

import com.arex.blog.dto.UserDTO;
import com.arex.blog.model.User;

public interface UserService {
	
	public abstract UserDTO searchUserByUserName(UserDTO userDTO);
	public void saveUserDTO(UserDTO userDTO);

}