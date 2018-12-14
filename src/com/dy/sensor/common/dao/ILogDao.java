package com.dy.sensor.common.dao;


import com.dy.sensor.common.exception.RollbackableBizException;
import com.dy.sensor.common.model.LogPo;
import com.dy.sensor.common.support.Pagination;
import com.dy.sensor.common.support.PaginationParam;

/**
 * 
 * @ClassName: ILogDao
 * @Description:日志管理数据层实现类
 * @author: myh
 * @date: 2015-1-15 下午2:32:04
 * 
 */
public interface ILogDao extends ICommonDAO {

	/**
	 * 查询日志列表
	 * 
	 * @Title: findLogPage
	 * 
	 * @field: @param paginationParam
	 * @field: @return
	 * @field: @throws RollbackableBizException
	 * @return Pagination<LogPo>
	 * @throws
	 */
	public Pagination<LogPo> findLogPage(PaginationParam paginationParam)
			throws RollbackableBizException;

	/**
	 * 根据日志Id 查询日志
	 * 
	 * @Title: findLogById
	 * 
	 * @field: @param logId
	 * @field: @return
	 * @field: @throws RollbackableBizException
	 * @return List
	 * @throws
	 */
	public LogPo findLogById(String logId) throws RollbackableBizException;

}