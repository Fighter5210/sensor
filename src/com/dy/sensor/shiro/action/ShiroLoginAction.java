package com.dy.sensor.shiro.action;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.dy.sensor.common.action.BaseAction;
import com.dy.sensor.common.model.vo.JsonVo;
import com.dy.sensor.shiro.service.PermissionService;
import com.dy.sensor.sys.model.po.SysUserPo;
import com.dy.sensor.sys.tools.Security;

/**
 * Shiro登录认证
 * 
 * @ClassName: ShiroLoginAction
 * @author caohaihong
 * @date 2014-11-27 下午3:47:17
 */
@SuppressWarnings("rawtypes")
public class ShiroLoginAction extends BaseAction {

	private static final long serialVersionUID = 329347674483723005L;

	private String username; // 用户名;
	private String password; // 密码;
	private String idCode; // 验证码;
	private PermissionService permissionService; // 认证业务类接口对象;

	/**
	 * 检测系统用户登录平台;
	 * 
	 * @throws Exception
	 */
	public void login() throws Exception {
		JsonVo json = new JsonVo();

		// 检测用户名不能为空
		if (this.username == null || this.username.equals("")) {
			json.setMsg("用户名不能为空!");
			json.setSuccess(false);
			this.jsonOut(json);
			return;
		}

		// 检测密码不能为空
//		if (password == null || this.password.equals("")) {
//			// return ERROR;
//			json.setMsg("密码不能为空!");
//			json.setSuccess(false);
//			this.jsonOut(json);
//			return;
//		}

		// 检测验证码是否为空
		if (this.idCode == null || this.idCode.equals("")) {
			json.setMsg("验证码不能为空!");
			json.setSuccess(false);
			this.jsonOut(json);
			return;
		} else {
			// 验证验证码输入是否正确;
			Object randIdCode = this.getRequest().getSession().getAttribute("rand");
			if (randIdCode != null) {
				String idCode = randIdCode.toString();
				if (!idCode.equals(this.idCode)) {
					json.setMsg("验证码输入错误，请重新输入！");
					json.setSuccess(false);
					this.jsonOut(json);
					return;
				}
			}
		}

		Security sec = new Security();
		String loginPassword = sec.digestToHex(password.getBytes());
		loginPassword = loginPassword.toUpperCase();
		// 1 根据username 查询用户信息；
		SysUserPo sysUserPo = permissionService.findUserByLoginName(username);
		Subject subject = SecurityUtils.getSubject();
		SysUserPo shiroUser = (SysUserPo) subject.getPrincipal();
		// 如果用户不存在或登录密码不一致则当前已登录用户退出；并返回登录失败
		if (sysUserPo == null || !loginPassword.equals(sysUserPo.getLoginPassword())) {
			subject.logout();
			// return ERROR;
			json.setMsg("用户名/密码不正确，请重新输入!");
			json.setSuccess(false);
			this.jsonOut(json);
			return;
		}

		// 2 检查登录用户和shiro是否同一用户
		if (shiroUser == null || !sysUserPo.getLoginName().equals(shiroUser.getLoginName())) {
			subject.logout();
			UsernamePasswordToken token = new UsernamePasswordToken(username, loginPassword);
			subject.login(token);
		}

		shiroUser = (SysUserPo) subject.getPrincipal();
		// 首先，初始化'是否是超级管理员标识'为false
		shiroUser.setIsManager(false);
		// 超级管理员的role_id默认为1，这是系统初始数据。
		if ("1".equals(shiroUser.getRoleId())) {
			// 如果是超级管理员，则'是否是超级管理员标识'被赋值为ture
			shiroUser.setIsManager(true);
		}

		// return SUCCESS;
		json.setMsg("登录成功!");
		json.setSuccess(true);
		this.jsonOut(json);
		return;
	}

	/**
	 * 系统退出方法;
	 * 
	 * @return
	 */
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return SUCCESS;
	}

	/**
	 * 进入系统主控制界面;
	 * 
	 * @return
	 */
	public String index() {
		return "toIndex";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public PermissionService getPermissionService() {
		return permissionService;
	}

	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

}
