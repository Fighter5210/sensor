package com.dy.sensor.common.model;

/**
 * 
 * CopyRight(c) 2014 by GIT
 * @Title: StatusEnum.java
 * @Package com.dy.sensor.common.model
 * @Description: TODO(用一句话描述该文件做什么)
 * @author lizhizhong
 * @date 2014-12-8 下午12:19:54
 * @version V1.0
 */
public enum StatusEnum {

	YES("Y"),
	NO("N");
	
	private final String value;
	
	private StatusEnum(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
}
