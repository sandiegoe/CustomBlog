package com.arex.blog.dto;

import java.io.File;
import java.util.Date;

public class UserDTO {

	private String userId;
	private String userName;
	private String userNickName;
	private String logonPassword;              //修改密码时：logonPassword代表新密码
	private int sex; // 0 boy 1 girl
	private Date birthdate;
	private String address;
	private String contactTel;
	private String email;
	private String telphone;
	private Date lastLogonDate;
	private Date craeteDate;
	
	//个人头像URL 设置为默认头像
	private String avatarURL;

	private String confimPassword;           //修改密码时：confimPassword代表重复密码
	private String olderPassword;            //修改密码时：olderPassword代表原密码
	
	private String remember;
	//上传头像
	private File file;
	private String fileFileName;
	private String fileContentType;

	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public String getLogonPassword() {
		return logonPassword;
	}

	public void setLogonPassword(String logonPassword) {
		this.logonPassword = logonPassword;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public Date getLastLogonDate() {
		return lastLogonDate;
	}

	public void setLastLogonDate(Date lastLogonDate) {
		this.lastLogonDate = lastLogonDate;
	}

	public Date getCraeteDate() {
		return craeteDate;
	}

	public void setCraeteDate(Date craeteDate) {
		this.craeteDate = craeteDate;
	}

	public String getConfimPassword() {
		return confimPassword;
	}

	public void setConfimPassword(String confimPassword) {
		this.confimPassword = confimPassword;
	}

	public String getRemember() {
		return remember;
	}

	public void setRemember(String remember) {
		this.remember = remember;
	}

	public String getOlderPassword() {
		return olderPassword;
	}

	public void setOlderPassword(String olderPassword) {
		this.olderPassword = olderPassword;
	}

	public String getAvatarURL() {
		return avatarURL;
	}

	public void setAvatarURL(String avatarURL) {
		this.avatarURL = avatarURL;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

}
