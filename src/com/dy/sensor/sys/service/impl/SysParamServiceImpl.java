package com.dy.sensor.sys.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dy.sensor.common.exception.RollbackableBizException;
import com.dy.sensor.sys.dao.ISysParamDao;
import com.dy.sensor.sys.model.po.SysParamPo;
import com.dy.sensor.sys.model.vo.SysParamVo;
import com.dy.sensor.sys.service.ISysParamService;

public class SysParamServiceImpl implements ISysParamService{
	private ISysParamDao  sysParamDao;
	
	@Override
	public String updateSysParam(SysParamPo sysParamPo)
			throws RollbackableBizException {
		sysParamDao.updateSysParam(sysParamPo);
		return "success";
	}
	
	@Override
	public SysParamVo findSysParamById(String id)
			throws RollbackableBizException {
		return sysParamDao.findSysParamById(id);
	}

	public synchronized void setSysParamDao(ISysParamDao sysParamDao) {
		this.sysParamDao = sysParamDao;
	}
	
}
