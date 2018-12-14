package com.dy.sensor.common.action;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dy.sensor.common.model.LogPo;
import com.dy.sensor.common.service.ILogService;

/**
 * 
 * @ClassName: LogAction
 * @Description:日志管理控制层;
 * @author: myh
 * @date: 2015-1-15 下午2:17:22
 * 
 */
public class LogAction extends BaseAction<Object> {
	private static final long serialVersionUID = 1L;
	private static Log logger = LogFactory.getLog(LogAction.class);
	private ILogService logService;
	private LogPo logPo = new LogPo();

	public void setLogService(ILogService logService) {
		this.logService = logService;
	}

	/**
	 * 主页
	 * 
	 * @return
	 */
	public String index() {
		return "index";
	}

	/**
	 * 查询日志
	 * 
	 * @Title: findLogPage
	 * 
	 * @field: @throws Exception
	 * @return void
	 * @throws
	 */
	public void findLogPage() throws Exception {
		this.jsonOut(logService.findLogPage(this.getPaginationParam()));
	}

	/**
	 * 按logId查询日志
	 * 
	 * @Title: findLogById
	 * 
	 * @field: @throws Exception
	 * @return void
	 * @throws
	 */
	public void findLogById() throws Exception {
		LogPo po = logService.findLogById(logPo.getId());
		this.jsonOut(po);
	}

}
