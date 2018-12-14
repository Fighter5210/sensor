package com.dy.sensor.product.service;


import java.util.List;

import com.dy.sensor.common.exception.RollbackableBizException;
import com.dy.sensor.product.model.po.BarCodesPo;
import com.dy.sensor.product.model.vo.BarCodesVo;

public interface IBarCodesService {
	public void addBarCodesBatch(List<BarCodesPo> list) throws RollbackableBizException;
	public List<BarCodesVo> getBarCodesByProductId(String productId) throws RollbackableBizException;
}
