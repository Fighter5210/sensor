package com.dy.sensor.common.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.dy.sensor.common.dao.ICommonDAO;
import com.dy.sensor.common.exception.RollbackableBizException;
import com.dy.sensor.common.model.base.BaseBO;
import com.dy.sensor.common.support.Pagination;
import com.dy.sensor.common.support.PaginationParam;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * @author Spring.Cao
 * @version v1.0 2013-03-22
 */
public class CommonDAOImpl extends SqlMapClientDaoSupport implements ICommonDAO {

	private static Log logger = LogFactory.getLog(CommonDAOImpl.class);

	@Autowired
	public CommonDAOImpl(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}

	public CommonDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public <T extends BaseBO> void batchInsert(String method, List<T> boList)
			throws RollbackableBizException {
		getSqlMapClientTemplate().insert(method, boList);
	}

	/**
	 * 
	 * @Title: batchUpdate
	 * @Description: 根据对象列表，批量更新
	 * @param method
	 * @param boList
	 * @throws RollbackableBizException
	 *             void 返回类型
	 * @throws
	 */
	public <T extends BaseBO> void batchUpdate(String method, List<T> boList)
			throws RollbackableBizException {
		getSqlMapClientTemplate().update(method, boList);
	}

	@Override
	public <T extends BaseBO> void save(String method, T bo)
			throws RollbackableBizException {
		bo.setCreateDatetime(new Date());
		getSqlMapClientTemplate().insert(method, bo);
	}

	@Override
	public <T extends BaseBO> int delete(String method, T bo)
			throws RollbackableBizException {
		String bizId = String.valueOf(bo.getBizId());
		System.out.println("id : " + bizId);
		return getSqlMapClientTemplate().delete(method, bizId);
	}

	@Override
	public <T extends BaseBO> int update(String method, T bo)
			throws RollbackableBizException {
		bo.setUpdateDatetime(new Date());
		return getSqlMapClientTemplate().update(method, bo);
	}

	@Override
	public <T extends BaseBO> List<T> findAll(String method)
			throws RollbackableBizException {
		List<T> list = getSqlMapClientTemplate().queryForList(method);
		return list;
	}

	@Override
	public <T extends BaseBO> List<T> findByID(String method, String bizId)
			throws RollbackableBizException {
		List<T> list = getSqlMapClientTemplate().queryForList(method, bizId);
		return list;
	}

	@Override
	public <T extends BaseBO> T callProc(String method, T bo)
			throws RollbackableBizException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> commonQuery(String method) {
		return this.getSqlMapClientTemplate().queryForList(method);
	}

	@Override
	public <T extends BaseBO> int delete(String method, String bizId)
			throws RollbackableBizException {
		System.out.println("id : " + bizId);
		return getSqlMapClientTemplate().delete(method, bizId);
	}

	public <T extends BaseBO> int deleteForParam(String method,
			Map<String, ?> paramMap) throws RollbackableBizException {
		return getSqlMapClientTemplate().delete(method, paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> Pagination<T> pageQuery(String sqlTotal, String sqlPage,
			PaginationParam paginationParam) {
		int record = (Integer) getSqlMapClientTemplate().queryForObject(
				sqlTotal, paginationParam.getParams());
		List<T> list = (List<T>) getSqlMapClientTemplate().queryForList(
				sqlPage, paginationParam.getParams());
		Pagination<T> pagination = new Pagination<T>(paginationParam);
		pagination.setRecord(record);
		int total = pagination.getRecord() / pagination.getRows()
				+ (pagination.getRecord() % pagination.getRows() == 0 ? 0 : 1);
		pagination.setTotal(total);
		pagination.setDataList(list);
		return pagination;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dy.sensor.common.service.base.ICommonDAO#findObjectByID(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	public <T extends BaseBO> T findObjectByID(String method, String bizId)
			throws RollbackableBizException {

		return (T) getSqlMapClientTemplate().queryForObject(method, bizId);
	}

	@Override
	public <T extends BaseBO> List<T> findListByParam(String method, Map map) {

		List<T> list = getSqlMapClientTemplate().queryForList(method, map);

		return list;
	}

	@Override
	public <T extends BaseBO> int deleteForIsActive(String method, String bizId)
			throws RollbackableBizException {

		return getSqlMapClientTemplate().update(method, bizId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dy.sensor.common.dao.ICommonDAO#findListByParam(java.lang.String,
	 * com.dy.sensor.common.model.base.BaseBO)
	 */
	@Override
	public <T extends BaseBO> List<T> findListByParam(String method, T bo)
			throws RollbackableBizException {
		List<T> list = getSqlMapClientTemplate().queryForList(method, bo);
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dy.sensor.common.dao.ICommonDAO#findForList(java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> findForList(String method)
			throws RollbackableBizException {
		List<Map<String, Object>> list = getSqlMapClientTemplate()
				.queryForList(method);
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dy.sensor.common.dao.ICommonDAO#findForList(java.lang.String,
	 * java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> findForList(String method, Map map)
			throws RollbackableBizException {
		List<Map<String, Object>> list = getSqlMapClientTemplate()
				.queryForList(method, map);
		return list;
	}

	@Override
	public <T extends BaseBO> T findObjectByMap(String method, Map map)
			throws RollbackableBizException {
		return (T) getSqlMapClientTemplate().queryForObject(method, map);
	}

	@Override
	public <T extends BaseBO> List<T> findListByParam(String method,
			Object object) throws RollbackableBizException {

		@SuppressWarnings("unchecked")
		List<T> list = getSqlMapClientTemplate().queryForList(method, object);
		return list;
	}

	/**
	 * 获取数据库连接地址;
	 */
	@Override
	public Connection getDBConnection() throws SQLException {

		Connection con = this.getSqlMapClientTemplate().getDataSource()
				.getConnection();
		con.setAutoCommit(false);

		return con;

	}

}
