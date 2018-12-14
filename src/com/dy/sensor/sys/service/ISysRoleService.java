package com.dy.sensor.sys.service;

import java.util.List;

import com.dy.sensor.common.exception.RollbackableBizException;
import com.dy.sensor.common.service.IService;
import com.dy.sensor.common.support.Pagination;
import com.dy.sensor.common.support.PaginationParam;
import com.dy.sensor.sys.model.po.SysMenuPo;
import com.dy.sensor.sys.model.po.SysRolePo;
import com.dy.sensor.sys.model.po.SysUserPo;
import com.dy.sensor.sys.model.vo.SysRoleVo;

public interface ISysRoleService extends IService {
	public Pagination<SysRolePo> getSysRolePagination(PaginationParam paginationParam) throws RollbackableBizException;

	public SysRolePo findSysRoleByRoleId(String roleId) throws RollbackableBizException;

	public String saveSysRolePo(SysRolePo sysRolePo) throws RollbackableBizException, Exception;

	public String updateSysRolePo(SysRolePo sysRolePo) throws RollbackableBizException, Exception;

	public String deleteSysRolePoByRoleId(String[] ids) throws RollbackableBizException, Exception;

	public String saveAuthorization(SysRoleVo sysRoleVo)throws RollbackableBizException, Exception;

	public List<SysMenuPo> findSysMenuPoByRoleId(String roleId)throws RollbackableBizException, Exception;

	public Pagination<SysUserPo> findSysUserPagination(PaginationParam paginationParam)throws RollbackableBizException;

	public List<SysUserPo> findSysUserByRoleId(String roleId)throws RollbackableBizException;

}
