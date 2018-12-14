package com.dy.sensor.sys.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dy.sensor.common.dao.impl.CommonDAOImpl;
import com.dy.sensor.common.exception.RollbackableBizException;
import com.dy.sensor.common.model.IsActiveEnum;
import com.dy.sensor.foundation.util.UUIDGenerator;
import com.dy.sensor.sys.dao.SysMenuDao;
import com.dy.sensor.sys.model.po.SysMenuPo;

public class SysMenuDaoImpl extends CommonDAOImpl implements SysMenuDao {

	@Override
	public SysMenuPo load(String id) throws RollbackableBizException {
		List list = super.findByID("SysMenu.load", id);
			if (list != null)
			for (Object o : list) {
				return (SysMenuPo) o;
			}
		return null;
	}

	@Override
	public List search(Map para) {
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Object> loadTree() throws RollbackableBizException {
		List list = super.findAll("SysMenu.tree");
		return list;
	}

	@Override
	public SysMenuPo save(SysMenuPo sysMenuPo) throws RollbackableBizException {
		if (sysMenuPo.getId() == null || "".equals(sysMenuPo.getId())) {
			sysMenuPo.setCreateDatetime(new Date());
			sysMenuPo.setId(UUIDGenerator.getUUID());
			sysMenuPo.setIsActive("Y");
			if (sysMenuPo.getParent() == null || sysMenuPo.getParent().getId() == null || "".equals(sysMenuPo.getParent().getId())) {
				SysMenuPo p = new SysMenuPo();
				p.setId("0");
			}
			this.getSqlMapClientTemplate().insert("SysMenu.insert", sysMenuPo);
		} else {
			sysMenuPo.setUpdateDatetime(new Date());
			this.getSqlMapClientTemplate().update("SysMenu.update", sysMenuPo);
		}
		return sysMenuPo;
	}

	@Override
	public void delete(String id) throws RollbackableBizException {
		// 查询全部子菜单
		String pid = id;
		String allChildren = id;
		while (pid != null) {
			Object o = this.getSqlMapClientTemplate().queryForObject("SysMenu.loadChildren", pid);
			if (o != null) {
				allChildren += "," + o.toString();
				pid = o.toString();
			} else {
				pid = null;
			}
		}
		System.out.println(allChildren);
		String[] ids = allChildren.split(",");
		for (String d : ids) {
			this.getSqlMapClientTemplate().update("SysMenu.delete0", d);
		}
		// this.getSqlMapClientTemplate().update("SysMenu.delete0", id);
		// super.update("SysMenu.delete0", id);
		// super.delete("SysMenu.delete", id);
	}

	@Override
	public List<Object> loadChildren(String parentId) throws RollbackableBizException {
		return this.getSqlMapClientTemplate().queryForList("SysMenu.loadChildren", parentId);
	}
	
	@Override
	public List<SysMenuPo> findAll(String resourceType) throws RollbackableBizException {
		Map<String,String> map = new HashMap<String,String>();
		map.put("isActive", IsActiveEnum.YES.getValue());
		map.put("resourceType", resourceType);
		return this.findListByParam("findAllFunctions", map);
	}

	@Override
	public List<Object> roles(String id) throws RollbackableBizException {
		return this.getSqlMapClientTemplate().queryForList("SysMenu.roles", id);
	}

}
