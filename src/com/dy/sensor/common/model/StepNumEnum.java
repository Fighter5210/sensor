package com.dy.sensor.common.model;

/**
 * CopyRight(c) 2014 by GIT
 * 
 * @Title: StepNumEnum.java
 * @Package com.dy.sensor.common.model
 * @Description: 软件安装时，任务执行时的步骤编号
 * @author lizhizhong
 * @date 2015-1-28 下午1:46:09
 * @version V1.0
 */
public enum StepNumEnum {

	STEP_ZERO("0"),
	STEP_ONE("1"),
	STEP_TWO("2"),
	STEP_THREE("3"),
	STEP_FOUR("4"),
	STEP_FIVE("5");
	
	private final String value;
	
	private StepNumEnum(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
}
