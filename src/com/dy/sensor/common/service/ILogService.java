package com.dy.sensor.common.service;


import com.dy.sensor.common.exception.RollbackableBizException;
import com.dy.sensor.common.model.LogPo;
import com.dy.sensor.common.support.Pagination;
import com.dy.sensor.common.support.PaginationParam;

/**
 * 
 * @ClassName: ILogService
 * @Description:日志管理业务层接口
 * @author: myh
 * @date: 2015-1-15 下午2:32:55
 * 
 */
public interface ILogService {

	/**
	 * 查询日志
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
	 * 根据 findLogById 查询日志
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
