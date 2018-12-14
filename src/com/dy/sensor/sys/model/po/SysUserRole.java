package com.dy.sensor.sys.model.po;

import java.io.Serializable;

import com.dy.sensor.common.model.base.BaseBO;

public class SysUserRole extends BaseBO implements Serializable  {
    
	private static final long serialVersionUID = 1L;
	
	private String userId;
	private String roleId;
	
	public SysUserRole(){}
	public SysUserRole(String userId, String roleId) {
		super();
		this.userId = userId;
		this.roleId = roleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Override
	public String getBizId() {
		// TODO Auto-generated method stub
		return null;
	}

}
