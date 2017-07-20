package com.arex.blog.service;

import java.util.Date;
import java.util.List;

import com.arex.blog.dto.LoginDTO;

public interface LoginService {

	public void addLoginRecord(LoginDTO loginDTO);
	public List<LoginDTO> searchLoginByUserId(String userId);
	public List<LoginDTO> searchLoginByUserName(String userName);
	public Date searchLastLoginDateByUserName(String userId);
	public Date searchLastLoginDateByUserId(String userId);
}
