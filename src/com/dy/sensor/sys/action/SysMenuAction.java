package com.dy.sensor.sys.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.dy.sensor.common.model.ZtreeIconEnum;
import com.dy.sensor.sys.model.po.SysMenuPo;
import com.dy.sensor.sys.model.po.SysMenuRolePo;
import com.dy.sensor.sys.model.po.SysUserPo;
import com.dy.sensor.sys.service.ISysRoleService;
import com.dy.sensor.sys.service.SysMenuService;
import com.opensymphony.xwork2.ActionSupport;

public class SysMenuAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8783973185595308634L;
	private SysMenuPo sysMenuPo;
	private SysMenuService SysMenuService;
	private Map<String, Object> result;
	private List<Object> list;
	private String roleId;
	private ISysRoleService sysRoleService;

	public String toMain() {
		return SUCCESS;
	}

	/**
	 * 查詢包含指定菜单的角色
	 * 
	 * @return
	 */
	public String roles() {
		try {
			Subject subject = SecurityUtils.getSubject();
			SysUserPo sysUserPo = (SysUserPo) subject.getPrincipal();
			if (subject.isPermitted("cdgl")) {
				System.out.println("有权限");
				// 有权限
			} else {
				System.out.println("无权限");
				// 无权限
			}
			if (sysMenuPo != null && sysMenuPo.getId() != null) list = SysMenuService.roles(sysMenuPo.getId());
			result = new HashMap<String, Object>();
			result.put("code", "success");
			result.put("data", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String load() {
		try {
			if (sysMenuPo != null && sysMenuPo.getId() != null) sysMenuPo = SysMenuService.load(sysMenuPo.getId());
			result = new HashMap<String, Object>();
			result.put("code", "success");
			result.put("data", sysMenuPo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String loadTree() {
		try {
			List<?> list1 = this.getSysMenuService().loadTree();
			list = new ArrayList<Object>();
			if (list1 != null) {
				for (Object o : list1) {
					Map<String, String> map = new HashMap<String, String>();
					SysMenuPo m = (SysMenuPo) o;
					map.put("id", m.getId());
					if (m.getParent() != null && !"0".equals(m.getParent().getId())
							&& "menu".equals(m.getResourceType())) {
						//map.put("icon", ZtreeIconEnum.TWOLEVELMENU.getIcon());
						map.put("pid", m.getParent().getId());
					} else if (m.getParent() != null && "function".equals(m.getResourceType())) {
						//map.put("icon", ZtreeIconEnum.FUNCTION.getIcon());
						map.put("pid", m.getParent().getId());
					} else if (m.getParent() != null && "0".equals(m.getParent().getId())
							&& "menu".equals(m.getResourceType())) {

						//map.put("icon", ZtreeIconEnum.ONELEVELMENU.getIcon());
						map.put("pid", m.getParent().getId());
					}
					map.put("name", m.getMenuName());
					map.put("open", "true");
					list.add(map);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public String loadtreeNew() {
		try {
			List<SysMenuPo> list1 = sysRoleService.findSysMenuPoByRoleId(getRoleId());

			list = new ArrayList<Object>();
			if (list1 != null) {
				for (Object o : list1) {
					Map<String, String> map = new HashMap<String, String>();
					SysMenuPo m = (SysMenuPo) o;
					map.put("id", m.getId());
					if (m.getParent() != null && !"0".equals(m.getParent().getId())
							&& "menu".equals(m.getResourceType())) {
						//map.put("icon", ZtreeIconEnum.TWOLEVELMENU.getIcon());
						map.put("pid", m.getParent().getId());
					} else if (m.getParent() != null && "function".equals(m.getResourceType())) {
						//map.put("icon", ZtreeIconEnum.FUNCTION.getIcon());
						map.put("pid", m.getParent().getId());
					} else if (m.getParent() != null && "0".equals(m.getParent().getId())
							&& "menu".equals(m.getResourceType())) {

						//map.put("icon", ZtreeIconEnum.ONELEVELMENU.getIcon());
						map.put("pid", m.getParent().getId());
					}
					
					Boolean checked= m.getChecked();
					String menuId= m.getMenuId();
					if (menuId !=null || checked==true ){
						map.put("checked", "true");
					}else{
						map.put("checked", "false");
					}
					map.put("name", m.getMenuName());
					//map.put("open", "true");
					
					list.add(map);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public String save() {
		try {
			sysMenuPo = this.getSysMenuService().save(sysMenuPo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String delete() {
		try {
			this.getSysMenuService().delete(sysMenuPo.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public SysMenuPo getSysMenuPo() {
		return sysMenuPo;
	}

	public void setSysMenuPo(SysMenuPo sysMenuPo) {
		this.sysMenuPo = sysMenuPo;
	}

	public SysMenuService getSysMenuService() {
		return SysMenuService;
	}

	public void setSysMenuService(SysMenuService SysMenuService) {
		this.SysMenuService = SysMenuService;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public ISysRoleService getSysRoleService() {
		return sysRoleService;
	}

	public void setSysRoleService(ISysRoleService sysRoleService) {
		this.sysRoleService = sysRoleService;
	}

}
