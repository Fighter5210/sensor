package com.dy.sensor.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;

import com.dy.sensor.common.exception.RollbackableBizException;
import com.dy.sensor.sys.dao.IUserDao;
import com.dy.sensor.sys.dao.MenuDao;
import com.dy.sensor.sys.model.po.MenuPo;
import com.dy.sensor.sys.model.po.SysMenuPo;
import com.dy.sensor.sys.model.po.SysUserPo;
import com.dy.sensor.sys.model.vo.SysMenuVo;
import com.dy.sensor.sys.service.MenuService;

/**
 * 菜单操作实现类
 * 
 * @description:
 * @author: wangdy
 * @Date: 2014-9-5
 * @modify：
 * @version: 1.0
 * @Company: 高伟达软件股份有限公司
 */
public class MenuServiceImpl implements MenuService {

	private MenuDao menuDao;
	private IUserDao userDao;

	@Override
	public <T extends MenuPo> List<T> selectMenu() throws Exception {
		List<T> list = menuDao.findAll("selectAllMenus");
		return list;
	}

	@Override
	public List<SysMenuPo> selectMenusByParentId(String parentId)
			throws Exception {
		List<SysMenuPo> list = new ArrayList<SysMenuPo>();
		;
		SysUserPo shiroUser = (SysUserPo) SecurityUtils.getSubject()
				.getPrincipal();
		/** 使用缓存存放菜单项 */
		// Cache sysMenuCache = CacheUtil.getSysMenuCache();
		// List sysMenuList = (List)sysMenuCache.get("sysMenu_"+parentId);
		// if(sysMenuList != null && sysMenuList.size() > 0){
		// list = sysMenuList;
		// }else{
		String userId = shiroUser.getUserId();
		if (userId != null && !"".equals(userId)) {
			list = userDao
					.findResourceByLoginInfo(userId, "", "menu", parentId);
		}
		// list = menuDao.findByID("selectMenusByParentId", parentId);
		// sysMenuCache.put("sysMenu_"+parentId, list);
		// }
		return list;
	}

	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * 查询系统导航数据;
	 */
	@Override
	public List<SysMenuVo> selSysMenuVo() throws RollbackableBizException {
		List<SysMenuPo> list = new ArrayList<SysMenuPo>();
		List<SysMenuVo> result = new ArrayList<SysMenuVo>();

		String parentId = "0"; // 父类编号;
		SysUserPo shiroUser = (SysUserPo) SecurityUtils.getSubject()
				.getPrincipal(); // 获取当前登录用户;

		String userId = shiroUser.getUserId(); // 登录用户主键编号;

		if (userId != null && !"".equals(userId)) {
			list = userDao
					.findResourceByLoginInfo(userId, "", null, parentId);
		}
		
		// 查询二级菜单;
		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {
				SysMenuPo parentMenu = list.get(i);
				List<SysMenuPo> childMenu = userDao.findResourceByLoginInfo(
						userId, "", "menu", parentMenu.getId());
				SysMenuVo vo = new SysMenuVo();
				vo.setId(parentMenu.getId());
				vo.setMenuName(parentMenu.getMenuName());
				vo.setMenuUrl(parentMenu.getMenuUrl());
				vo.setParentId(parentMenu.getParentId());
				vo.setImageUrl(parentMenu.getImageUrl());
				vo.setChildMenu(childMenu);
				result.add(vo);
			}
		}

		return result;
	}

}
