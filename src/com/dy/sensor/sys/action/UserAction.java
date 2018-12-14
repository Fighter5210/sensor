package com.dy.sensor.sys.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dy.sensor.common.action.BaseAction;
import com.dy.sensor.common.exception.RollbackableBizException;
import com.dy.sensor.sys.model.po.SysUserPo;
import com.dy.sensor.sys.model.po.SysUserRole;
import com.dy.sensor.sys.model.vo.SysUserVo;
import com.dy.sensor.sys.service.IUserService;

/**
 * 
 * @ClassName: UserAction
 * @Description:用户管理控制器;
 * @author: myh
 * @date: 2015-1-20 上午9:43:34
 * 
 */
public class UserAction extends BaseAction<Object> {
	private static final long serialVersionUID = 1L;
	private static Log logger = LogFactory.getLog(UserAction.class);
	private IUserService userService;
	private SysUserPo sysUserPo;
	private SysUserVo sysUserVo;

	/**
	 * 用户管理界面
	 * 
	 * @throws Exception
	 */
	public String findUserList() {
		return "success";
	}

	/**
	 * 保存用户
	 * 
	 * @throws Exception
	 */
	public void saveUser() {
		try {
			String loginNameOld = this.getRequest()
					.getParameter("loginNameOld");
			String loginPassOld = this.getRequest()
					.getParameter("loginPassOld");
			String result = userService.saveUser(sysUserPo, loginNameOld,
					loginPassOld);
			this.stringOut(result);
		} catch (Exception e) {
			logger.error("操作异常:" + e.getMessage());
		}
	}

	/**
	 * 删除用户
	 * 
	 * @throws Exception
	 */
	public void deleteUser() {
		try {
			String userId = this.getRequest().getParameter("userId");
			String result = userService.deleteUser(userId);
			this.stringOut(result);
		} catch (Exception e) {
			logger.error("操作异常:" + e.getMessage());
		}
	}

	/**
	 * 根据ID获取用户信息
	 * 
	 * @throws Exception
	 */
	public void findUserById() throws Exception {
		String userId = this.getRequest().getParameter("userId");
		SysUserVo vo = userService.findUserById(userId);
		this.jsonOut(vo);
	}

	/**
	 * 根据机构获得所有用户
	 * 
	 * @param orgId
	 * @throws RollbackableBizException
	 */
	public void findUserByOrgId() throws Exception {
		String orgId = this.getRequest().getParameter("orgId");
		List<SysUserVo> list = userService.findUserByOrgId(orgId);
		this.arrayOut(list);
	}

	/**
	 * 用户列表
	 * 
	 * @throws Exception
	 */
	public void findUserPage() throws Exception {
		this.jsonOut(userService.findUserPage(this.getPaginationParam()));
	}

	/**
	 * 保存授予角色
	 * 
	 * @param roleList
	 * @throws RollbackableBizException
	 */
	public void saveAutoRole() throws RollbackableBizException {
		String userId = this.getRequest().getParameter("userId");
		String roleIds = this.getRequest().getParameter("roleIds");
		List<SysUserRole> roleList = new ArrayList<SysUserRole>();

		for (String roleId : roleIds.split(",")) {
			SysUserRole userRole = new SysUserRole(userId, roleId);
			roleList.add(userRole);
		}
		userService.saveRoleList(roleList);
	}

	/**
	 * 取消授予角色
	 * 
	 * @param userId
	 * @param roleId
	 * @throws RollbackableBizException
	 */
	public void calcleAutoRole() throws RollbackableBizException {
		String userId = this.getRequest().getParameter("userId");
		String roleIds = this.getRequest().getParameter("roleIds");

		for (String roleId : roleIds.split(",")) {
			userService.deleteRoleList(userId, roleId);
		}
	}


	/**
	 * 查询角色列表
	 * 
	 * @throws Exception
	 */
	public void findSysRoleList() throws Exception {
		this.jsonOut(userService.findSysRolePagination(this
				.getPaginationParam()));
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public SysUserPo getSysUserPo() {
		return sysUserPo;
	}

	public void setSysUserPo(SysUserPo sysUserPo) {
		this.sysUserPo = sysUserPo;
	}

	public SysUserVo getSysUserVo() {
		return sysUserVo;
	}

	public void setSysUserVo(SysUserVo sysUserVo) {
		this.sysUserVo = sysUserVo;
	}
}
