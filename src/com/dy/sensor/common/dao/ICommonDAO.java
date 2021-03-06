package com.dy.sensor.common.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.dy.sensor.common.exception.RollbackableBizException;
import com.dy.sensor.common.model.base.BaseBO;
import com.dy.sensor.common.support.Pagination;
import com.dy.sensor.common.support.PaginationParam;


/**
 * @author Spring.Cao
 * @version v1.0 2013-03-22
 */
public interface ICommonDAO {

	/**
	 * 批量插入数据
	 * 
	 * @param method
	 * @param boList
	 * @throws RollbackableBizException
	 */
	public <T extends BaseBO> void batchInsert(String method, List<T> boList)
			throws RollbackableBizException;

	/**
	 * 新增数据
	 * 
	 * @param method
	 * @param bo
	 * @throws RollbackableBizException
	 */
	public <T extends BaseBO> void save(String method, T bo)
			throws RollbackableBizException;

	/**
	 * 物理删除数据
	 * 
	 * @param method
	 * @param bo
	 * @return
	 * @throws RollbackableBizException
	 */
	public <T extends BaseBO> int delete(String method, T bo)
			throws RollbackableBizException;

	/**
	 * 物理删除数据
	 * 
	 * @param method
	 * @param bizId
	 * @return
	 * @throws RollbackableBizException
	 */
	public <T extends BaseBO> int delete(String method, String bizId)
			throws RollbackableBizException;

	/**
	 * 根据多个参数进行删除数据
	 * 
	 * @param method
	 * @param paramMap
	 * @return
	 * @throws RollbackableBizException
	 */
	public <T extends BaseBO> int deleteForParam(String method,
			Map<String, ?> paramMap) throws RollbackableBizException;

	/**
	 * 更新数据
	 * 
	 * @param method
	 * @param bo
	 * @return
	 * @throws RollbackableBizException
	 */
	public <T extends BaseBO> int update(String method, T bo)
			throws RollbackableBizException;

	/**
	 * 查询所有数据集合
	 * 
	 * @param method
	 * @return
	 * @throws RollbackableBizException
	 */
	public <T extends BaseBO> List<T> findAll(String method)
			throws RollbackableBizException;

	/**
	 * 根据ID查询数据
	 * 
	 * @param method
	 * @param bizId
	 * @return
	 * @throws RollbackableBizException
	 */
	public <T extends BaseBO> List<T> findByID(String method, String bizId)
			throws RollbackableBizException;

	/**
	 * 
	 * @Description:findObjectByID(根据ID查询数据)
	 * @param method
	 * @param bizId
	 * @return
	 * @throws RollbackableBizException
	 */
	public <T extends BaseBO> T findObjectByID(String method, String bizId)
			throws RollbackableBizException;

	/**
	 * 
	 * @param method
	 * @param bo
	 * @return
	 * @throws RollbackableBizException
	 */
	public <T extends BaseBO> T callProc(String method, T bo)
			throws RollbackableBizException;

	/**
	 * 
	 * @param method
	 * @return
	 */
	public <T extends Object> List<T> commonQuery(String method);

	/**
	 * 
	 * 根据条件查询集合
	 * 
	 * @param method
	 * @param map
	 * @return List<T>
	 * @exception
	 * @since 1.0.0
	 */
	public <T extends BaseBO> List<T> findListByParam(String method, Map map);

	/**
	 * 
	 * @Title: findListByParam
	 * @Description: 根据对象查询集合
	 * @field: @param method
	 * @field: @param object
	 * @field: @return
	 * @return List<T>
	 * @throws
	 */
	public <T extends BaseBO> List<T> findListByParam(String method,
			Object object) throws RollbackableBizException;

	/**
	 * 
	 * @Description:deleteForIsActive(根据ID逻辑删除数据)
	 * @param method
	 * @param bizId
	 * @return
	 * @throws RollbackableBizException
	 */
	public <T extends BaseBO> int deleteForIsActive(String method, String bizId)
			throws RollbackableBizException;

	/**
	 * @Description:pageQuery(这里用一句话描述这个方法的作用)
	 * @param sqlTotal
	 * @param sqlPage
	 * @param paginationParam
	 * @return
	 */
	public <T> Pagination<T> pageQuery(String sqlTotal, String sqlPage,
			PaginationParam paginationParam);

	/**
	 * 
	 * 根据条件查询集合
	 * 
	 * @param method
	 * @param map
	 * @return List<T>
	 * @exception
	 * @since 1.0.0
	 */
	public <T extends BaseBO> List<T> findListByParam(String method, T bo)
			throws RollbackableBizException;

	/**
	 * 
	 * @Description:findForList(查询Map集合)
	 * @param method
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> findForList(String method)
			throws RollbackableBizException;

	/**
	 * 
	 * @Description:findForList(条件查询，返回Map集合)
	 * @param method
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> findForList(String method, Map map)
			throws RollbackableBizException;

	/**
	 * @Description:findObjectByMap(多条件查询，返回单个Object)
	 * @param method
	 * @param map
	 * @return
	 * @throws RollbackableBizException
	 */
	public <T extends BaseBO> T findObjectByMap(String method, Map map)
			throws RollbackableBizException;

	/**
	 * 获取数据库连接;
	 * 
	 * @return
	 */
	public Connection getDBConnection() throws SQLException;

}
