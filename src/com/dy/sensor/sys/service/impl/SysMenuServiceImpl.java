package com.dy.sensor.sys.service.impl;

import java.util.List;
import java.util.Map;

import com.dy.sensor.cache.Cache;
import com.dy.sensor.cache.CacheException;
import com.dy.sensor.cache.CacheUtil;
import com.dy.sensor.common.exception.RollbackableBizException;
import com.dy.sensor.sys.dao.SysMenuDao;
import com.dy.sensor.sys.model.po.SysMenuPo;
import com.dy.sensor.sys.service.SysMenuService;

/**
 * 菜单管理业务实现类;
 * 
 * @ClassName: SysMenuServiceImpl
 * @Description:
 * @author: myh
 * @date: 2015-3-10 下午1:57:41
 * 
 */
public class SysMenuServiceImpl implements SysMenuService {
	private SysMenuDao SysMenuDao;

	@Override
	public SysMenuPo load(String id) throws RollbackableBizException {
		return SysMenuDao.load(id);
	}

	@Override
	public List search(Map para) throws RollbackableBizException {
		return null;
	}

	@Override
	public List<Object> loadTree() throws RollbackableBizException {
		return this.getSysMenuDao().loadTree();
	}

	@Override
	public SysMenuPo save(SysMenuPo sysMenuPo) throws RollbackableBizException {
		return this.getSysMenuDao().save(sysMenuPo);
	}

	@Override
	public void delete(String id) throws RollbackableBizException {
		this.getSysMenuDao().delete(id);
	}

	public SysMenuDao getSysMenuDao() {
		return SysMenuDao;
	}

	public void setSysMenuDao(SysMenuDao SysMenuDao) {
		this.SysMenuDao = SysMenuDao;
	}

	@Override
	public List<Object> roles(String id) throws RollbackableBizException {
		return this.getSysMenuDao().roles(id);
	}

}
