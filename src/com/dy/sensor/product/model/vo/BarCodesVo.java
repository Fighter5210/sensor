package com.dy.sensor.product.model.vo;

import com.dy.sensor.common.model.base.BaseBO;

public class BarCodesVo extends BaseBO {
	/**
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer productId;
	private String addressCode;
	private String barCode;
	
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
	@Override
	public String getBizId() {
		return String.valueOf(this.id);
	}
}
