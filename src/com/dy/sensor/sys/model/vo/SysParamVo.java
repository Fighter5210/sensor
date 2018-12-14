package com.dy.sensor.sys.model.vo;

import com.dy.sensor.common.model.base.BaseBO;
public class SysParamVo extends BaseBO {

	private static final long serialVersionUID = 1L;
	private String id;
	private Integer commIntervalTime;
	private Double temAllowOffset;
	private Double humAllowOffset;
	
	public synchronized String getId() {
		return id;
	}

	public synchronized void setId(String id) {
		this.id = id;
	}

	public synchronized Integer getCommIntervalTime() {
		return commIntervalTime;
	}

	public synchronized void setCommIntervalTime(Integer commIntervalTime) {
		this.commIntervalTime = commIntervalTime;
	}


	public synchronized Double getTemAllowOffset() {
		return temAllowOffset;
	}

	public synchronized void setTemAllowOffset(Double temAllowOffset) {
		this.temAllowOffset = temAllowOffset;
	}

	public synchronized Double getHumAllowOffset() {
		return humAllowOffset;
	}

	public synchronized void setHumAllowOffset(Double humAllowOffset) {
		this.humAllowOffset = humAllowOffset;
	}

	@Override
	public String getBizId() {
		return String.valueOf(this.id);
	}
}
