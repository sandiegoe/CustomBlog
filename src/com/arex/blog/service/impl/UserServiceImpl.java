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
		UserDTO searchUserDTO = this.searchUserByUserName(userDTO.getUserName());
		
		return searchUserDTO;
	}
	
	@Override
	public UserDTO searchUserByUserId(UserDTO userDTO) {
		
		UserDTO searchUserDTO = this.searchUserByUserId(userDTO.getUserId());
		return searchUserDTO;
	}
	
	@Override
	public UserDTO searchUserByUserId(String userId) {
		User user = userDAO.searchById(userId);
		UserDTO userDTO = this.convertUserPO2VO(user);
		return userDTO;
	}

	private UserDTO convertUserPO2VO(User user) {
		
		UserDTO userDTO = null;
		if (user != null) {
			userDTO = new UserDTO();
			userDTO.setAddress(user.getAddress());
			userDTO.setBirthdate(user.getBirthdate());
			userDTO.setContactTel(user.getContactTel());
			userDTO.setCraeteDate(user.getCraeteDate());
			userDTO.setEmail(user.getEmail());
			userDTO.setLastLogonDate(user.getLastLogonDate());
			userDTO.setLogonPassword(user.getLogonPassword());
			userDTO.setSex(user.getSex());
			userDTO.setTelphone(user.getTelphone());
			userDTO.setUserId(user.getUserId());
			userDTO.setUserName(user.getUserName());
			userDTO.setUserNickName(user.getUserNickName());
			userDTO.setAvatarURL(user.getAvatarURL());
		}
		return userDTO;
	}

	private List<UserDTO> convertUserListPO2VO(List<User> userList) { 
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
			userDTO.setUserNickName(userList.get(i).getUserNickName());
			userDTO.setAvatarURL(userList.get(i).getAvatarURL());
			userDTOList.add(userDTO);
		}
		
		return userDTOList;
	}
	
	@Override
	public void saveUserDTO(UserDTO userDTO) {
		
		User user = this.convertUserVO2PO(userDTO);
		//注册的时候头像设置为默认图片
		user.setAvatarURL(new User().getAvatarURL());
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
		user.setAvatarURL(userDTO.getAvatarURL());
		user.setUserId(userDTO.getUserId());
		return user;
	}

	@Override
	public void changePassword(UserDTO userDTO) {
		userDAO.changePassword(userDTO.getLogonPassword(), userDTO.getUserId());
	
	}

	@Override
	public UserDTO searchUserByUserName(String userName) {
		String hqlWhere = " where 1=1 ";
		List<String> paramsList = new ArrayList<String>();
		if (userName!=null && !"".equals(userName)) {
			hqlWhere += "and o.userName like ? ";
			paramsList.add(userName + "%");
		}
		Object[] objects = paramsList.toArray();
		LinkedHashMap<String, String> orderby = null;

		List<User> userList = userDAO.searchCollectionByConditionNoPage(hqlWhere, objects, orderby);
		List<UserDTO> userDTOList = this.convertUserListPO2VO(userList);
		
		if (userDTOList.size() >= 1) {
			return userDTOList.get(0);
		} else {
			return null;
		}
	}

	@Deprecated
	@Override
	public void setNewLastLogonDate(UserDTO userDTO) {
		userDAO.setNewLastLogonDate(userDTO.getUserId(), new Date());
	}

	@Override
	public void updateUser(UserDTO userDTO) {
		User user = this.convertUserVO2PO(userDTO);
		userDAO.updateUser(user);
	}


	@Override
	public void updateUserAvatarURL(UserDTO userDTO) {
		User user = this.convertUserVO2PO(userDTO);
		userDAO.updateUserAvatarURL(user);
	}
}
