package com.dy.sensor.product.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dy.sensor.common.dao.impl.CommonDAOImpl;
import com.dy.sensor.common.exception.RollbackableBizException;
import com.dy.sensor.product.dao.IBarCodesDao;
import com.dy.sensor.product.model.po.BarCodesPo;
import com.dy.sensor.product.model.vo.BarCodesVo;
/**
 * 产品条形码
 * @author Fighter
 *
 */
public class BarCodesDaoImpl extends CommonDAOImpl implements IBarCodesDao {


	@Override
	public void saveBarCodesBatch(List<BarCodesPo> barCodesList)
			throws RollbackableBizException {
		 this.batchInsert("insertBarCodesList", barCodesList);
	}
	@Override
	public List<BarCodesVo> getBarCodesByProductId(String productId)
			throws RollbackableBizException {
		Map<String,String> map = new HashMap<String,String>();
		map.put("productId", productId);
		return this.findListByParam("getBarCodesByProductId", map);
	}

}
