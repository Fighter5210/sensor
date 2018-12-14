package com.dy.sensor.sys.service;

import java.util.List;
import java.util.Map;

import com.dy.sensor.common.exception.RollbackableBizException;
import com.dy.sensor.common.model.AdminDicPo;
import com.dy.sensor.common.support.Pagination;
import com.dy.sensor.common.support.PaginationParam;
import com.dy.sensor.sys.model.po.SysMenuPo;
import com.dy.sensor.sys.model.po.SysRolePo;
import com.dy.sensor.sys.model.po.SysUserPo;
import com.dy.sensor.sys.model.po.SysUserRole;
import com.dy.sensor.sys.model.vo.SysUserVo;

public interface IUserService {
	public String saveUser(SysUserPo sysUserPo,String loginNameOld,String loginPassOld) throws RollbackableBizException;
	public String deleteUser(String userId) throws RollbackableBizException;
	public SysUserVo findUserById(String userId) throws RollbackableBizException;
	public Pagination<SysUserPo> findUserPage(PaginationParam paginationParam) throws RollbackableBizException;
	public void saveRoleList(List<SysUserRole> roleList) throws RollbackableBizException;
	public void deleteRoleList(String userId,String roleId) throws RollbackableBizException;
	public Pagination<SysRolePo> findSysRolePagination(PaginationParam paginationParam) throws RollbackableBizException;
	public List<SysMenuPo> findResourceByLoginInfo(String userId,String loginName,String resourceType,String parentId) throws RollbackableBizException;
	public List<SysUserVo> findUserByOrgId(String orgId) throws RollbackableBizException;
	public String findUserNameOnline();
	public List<AdminDicPo> findAdminDicPoByTerraceType(String dicTypeCode) throws RollbackableBizException;

}
