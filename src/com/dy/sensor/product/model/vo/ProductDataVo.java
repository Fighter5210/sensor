package com.dy.sensor.product.model.vo;

import com.dy.sensor.common.model.base.BaseBO;

public class ProductDataVo extends BaseBO {
	/**
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer productId;
	private String addressCode;
	private String barCode;
	private String productDate;
	private String productBatch;
	private Double temperatureStd;
	private Double temperatureData;
	private Double temperatureOffset;
	private Double humidityStd;
	private Double humidityData;
	private Double humidityOffset;
	private String isCheckNoPass = "0";
	private String unReturnData  = "0";
	private String isOverTemAllowOffset;
	private String isOverHumAllowOffset;
	private String isDelete;
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getAddressCode() {
		return addressCode;
	}
	public void setAddressCode(String addressCode) {
		this.addressCode = addressCode;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getProductDate() {
		return productDate;
	}
	public void setProductDate(String productDate) {
		this.productDate = productDate;
	}

	public String getProductBatch() {
		return productBatch;
	}
	public void setProductBatch(String productBatch) {
		this.productBatch = productBatch;
	}
	
	public synchronized Double getTemperatureStd() {
		return temperatureStd;
	}
	public synchronized void setTemperatureStd(Double temperatureStd) {
		this.temperatureStd = temperatureStd;
	}
	public synchronized Double getTemperatureData() {
		return temperatureData;
	}
	public synchronized void setTemperatureData(Double temperatureData) {
		this.temperatureData = temperatureData;
	}
	public synchronized Double getTemperatureOffset() {
		return temperatureOffset;
	}
	public synchronized void setTemperatureOffset(Double temperatureOffset) {
		this.temperatureOffset = temperatureOffset;
	}
	public synchronized Double getHumidityStd() {
		return humidityStd;
	}
	public synchronized void setHumidityStd(Double humidityStd) {
		this.humidityStd = humidityStd;
	}
	public synchronized Double getHumidityData() {
		return humidityData;
	}
	public synchronized void setHumidityData(Double humidityData) {
		this.humidityData = humidityData;
	}
	public synchronized Double getHumidityOffset() {
		return humidityOffset;
	}
	public synchronized void setHumidityOffset(Double humidityOffset) {
		this.humidityOffset = humidityOffset;
	}
	
	public String getIsCheckNoPass() {
		return isCheckNoPass;
	}
	public void setIsCheckNoPass(String isCheckNoPass) {
		this.isCheckNoPass = isCheckNoPass;
	}
	
	public String getIsOverTemAllowOffset() {
		return isOverTemAllowOffset;
	}
	public void setIsOverTemAllowOffset(String isOverTemAllowOffset) {
		this.isOverTemAllowOffset = isOverTemAllowOffset;
	}
	public String getIsOverHumAllowOffset() {
		return isOverHumAllowOffset;
	}
	public void setIsOverHumAllowOffset(String isOverHumAllowOffset) {
		this.isOverHumAllowOffset = isOverHumAllowOffset;
	}
	public String getUnReturnData() {
		return unReturnData;
	}
	public void setUnReturnData(String unReturnData) {
		this.unReturnData = unReturnData;
	}
	
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	@Override
	public String getBizId() {
		return String.valueOf(this.id);
	}
	
}
