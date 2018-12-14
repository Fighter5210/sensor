package com.dy.sensor.sys.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.dy.sensor.common.model.ZtreeIconEnum;
import com.dy.sensor.sys.model.po.SysOrganizationPo;
import com.dy.sensor.sys.service.SysOrganizationService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @ClassName: SysOrganizationAction
 * @Description:部门管理
 * @author: myh
 * @date: 2015-1-13 下午2:10:57
 * 
 */
public class SysOrganizationAction extends ActionSupport {

	private static final long serialVersionUID = -8783973185595308634L;
	private SysOrganizationPo sysOrganizationPo;
	private SysOrganizationService SysOrganizationService;
	private Map<String, Object> result;
	private List<Object> list;

	public String toMain() {
		return SUCCESS;
	}

	public String load() {
		try {
			if (sysOrganizationPo != null
					&& sysOrganizationPo.getOrgId() != null)
				sysOrganizationPo = SysOrganizationService
						.load(sysOrganizationPo.getOrgId());
			result = new HashMap<String, Object>();
			result.put("code", "success");
			result.put("data", sysOrganizationPo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String loadTree() {
		try {
			List<?> list1 = this.getSysOrganizationService().loadTree();
			list = new ArrayList<Object>();
			if (list1 != null) {
				for (Object o : list1) {
					Map<String, String> map = new HashMap<String, String>();
					SysOrganizationPo m = (SysOrganizationPo) o;
					map.put("id", m.getOrgId());
					if (m.getParent() != null
							&& StringUtils.isNotEmpty(m.getParent().getOrgId())) {
						map.put("pid", m.getParent().getOrgId());
					} else {
						map.put("pid", "0");
					}
					//map.put("icon", ZtreeIconEnum.ORGAN.getIcon());
					map.put("name", m.getOrgName());
					map.put("open", "true");
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
			sysOrganizationPo = this.getSysOrganizationService().save(
					sysOrganizationPo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String delete() {
		try {
			this.getSysOrganizationService().delete(
					sysOrganizationPo.getOrgId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public SysOrganizationPo getSysOrganizationPo() {
		return sysOrganizationPo;
	}

	public void setSysOrganizationPo(SysOrganizationPo sysOrganizationPo) {
		this.sysOrganizationPo = sysOrganizationPo;
	}

	public SysOrganizationService getSysOrganizationService() {
		return SysOrganizationService;
	}

	public void setSysOrganizationService(
			SysOrganizationService SysOrganizationService) {
		this.SysOrganizationService = SysOrganizationService;
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
}
