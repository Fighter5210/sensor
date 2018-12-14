package com.dy.sensor.sys.dao;

import java.util.List;
import java.util.Map;

import com.dy.sensor.common.exception.RollbackableBizException;
import com.dy.sensor.sys.model.po.SysMenuPo;

public interface SysMenuDao {
	public SysMenuPo load(String id) throws RollbackableBizException;

	public List search(Map para) throws RollbackableBizException;

	public List<Object> loadTree() throws RollbackableBizException;

	public List<Object> loadChildren(String parentId) throws RollbackableBizException;

	public SysMenuPo save(SysMenuPo sysMenuPo) throws RollbackableBizException;

	public void delete(String id) throws RollbackableBizException;

	public List<SysMenuPo> findAll(String resourceType) throws RollbackableBizException;

	public List<Object> roles(String id) throws RollbackableBizException;
}
