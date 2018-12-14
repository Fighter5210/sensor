package com.dy.sensor.sys.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;

import com.dy.sensor.common.dao.impl.CommonDAOImpl;
import com.dy.sensor.common.exception.RollbackableBizException;
import com.dy.sensor.common.model.AdminDicPo;
import com.dy.sensor.common.model.IsActiveEnum;
import com.dy.sensor.common.support.Pagination;
import com.dy.sensor.common.support.PaginationParam;
import com.dy.sensor.foundation.util.CollectionUtil;
import com.dy.sensor.sys.dao.IUserDao;
import com.dy.sensor.sys.model.po.SysMenuPo;
import com.dy.sensor.sys.model.po.SysRolePo;
import com.dy.sensor.sys.model.po.SysUserDevicePo;
import com.dy.sensor.sys.model.po.SysUserPo;
import com.dy.sensor.sys.model.po.SysUserRole;
import com.dy.sensor.sys.model.vo.SysUserVo;

public class UserDaoImpl extends CommonDAOImpl implements IUserDao{
	/**
	 * 保存用户信息	
	 */
	public void saveUser(SysUserPo sysUserPo) throws RollbackableBizException{
		sysUserPo.setIsActive(IsActiveEnum.YES.getValue());
		this.save("insertUser", sysUserPo);
	}
	/**
	 * 更新用户信息
	 */	
	public void updateUser(SysUserPo sysUserPo) throws RollbackableBizException{
		sysUserPo.setIsActive(IsActiveEnum.YES.getValue());
		this.update("updateUser", sysUserPo);
	}
	/**
	 * 删除用户信息
	 */
	public void deleteUser(String userId) throws RollbackableBizException{
		Map<String,String> map = new HashMap<String,String>();
		map.put("isActive", IsActiveEnum.NO.getValue());
		map.put("userId", userId);
		this.deleteForParam("deleteUser", map);
	}
	/**
	 * 根据ID获取用户信息
	 */
	public SysUserVo findUserById(String userId) throws RollbackableBizException{
		Map<String,String> map = new HashMap<String,String>();
		map.put("isActive", IsActiveEnum.YES.getValue());
		map.put("userId", userId);
		return this.findObjectByMap("getUserById", map);
	}
	/**
	 * 根据机构ID获得所有用户
	 */
	public List<SysUserVo> findUserByOrgId(String orgId) throws RollbackableBizException{
		Map<String,String> map = new HashMap<String,String>();
		map.put("isActive", IsActiveEnum.YES.getValue());
		map.put("orgId", orgId);
		return this.findListByParam("getUserByOrgId", map);
	}
	/**
	 * 保存授予角色
	 * @param roleList
	 * @throws RollbackableBizException
	 */
	public void saveRoleList(List<SysUserRole> roleList) throws RollbackableBizException {
		if(CollectionUtil.hasContent(roleList)){
			this.batchInsert("insertRoleList",roleList);	
		}
	}
	/**
	 * 取消授予角色
	 * @param userId
	 * @param roleId
	 * @throws RollbackableBizException
	 */
	public void deleteRoleList(Map<String,Object> mapRole) throws RollbackableBizException {
		 this.deleteForParam("deleteRloe", mapRole);
	}
	/**
	 * 用户列表
	 * @param paginationParam
	 * @return
	 */
	public Pagination<SysUserPo> findUserPage(PaginationParam paginationParam) throws RollbackableBizException{
		return this.pageQuery("selectUserListTotal","selectUserListPage", paginationParam);
	}
	/**
	 * 角色列表
	 * @param paginationParam
	 * @return
	 * @throws RollbackableBizException
	 */
	public Pagination<SysRolePo> findSysRolePagination(PaginationParam paginationParam) throws RollbackableBizException {
		return pageQuery("findUserRoleTotal", "findUserRolePage", paginationParam);
	}
	/**
	 * 根据登录名获得授权资源
	 * @return
	 */
	public List<SysMenuPo> findResourceByLoginInfo(String userId,String loginName,String resourceType,String parentId) throws RollbackableBizException{
		Map<String,String> map = new HashMap<String,String>();
		map.put("isActive", IsActiveEnum.YES.getValue());
		map.put("userId", userId);
		map.put("loginName", loginName);
		map.put("resourceType", resourceType);
		map.put("parentId", parentId);
		return this.findListByParam("selectResByLoginName", map);
	}
	/**
	 * 验证登录名是否存在
	 * @param loginName
	 * @return
	 */
	public boolean findLoginNameCount(String loginName) throws RollbackableBizException{
		Map<String,String> map = new HashMap<String,String>();
		map.put("isActive", IsActiveEnum.YES.getValue());
		map.put("loginName", loginName);
		int size = this.findListByParam("selectLoginNameCount", map).size();
		if(size==0)
			return false;
		else
			return true;
	}
	
	/**
	 * 获取当前在线用户
	 * @return
	 */
	public String findUserNameOnline(){
		SysUserPo shiroUser = (SysUserPo) SecurityUtils.getSubject().getPrincipal();
		if(shiroUser != null) {
			return shiroUser.getLoginName();
		}
		return "";
	}
	
	public List<SysRolePo> findRoleByUserId(String userId) throws RollbackableBizException {
		Map<String, Object> map = new HashMap<String, Object> ();
		map.put("userId", userId);
		return this.findListByParam("findRoleByUserId", map);
	}
	@Override
	public List<AdminDicPo> findAdminDicPoByTerraceType(Map<String, String> map)
			throws RollbackableBizException {
		return this.findListByParam("findAdminDicPoByTerraceType", map);
	}
}
