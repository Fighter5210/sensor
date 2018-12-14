package com.dy.sensor.sys.dao;

import java.util.List;
import java.util.Map;

import com.dy.sensor.common.exception.RollbackableBizException;
import com.dy.sensor.sys.model.po.SysOrganizationPo;

public interface SysOrganizationDao {
	public SysOrganizationPo load(String id) throws RollbackableBizException;

	public List search(Map para) throws RollbackableBizException;

	public List<Object> loadTree() throws RollbackableBizException;

	public List<Object> loadChildren(String parentId) throws RollbackableBizException;

	public SysOrganizationPo save(SysOrganizationPo sysOrganizationPo) throws RollbackableBizException;

	public void delete(String id) throws RollbackableBizException;
}
