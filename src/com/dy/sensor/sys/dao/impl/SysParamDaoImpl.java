package com.dy.sensor.sys.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.dy.sensor.common.dao.impl.CommonDAOImpl;
import com.dy.sensor.common.exception.RollbackableBizException;
import com.dy.sensor.sys.dao.ISysParamDao;
import com.dy.sensor.sys.model.po.SysParamPo;
import com.dy.sensor.sys.model.vo.SysParamVo;

public class SysParamDaoImpl extends CommonDAOImpl implements ISysParamDao{
	public void updateSysParam(SysParamPo sysParamPo) throws RollbackableBizException{
		this.update("updateSysParam", sysParamPo);
	}
	public SysParamVo findSysParamById(String id) throws RollbackableBizException{
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", id);
		return this.findObjectByMap("getSysParamById", map);
	}
}
