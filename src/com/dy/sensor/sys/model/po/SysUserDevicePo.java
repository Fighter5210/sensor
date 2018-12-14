package com.dy.sensor.sys.model.po;

import java.io.Serializable;

import com.dy.sensor.common.model.base.BaseBO;

public class SysUserDevicePo extends BaseBO implements Serializable {
	
	private static final long serialVersionUID = 2515713714414635310L;
	
	private String userId;
	private String deviceId;
	
	public SysUserDevicePo(){
	}
	public SysUserDevicePo(String userId,String deviceId){
		this.userId = userId;
		this.deviceId = deviceId;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Override
	public String getBizId() {
		return null;
	}

}
