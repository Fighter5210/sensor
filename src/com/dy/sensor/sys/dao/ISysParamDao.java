package com.dy.sensor.sys.dao;


import com.dy.sensor.common.dao.ICommonDAO;
import com.dy.sensor.common.exception.RollbackableBizException;
import com.dy.sensor.sys.model.po.SysParamPo;
import com.dy.sensor.sys.model.vo.SysParamVo;

public interface ISysParamDao extends ICommonDAO{
	public void updateSysParam(SysParamPo sysParamPo) throws RollbackableBizException;
	public SysParamVo findSysParamById(String id) throws RollbackableBizException;
}
