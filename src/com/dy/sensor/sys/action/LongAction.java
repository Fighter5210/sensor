package com.dy.sensor.sys.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;

import com.dy.sensor.common.action.BaseAction;
import com.dy.sensor.sys.model.po.MenuPo;
import com.dy.sensor.sys.service.MenuService;

/**
 * 登录action
 * 
 * @author wangdy
 * 
 */
public class LongAction extends BaseAction {

	/**
	 */
	private static final long serialVersionUID = 1L;

	public LongAction() {
		super();
	}

	private MenuService menuService;

	/**
	 * 显示系统菜单
	 * 
	 * @Title: showMenu
	 * @field: @return
	 * @return String
	 * @throws
	 */
	public String showMenu() {
		try {
			List<MenuPo> menuList = menuService.selectMenu();
			this.getRequest().setAttribute("menuList", menuList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return "menu";
	}

	/**
	 * 显示系统菜单 by parentId
	 * 
	 * @Title: showRootMenus
	 * @field:
	 * @return void
	 * @throws
	 */
	public void showMenusByParentId() {
		try {
			String parentId = this.getRequest().getParameter("parentId");
			List<MenuPo> menuList = menuService.selectMenusByParentId(parentId);
			this.arrayOut(menuList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	/**
	 * 获取系统导航菜单
	 */
	public void selSystemMenu() throws Exception {
		this.arrayOut(this.menuService.selSysMenuVo());
	}

	public void setSession() {
		String firstMenuId = this.getRequest().getParameter("firstMenuId");
		this.getRequest().getSession().setAttribute("firstMenuId", firstMenuId);
		String secondMenuId = this.getRequest().getParameter("secondMenuId");
		this.getRequest().getSession()
				.setAttribute("secondMenuId", secondMenuId);
	}

	public String login() {

		HttpServletRequest request = null;

		String result = "login";

		request = ServletActionContext.getRequest();
		// 此处默认有值
		String username = request.getParameter("username");
		// MD5加密
		String password = com.dy.sensor.sys.tools.CipherUtil
				.generatePassword(request.getParameter("password"));
		// String password = request.getParameter("password");
		UsernamePasswordToken token = new UsernamePasswordToken(username,
				password);

		Subject currentUser = SecurityUtils.getSubject();
		try {
			System.out.println("----------------------------");
			if (!currentUser.isAuthenticated()) {
				// token.setRememberMe(true);
				currentUser.login(token);
			}

			result = "index";
			System.out.println("result: " + result);
		} catch (Exception e) {
			// logger.error(e.getMessage());
			e.printStackTrace();
			result = "login";
		}
		System.out.println("result: " + result);
		return result;

	}

	/**
	 * 登出
	 * 
	 * @return
	 */
	public String logout() {

		Subject currentUser = SecurityUtils.getSubject();
		String result = "logout";
		currentUser.logout();
		return result;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

}
