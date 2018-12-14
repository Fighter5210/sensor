package com.dy.sensor.product.model.vo;

import com.dy.sensor.common.model.base.BaseBO;

public class ProductVo extends BaseBO {
	/**
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String pDate;
	private String pNumber;
	private String pBatch;
	private Double temperatureStd;
	private Double humidityStd;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPDate() {
		return pDate;
	}

	public void setPDate(String date) {
		pDate = date;
	}

	public String getPNumber() {
		return pNumber;
	}

	public void setPNumber(String number) {
		pNumber = number;
	}

	public String getPBatch() {
		return pBatch;
	}

	public void setPBatch(String batch) {
		pBatch = batch;
	}


	public synchronized Double getTemperatureStd() {
		return temperatureStd;
	}

	public synchronized void setTemperatureStd(Double temperatureStd) {
		this.temperatureStd = temperatureStd;
	}

	public synchronized Double getHumidityStd() {
		return humidityStd;
	}

	public synchronized void setHumidityStd(Double humidityStd) {
		this.humidityStd = humidityStd;
	}

	@Override
	public String getBizId() {
		return String.valueOf(this.id);
	}
}
