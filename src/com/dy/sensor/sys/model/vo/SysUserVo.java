package com.dy.sensor.sys.model.vo;

import java.io.Serializable;

import com.dy.sensor.common.model.base.BaseBO;

public class SysUserVo   extends BaseBO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public SysUserVo(){}
	
	private String userId;
	private String orgId;
	private String orgName;
	private String firstName;
	private String lastName;
	private String loginName;
	private String loginPassword;
	private String email;
	private String phone;
	private String ipAddress;
	private String lastLogin;
	private String userType;
	private String userTypeName;
	private String isActive;
	
	private String userDevices;		//角色菜单关联数据


	public SysUserVo(String userId, String orgId, String orgName,
			String firstName, String lastName, String loginName,
			String loginPassword, String email, String phone, String ipAddress,
			String lastLogin, String userType, String userTypeName,
			String isActive) {
		super();
		this.userId = userId;
		this.orgId = orgId;
		this.orgName = orgName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.loginName = loginName;
		this.loginPassword = loginPassword;
		this.email = email;
		this.phone = phone;
		this.ipAddress = ipAddress;
		this.lastLogin = lastLogin;
		this.userType = userType;
		this.userTypeName = userTypeName;
		this.isActive = isActive;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getOrgId() {
		return orgId;
	}


	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}


	public String getOrgName() {
		return orgName;
	}


	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getLoginName() {
		return loginName;
	}


	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}


	public String getLoginPassword() {
		return loginPassword;
	}


	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getIpAddress() {
		return ipAddress;
	}


	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}


	public String getLastLogin() {
		return lastLogin;
	}


	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}


	public String getUserType() {
		return userType;
	}


	public void setUserType(String userType) {
		this.userType = userType;
	}


	public String getUserTypeName() {
		return userTypeName;
	}


	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}


	public String getIsActive() {
		return isActive;
	}


	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}


	@Override
	public String getBizId() {
		// TODO Auto-generated method stub
		return null;
	}


	public String getUserDevices() {
		return userDevices;
	}


	public void setUserDevices(String userDevices) {
		this.userDevices = userDevices;
	}

}
