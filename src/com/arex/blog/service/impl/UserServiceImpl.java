package com.arex.blog.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.arex.blog.dao.UserDAO;
import com.arex.blog.dto.UserDTO;
import com.arex.blog.model.User;
import com.arex.blog.service.UserService;

@Component(value="userServiceImpl")
public class UserServiceImpl implements UserService {
	
	@Resource(name="userDAOImpl")
	private UserDAO userDAO;
	
	@Override
	public UserDTO searchUserByUserName(UserDTO userDTO) {
		
		String hqlWhere = " where 1=1 ";
		List<String> paramsList = new ArrayList<String>();
		if (userDTO!=null && userDTO.getUserName()!=null && !userDTO.getUserName().equals("")) {
			hqlWhere += "and o.userName like ? ";
			paramsList.add(userDTO.getUserName() + "%");
		}
		Object[] objects = paramsList.toArray();
		LinkedHashMap<String, String> orderby = null;

		List<User> userList = userDAO.findCollectionByConditionNoPage(hqlWhere, objects, orderby);
		List<UserDTO> userDTOList = this.convertElecUserPO2VO(userList);
		
		if (userDTOList.size() >= 1) {
			return userDTOList.get(0);
		} else {
			return null;
		}
		
	
	}

	private List<UserDTO> convertElecUserPO2VO(List<User> userList) { 
		List<UserDTO> userDTOList = new ArrayList<UserDTO>();
		for (int i=0; userList!=null && i<userList.size(); ++i) {
			UserDTO userDTO = new UserDTO();
			userDTO.setAddress(userList.get(i).getAddress());
			userDTO.setBirthdate(userList.get(i).getBirthdate());
			userDTO.setContactTel(userList.get(i).getContactTel());
			userDTO.setCraeteDate(userList.get(i).getCraeteDate());
			userDTO.setEmail(userList.get(i).getEmail());
			userDTO.setLastLogonDate(userList.get(i).getLastLogonDate());
			userDTO.setLogonPassword(userList.get(i).getLogonPassword());
			userDTO.setSex(userList.get(i).getSex());
			userDTO.setTelphone(userList.get(i).getTelphone());
			userDTO.setUserId(userList.get(i).getUserId());
			userDTO.setUserName(userList.get(i).getUserName());
			userDTOList.add(userDTO);
		}
		
		return userDTOList;
	}
	
	@Override
	public void saveUserDTO(UserDTO userDTO) {
		
		User user = this.convertUserVO2PO(userDTO);
		userDAO.save(user);
	}

	private User convertUserVO2PO(UserDTO userDTO) {
		User user = new User();
		user.setAddress(userDTO.getAddress());
		user.setBirthdate(userDTO.getBirthdate());
		user.setContactTel(userDTO.getContactTel());
		user.setCraeteDate(new Date());
		user.setEmail(userDTO.getEmail());
		user.setLastLogonDate(new Date());
		user.setSex(userDTO.getSex());
		user.setTelphone(userDTO.getTelphone());
		user.setUserName(userDTO.getUserName());
		user.setUserNickName(userDTO.getUserNickName());
		user.setLogonPassword(userDTO.getLogonPassword());
		return user;
	}

	@Override
	public void changePassword(UserDTO userDTO) {
		userDAO.changePassword(userDTO.getLogonPassword(), userDTO.getUserId());
	
	}
	

}
