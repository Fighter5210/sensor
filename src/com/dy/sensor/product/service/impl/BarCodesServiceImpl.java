package com.dy.sensor.product.service.impl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dy.sensor.common.exception.RollbackableBizException;
import com.dy.sensor.product.dao.IBarCodesDao;
import com.dy.sensor.product.model.po.BarCodesPo;
import com.dy.sensor.product.model.vo.BarCodesVo;
import com.dy.sensor.product.service.IBarCodesService;

public class BarCodesServiceImpl implements IBarCodesService{
	private static Log logger = LogFactory.getLog(BarCodesServiceImpl.class);
	private IBarCodesDao  barCodesDao;
	
	/**
	 * 保存产品条形码信息
	 * @param productPo
	 * @return
	 * @throws RollbackableBizException
	 */
	public void addBarCodesBatch(List<BarCodesPo> list) throws RollbackableBizException{
		try {		
			barCodesDao.saveBarCodesBatch(list);
		} catch (Exception e) {
			logger.error("保存产品信息时发生异常:"+e.getMessage());
		}
	}

	/**
	 * 根据产品ID查询产品条形码信息
	 * @param productPo
	 * @return
	 * @throws RollbackableBizException
	 */
	public List<BarCodesVo> getBarCodesByProductId(String productId) throws RollbackableBizException{
		return barCodesDao.getBarCodesByProductId(productId);
	}

	public void setBarCodesDao(IBarCodesDao barCodesDao) {
		this.barCodesDao = barCodesDao;
	}

}
