package com.dy.sensor.sys.model.po;

import com.dy.sensor.common.model.base.BaseBO;

public class SysParamPo extends BaseBO {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String commIntervalTime;
	private Double temAllowOffset;
	private Double humAllowOffset;
	public synchronized Integer getId() {
		return id;
	}
	public synchronized void setId(Integer id) {
		this.id = id;
	}
	public synchronized String getCommIntervalTime() {
		return commIntervalTime;
	}
	public synchronized void setCommIntervalTime(String commIntervalTime) {
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
		return String.valueOf(id);
	}
}
