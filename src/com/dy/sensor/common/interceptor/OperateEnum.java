package com.dy.sensor.common.interceptor;

/**
 * 
* @ClassName: OperateEnum 
* @Description:
* @author: myh 
* @date: 2015-3-9 下午4:35:19 
*
 */
public enum OperateEnum {

	INSERT("INSERT"),
	UPDATE("UPDATE"),
	DELETE("DELETE"),
	SPECIAL("SPECIAL");
	
	private final String value;
	
	private OperateEnum(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
}
