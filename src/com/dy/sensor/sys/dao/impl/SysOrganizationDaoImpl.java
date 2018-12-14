package com.dy.sensor.sys.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.dy.sensor.common.dao.impl.CommonDAOImpl;
import com.dy.sensor.common.exception.RollbackableBizException;
import com.dy.sensor.foundation.util.UUIDGenerator;
import com.dy.sensor.sys.dao.SysOrganizationDao;
import com.dy.sensor.sys.model.po.SysOrganizationPo;

public class SysOrganizationDaoImpl extends CommonDAOImpl implements SysOrganizationDao {

	@SuppressWarnings("rawtypes")
	@Override
	public SysOrganizationPo load(String orgId) throws RollbackableBizException {
		List list = super.findByID("SysOrganization.load", orgId);
		if (list != null)
			for (Object o : list) {
				return (SysOrganizationPo) o;
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
		List list = super.findAll("SysOrganization.tree");
		return list;
	}

	@Override
	public SysOrganizationPo save(SysOrganizationPo sysOrganizationPo) throws RollbackableBizException {
		if (sysOrganizationPo.getOrgId() == null || "".equals(sysOrganizationPo.getOrgId())) {
			sysOrganizationPo.setCreateDatetime(new Date());
			sysOrganizationPo.setOrgId(UUIDGenerator.getUUID());
			sysOrganizationPo.setIsActive("Y");
			if (sysOrganizationPo.getParent() == null || sysOrganizationPo.getParent().getOrgId() == null || "".equals(sysOrganizationPo.getParent().getOrgId())) {
				SysOrganizationPo p = new SysOrganizationPo();
				p.setOrgId("0");
				sysOrganizationPo.setParent(p);
			}
			this.getSqlMapClientTemplate().insert("SysOrganization.insert", sysOrganizationPo);
		} else {
			sysOrganizationPo.setUpdateDatetime(new Date());
			this.getSqlMapClientTemplate().update("SysOrganization.update", sysOrganizationPo);
		}
		return sysOrganizationPo;
	}

	@Override
	public void delete(String id) throws RollbackableBizException {
		// 查询全部子菜单
		String pid = id;
		String allChildren = id;
		while (pid != null) {
			Object o = this.getSqlMapClientTemplate().queryForObject("SysOrganization.loadChildren", pid);
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
			this.getSqlMapClientTemplate().update("SysOrganization.delete0", d);
		}
		// this.getSqlMapClientTemplate().update("SysOrganization.delete0", id);
		// super.update("SysOrganization.delete0", id);
		// super.delete("SysOrganization.delete", id);
	}

	@Override
	public List<Object> loadChildren(String parentId) throws RollbackableBizException {
		return this.getSqlMapClientTemplate().queryForList("SysOrganization.loadChildren", parentId);
	}

}
