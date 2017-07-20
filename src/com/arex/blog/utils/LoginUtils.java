package com.arex.blog.utils;

import javax.servlet.http.HttpSession;

import com.arex.blog.dto.UserDTO;

public class LoginUtils {

	public static boolean checkUserIsAlreadyLogin(HttpSession session) {

		UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
		if (loginUser == null) {
			return false;
		} else {
			return true;
		}
	}
}
