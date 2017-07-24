package com.arex.blog.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * model层 包含当前用户的所有属性
 * 
 * @author arex
 * 
 */

@Entity
@Table(name = "User")
public class User {

	private String userId;
	private String userName;
	private String userNickName;
	private String logonPassword;
	private int sex; // 0 boy 1 girl
	private Date birthdate;
	private String address;
	private String contactTel;
	private String email;
	private String telphone;
	private Date lastLogonDate;
	private Date craeteDate;
	private int idSign = 0;
	
	public int getIdSign() {
		return idSign;
	}

	public void setIdSign(int idSign) {
		this.idSign = idSign;
	}

	//个人头像URL 设置为默认头像
	private String avatarURL = "http://localhost:8080/Blog/img/me.jpg";

	public Date getCraeteDate() {
		return craeteDate;
	}

	public void setCraeteDate(Date craeteDate) {
		this.craeteDate = craeteDate;
	}

	public User() {
		super();
	}

	public User(String userId, String userName, String userNickName,
			String logonPassword, int sex, Date birthdate, String address,
			String contactTel, String email, String telphone, Date lastLogonDate) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userNickName = userNickName;
		this.logonPassword = logonPassword;
		this.sex = sex;
		this.birthdate = birthdate;
		this.address = address;
		this.contactTel = contactTel;
		this.email = email;
		this.telphone = telphone;
		this.lastLogonDate = lastLogonDate;
	}

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
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

	@Temporal(TemporalType.TIMESTAMP)
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

	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastLogonDate() {
		return lastLogonDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public void setLastLogonDate(Date lastLogonDate) {
		this.lastLogonDate = lastLogonDate;
	}

	public String getAvatarURL() {
		return avatarURL;
	}

	public void setAvatarURL(String avatarURL) {
		this.avatarURL = avatarURL;
	}

}
