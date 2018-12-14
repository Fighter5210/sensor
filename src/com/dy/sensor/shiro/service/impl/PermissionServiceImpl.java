package com.dy.sensor.shiro.service.impl;

import java.util.List;

import com.dy.sensor.shiro.dao.PermissionDao;
import com.dy.sensor.shiro.service.PermissionService;
import com.dy.sensor.sys.model.po.SysMenuPo;
import com.dy.sensor.sys.model.po.SysUserPo;
/**
 * Shiro登录认证权限实现
 * @ClassName: PermissionServiceImpl
 * @Description:TODO
 * @author caohaihong
 * @date 2014-11-27 下午3:47:17
 */
public class PermissionServiceImpl implements PermissionService {
	private PermissionDao permissionDao;

	@Override
	public SysUserPo findUserByLoginName(String logname) {
		return permissionDao.findUserByLoginName(logname);
	}

	@Override
	public List<SysMenuPo> userPermissions(String userId) {
		return permissionDao.userPermissions(userId);
	}

	public PermissionDao getPermissionDao() {
		return permissionDao;
	}

	public void setPermissionDao(PermissionDao permissionDao) {
		this.permissionDao = permissionDao;
	}

}
