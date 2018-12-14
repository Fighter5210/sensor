package com.dy.sensor.sys.service;

import java.util.List;

import com.dy.sensor.common.exception.RollbackableBizException;
import com.dy.sensor.sys.model.po.MenuPo;
import com.dy.sensor.sys.model.vo.SysMenuVo;

/**
 * 菜单操作接口
 * 
 * @description:
 * @author: wangdy
 * @Date: 2014-9-5
 * @modify：
 * @version: 1.0
 * @Company: 高伟达软件股份有限公司
 */

public interface MenuService {

	/**
	 * 显示系统菜单
	 */
	public <T extends MenuPo> List<T> selectMenu() throws Exception;

	/**
	 * 显示系统菜单 by parentId
	 */
	public <T extends MenuPo> List<T> selectMenusByParentId(String parentId)
			throws Exception;

	/**
	 * 查询系统导航菜单;
	 * 
	 * @return
	 */
	public List<SysMenuVo> selSysMenuVo() throws RollbackableBizException;
}
