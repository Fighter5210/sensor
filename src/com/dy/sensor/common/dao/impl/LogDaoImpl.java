package com.dy.sensor.common.dao.impl;


import java.util.HashMap;
import java.util.Map;

import com.dy.sensor.common.dao.ILogDao;
import com.dy.sensor.common.exception.RollbackableBizException;
import com.dy.sensor.common.model.LogPo;
import com.dy.sensor.common.support.Pagination;
import com.dy.sensor.common.support.PaginationParam;

/**
 * 
 * @ClassName: LogDaoImpl
 * @Description:日志管理数据层实现类
 * @author: myh
 * @date: 2015-1-15 下午2:32:33
 * 
 */
public class LogDaoImpl extends CommonDAOImpl implements ILogDao {

	public Pagination<LogPo> findLogPage(PaginationParam paginationParam)
			throws RollbackableBizException {
		return this.pageQuery("queryLogListTotal", "queryLogListPage",
				paginationParam);
	}

	public LogPo findLogById(String logId) throws RollbackableBizException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("logId", logId);
		return this.findObjectByMap("queryLogByParams", map);
	}
}
