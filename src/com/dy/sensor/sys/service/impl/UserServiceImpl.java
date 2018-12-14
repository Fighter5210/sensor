package com.dy.sensor.sys.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;

import com.dy.sensor.common.exception.RollbackableBizException;
import com.dy.sensor.common.model.AdminDicPo;
import com.dy.sensor.common.support.Pagination;
import com.dy.sensor.common.support.PaginationParam;
import com.dy.sensor.foundation.util.CollectionUtil;
import com.dy.sensor.foundation.util.DateUtil;
import com.dy.sensor.foundation.util.UUIDGenerator;
import com.dy.sensor.shiro.realm.ShiroDbRealm;
import com.dy.sensor.sys.dao.IUserDao;
import com.dy.sensor.sys.model.po.SysMenuPo;
import com.dy.sensor.sys.model.po.SysMenuRolePo;
import com.dy.sensor.sys.model.po.SysRolePo;
import com.dy.sensor.sys.model.po.SysUserDevicePo;
import com.dy.sensor.sys.model.po.SysUserPo;
import com.dy.sensor.sys.model.po.SysUserRole;
import com.dy.sensor.sys.model.vo.SysRoleVo;
import com.dy.sensor.sys.model.vo.SysUserVo;
import com.dy.sensor.sys.service.IUserService;
import com.dy.sensor.sys.tools.Security;
import com.google.common.collect.Maps;

public class UserServiceImpl implements IUserService{
	private static Log logger = LogFactory.getLog(UserServiceImpl.class);
	private static String mat = "yyyy-MM-dd HH:mm:ss";
	private static Security sec = new Security();
	private IUserDao  userDao;
	
	/**
	 * 保存用户信息
	 * @param sysUserPo
	 * @return
	 * @throws RollbackableBizException
	 */
	public String saveUser(SysUserPo sysUserPo,String loginNameOld,String loginPassOld) throws RollbackableBizException{
		try {		
			String time = DateUtil.format(new Date(), mat);
			String pass = sysUserPo.getLoginPassword();
			
			loginNameOld = loginNameOld==null?"":loginNameOld;
			if(!loginNameOld.equals(sysUserPo.getLoginName())){
				if(userDao.findLoginNameCount(sysUserPo.getLoginName())){
					return "faile";
				}
			}
			if(null==sysUserPo.getUserId()||sysUserPo.getUserId().length()==0){//新增
				sysUserPo.setUserId(UUIDGenerator.getUUID());
				sysUserPo.setCreateDatetime(DateUtil.parse(time, mat));
				if(pass.length()>0){
				 sysUserPo.setLoginPassword(sec.digestToHex(pass.getBytes()));
				}
				userDao.saveUser(sysUserPo);
			}else{//更新
				if(pass.length()>0&&!pass.equals(loginPassOld)){
					 sysUserPo.setLoginPassword(sec.digestToHex(pass.getBytes()));
			   }
				sysUserPo.setUpdateDatetime(DateUtil.parse(time, mat));
				userDao.updateUser(sysUserPo);
			}
		} catch (Exception e) {
			logger.error("保存用户信息时发生异常:"+e.getMessage());
		}
		return sysUserPo.getUserId();
	}
	
	/**
	 * 删除用户信息
	 * @param userId
	 * @return
	 * @throws RollbackableBizException
	 */
	public String deleteUser(String userId) throws RollbackableBizException{
		String result = "1";
		try {
			userDao.deleteUser(userId);
		} catch (Exception e) {
			result = "0";
			logger.error("删除用户信息前发生异常:"+e.getMessage());
		}
		return result;
	}
	
	/**
	 * 根据主键获得用户信息
	 * @param userId
	 * @throws RollbackableBizException
	 */
	public SysUserVo findUserById(String userId) throws RollbackableBizException{
		return userDao.findUserById(userId);
	}
	
	/**
	 * 根据机构ID获得所有用户
	 * @param orgId
	 * @throws RollbackableBizException
	 */
	public List<SysUserVo> findUserByOrgId(String orgId) throws RollbackableBizException{
		return userDao.findUserByOrgId(orgId);
	}
	
	 /**
	  * 获得用户列表
	  * @param paginationParam
	  * @return
	  */
	public Pagination<SysUserPo> findUserPage(PaginationParam paginationParam) throws RollbackableBizException{
		return userDao.findUserPage(paginationParam);
	}
	
	/**
	 * 保存授予角色
	 * @param roleList
	 * @throws RollbackableBizException
	 */
	public void saveRoleList(List<SysUserRole> roleList) throws RollbackableBizException {
		if(CollectionUtil.hasContent(roleList)){
			userDao.saveRoleList(roleList);
			RealmSecurityManager securityManager =  
			      (RealmSecurityManager) SecurityUtils.getSecurityManager();  
					ShiroDbRealm shiroDbRealm = (ShiroDbRealm)securityManager.getRealms().iterator().next(); 
					shiroDbRealm.clearAllCachedAuthorizationInfo();
		}
	}
	/**
	 * 取消授予角色
	 * @param userId
	 * @param roleId
	 * @throws RollbackableBizException
	 */
	public void deleteRoleList(String userId,String roleId) throws RollbackableBizException {
		Map<String,Object> mapRole = new HashMap<String,Object>();
		mapRole.put("userId", userId);
		mapRole.put("roleId", roleId);
		userDao.deleteRoleList(mapRole);
		RealmSecurityManager securityManager =  
		      (RealmSecurityManager) SecurityUtils.getSecurityManager();  
				ShiroDbRealm shiroDbRealm = (ShiroDbRealm)securityManager.getRealms().iterator().next();  
				//更新用户授权信息缓存.
//				shiroDbRealm.clearCachedAuthorizationInfo((String)SecurityUtils.getSubject().getPrincipal());
				shiroDbRealm.clearAllCachedAuthorizationInfo(); //清除所有用户授权信息缓存
	}
	
	/**
	 * 角色列表
	 * @param paginationParam
	 * @return
	 * @throws RollbackableBizException
	 */
	public Pagination<SysRolePo> findSysRolePagination(PaginationParam paginationParam) throws RollbackableBizException {
		return userDao.findSysRolePagination(paginationParam);
	}
	
	/**
	 * @param userId null but loginName not null
	 * @param loginName null but userId not null
	 * @param resourceType not null
	 * @param parentId null
	 * 根据登录名获得授权资源
	 */
	public List<SysMenuPo> findResourceByLoginInfo(String userId,String loginName,String resourceType,String parentId) throws RollbackableBizException{
		if(null!=loginName && loginName.length()>0)
			loginName = loginName.toLowerCase();
		
		return userDao.findResourceByLoginInfo(userId,loginName,resourceType,parentId);
	}
	
	/**
	 * 获取当前在线用户
	 * @return
	 */
	public String findUserNameOnline(){
		return userDao.findUserNameOnline();
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<AdminDicPo> findAdminDicPoByTerraceType(String dicTypeCode)
			throws RollbackableBizException {
		Map<String,String> map = Maps.newHashMap();
		map.put("dicTypeCode", dicTypeCode);
		return userDao.findAdminDicPoByTerraceType(map);
	}

}
