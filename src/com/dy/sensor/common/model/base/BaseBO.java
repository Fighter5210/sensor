package com.dy.sensor.common.model.base;

import java.util.Date;

public abstract class BaseBO implements java.io.Serializable{
	
	//业务主键
	private String bizId;
	
	private String createUser;
	private Date createDatetime;
	private String updateUser;
	private Date updateDatetime;

	
	public abstract String getBizId();
	
	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}
}
