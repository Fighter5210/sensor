package com.dy.sensor.sys.service;

import java.util.List;
import java.util.Map;

import com.dy.sensor.common.exception.RollbackableBizException;
import com.dy.sensor.sys.model.po.SysMenuPo;

/**
 * 菜单管理业务类接口;
 * 
 * @ClassName: SysMenuService
 * @Description:
 * @author: myh
 * @date: 2015-3-10 下午1:57:27
 * 
 */
public interface SysMenuService {

	public SysMenuPo load(String id) throws RollbackableBizException;

	public List search(Map para) throws RollbackableBizException;

	public List<Object> loadTree() throws RollbackableBizException;

	public SysMenuPo save(SysMenuPo sysMenuPo) throws RollbackableBizException;

	public void delete(String id) throws RollbackableBizException;

	public List<Object> roles(String id) throws RollbackableBizException;
}
