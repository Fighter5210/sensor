package com.dy.sensor.sys.service.impl;

import java.util.List;
import java.util.Map;

import com.dy.sensor.common.exception.RollbackableBizException;
import com.dy.sensor.sys.dao.SysOrganizationDao;
import com.dy.sensor.sys.model.po.SysOrganizationPo;
import com.dy.sensor.sys.service.SysOrganizationService;

/**
 * 组织部门管理业务实现类
 * 
 * @ClassName: SysOrganizationServiceImpl
 * @Description:
 * @author: myh
 * @date: 2015-3-10 上午11:27:47
 * 
 */
public class SysOrganizationServiceImpl implements SysOrganizationService {
	private SysOrganizationDao SysOrganizationDao;

	@Override
	public SysOrganizationPo load(String id) throws RollbackableBizException {
		return SysOrganizationDao.load(id);
	}

	@Override
	public List search(Map para) throws RollbackableBizException {
		return null;
	}

	@Override
	public List<Object> loadTree() throws RollbackableBizException {
		return this.getSysOrganizationDao().loadTree();
	}

	@Override
	public SysOrganizationPo save(SysOrganizationPo sysOrganizationPo)
			throws RollbackableBizException {
		return this.getSysOrganizationDao().save(sysOrganizationPo);
	}

	@Override
	public void delete(String id) throws RollbackableBizException {
		this.getSysOrganizationDao().delete(id);
	}

	public SysOrganizationDao getSysOrganizationDao() {
		return SysOrganizationDao;
	}

	public void setSysOrganizationDao(SysOrganizationDao SysOrganizationDao) {
		this.SysOrganizationDao = SysOrganizationDao;
	}

}
