package com.dy.sensor.product.dao;

import java.util.List;

import com.dy.sensor.common.exception.RollbackableBizException;
import com.dy.sensor.product.model.po.BarCodesPo;
import com.dy.sensor.product.model.vo.BarCodesVo;
/**
 * 产品条形码管理
 * @author Fighter
 *
 */
public interface IBarCodesDao {
	
	/**
	 * 批量添加产品条形码信息
	 * @param barCodesPo
	 * @return
	 */
	public void saveBarCodesBatch(List<BarCodesPo> list) throws RollbackableBizException;
	/**
	 * 根据ID查询产品条形码信息
	 * @param productId
	 * @return
	 */
	public List<BarCodesVo> getBarCodesByProductId(String productId) throws RollbackableBizException;
}
