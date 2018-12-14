package com.dy.sensor.sys.service;

import com.dy.sensor.common.exception.RollbackableBizException;
import com.dy.sensor.sys.model.po.SysParamPo;
import com.dy.sensor.sys.model.vo.SysParamVo;

public interface ISysParamService {
	public String updateSysParam(SysParamPo sysParamPo) throws RollbackableBizException;
	public SysParamVo findSysParamById(String id) throws RollbackableBizException;
}
