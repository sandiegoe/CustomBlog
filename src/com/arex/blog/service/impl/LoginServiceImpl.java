package com.arex.blog.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.arex.blog.dao.LoginDAO;
import com.arex.blog.dto.LoginDTO;
import com.arex.blog.dto.UserDTO;
import com.arex.blog.model.Login;
import com.arex.blog.service.LoginService;
import com.arex.blog.service.UserService;

@Component(value = "loginServiceImpl")
public class LoginServiceImpl implements LoginService {

	@Resource(name = "loginDAOImpl")
	private LoginDAO loginDAO;
	@Resource(name = "userServiceImpl")
	private UserService userService;

	@Override
	public void addLoginRecord(LoginDTO loginDTO) {
		// 将loginDTO转换为login对象
		Login login = this.convertVO2PO(loginDTO);

		// 保存login对象
		if (login != null) {
			loginDAO.save(login);
		}
	}

	private Login convertVO2PO(LoginDTO loginDTO) {

		Login login = null;
		if (loginDTO != null) {
			login = new Login();
			login.setLoginDate(new Date());
			login.setLoginIp(loginDTO.getLoginIp());
			login.setUserId(loginDTO.getUserId());
		}
		return login;
	}

	@Override
	public List<LoginDTO> searchLoginByUserId(String userId) {

		String hqlWhere = " where 1=1 ";
		List<Object> paramList = new ArrayList<Object>();
		if (userId != null && !"".equals(userId)) {
			hqlWhere += " and o.userId = ? ";
			paramList.add(userId);
		}
		Object[] objects = paramList.toArray();
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.loginDate", "desc");

		List<Login> loginList = loginDAO.searchCollectionByConditionNoPage(
				hqlWhere, objects, orderby);
		List<LoginDTO> loginDTOList = this.convertLoginListPO2VO(loginList);

		return loginDTOList;
	}

	private List<LoginDTO> convertLoginListPO2VO(List<Login> loginList) {
		List<LoginDTO> loginDTOList = new ArrayList<LoginDTO>();
		LoginDTO loginDTO = null;
		for (int i = 0; loginList != null && i < loginList.size(); ++i) {
			loginDTO = new LoginDTO();
			Login login = loginList.get(i);
			loginDTO.setLoginDate(login.getLoginDate());
			loginDTO.setLoginId(login.getLoginId());
			loginDTO.setLoginIp(login.getLoginIp());
			loginDTO.setUserId(login.getUserId());
			loginDTOList.add(loginDTO);
		}
		return loginDTOList;
	}

	@Override
	public List<LoginDTO> searchLoginByUserName(String userName) {

		// 查找指定userName对应的userId
		UserDTO userDTO = userService.searchUserByUserName(userName);
		if (userDTO == null || userDTO.getUserId() == null
				|| "".equals(userDTO.getUserId())) {
			return null;
		}
		// 调用searchLoginByUserId(String userId)
		List<LoginDTO> loginDTO = this.searchLoginByUserId(userDTO.getUserId());
		return loginDTO;
	}

	@Override
	public Date searchLastLoginDateByUserName(String userName) {

		// 取出指定userName的userId
		UserDTO userDTO = userService.searchUserByUserName(userName);
		if (userDTO==null || userDTO.getUserId()==null || "".equals(userDTO.getUserId())) {
			return null;
		}
		
		//调用searchLastLoginDateByUserId获取登录时间
		Date date = this.searchLastLoginDateByUserId(userDTO.getUserId());
		return date;
	}

	@Override
	public Date searchLastLoginDateByUserId(String userId) {

		Date date = null;

		// 取出userId的所有登录记录，按照降序排列
		List<LoginDTO> loginDTOList = this.searchLoginByUserId(userId);

		// 取出第一条即是最后一次登录时间
		if (loginDTOList != null && loginDTOList.size()>0) {
			//如果当前用户第一次登录，则上次登录时间即为本次登录时间
			if (loginDTOList.size() == 1) {
				date = loginDTOList.get(0).getLoginDate();
			} else {
				//取出第二条记录，即为上一次的登录时间
				//本次登录，会立即添加一条记录，所以取上次登录时间，为第二条记录
				date = loginDTOList.get(1).getLoginDate();
			}
		}
		return date;
	}

}
