package com.dy.sensor.sys.service;

import java.util.List;
import java.util.Map;

import com.dy.sensor.common.exception.RollbackableBizException;
import com.dy.sensor.sys.model.po.SysOrganizationPo;

/**
 * 组织部门管理业务类接口;
 * 
 * @ClassName: SysOrganizationService
 * @Description:
 * @author: myh
 * @date: 2015-3-10 上午11:27:19
 * 
 */
public interface SysOrganizationService {

	/**
	 * 加载单个部门对象;
	 * 
	 * @param id
	 * @return
	 * @throws RollbackableBizException
	 */
	public SysOrganizationPo load(String id) throws RollbackableBizException;

	/**
	 * 搜索;
	 * 
	 * @param para
	 * @return
	 * @throws RollbackableBizException
	 */
	public List search(Map para) throws RollbackableBizException;

	/**
	 * 加载树;
	 * 
	 * @return
	 * @throws RollbackableBizException
	 */
	public List<Object> loadTree() throws RollbackableBizException;

	/**
	 * 添加部门;
	 * 
	 * @param sysOrganizationPo
	 * @return
	 * @throws RollbackableBizException
	 */
	public SysOrganizationPo save(SysOrganizationPo sysOrganizationPo)
			throws RollbackableBizException;

	/**
	 * 删除部门
	 * 
	 * @param id
	 * @throws RollbackableBizException
	 */
	public void delete(String id) throws RollbackableBizException;
}
