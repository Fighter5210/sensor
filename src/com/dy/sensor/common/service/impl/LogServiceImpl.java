package com.dy.sensor.common.service.impl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dy.sensor.common.dao.ILogDao;
import com.dy.sensor.common.exception.RollbackableBizException;
import com.dy.sensor.common.model.LogPo;
import com.dy.sensor.common.service.ILogService;
import com.dy.sensor.common.support.Pagination;
import com.dy.sensor.common.support.PaginationParam;

/**
 * 
 * @ClassName: LogServiceImpl
 * @Description:日志管理业务层实现类
 * @author: myh
 * @date: 2015-1-15 下午2:33:20
 * 
 */
public class LogServiceImpl implements ILogService {

	private static Log logger = LogFactory.getLog(LogServiceImpl.class);
	private ILogDao logDao;
	private static String mat = "yyyy-MM-dd HH:mm:ss";

	@Override
	public Pagination<LogPo> findLogPage(PaginationParam paginationParam)
			throws RollbackableBizException {
		return logDao.findLogPage(paginationParam);
	}

	@Override
	public LogPo findLogById(String logId) throws RollbackableBizException {
		return logDao.findLogById(logId);
	}

	public void setLogDao(ILogDao logDao) {
		this.logDao = logDao;
	}

}
