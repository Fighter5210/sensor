package com.dy.sensor.sys.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dy.sensor.common.action.BaseAction;
import com.dy.sensor.sys.model.po.SysParamPo;
import com.dy.sensor.sys.model.vo.SysParamVo;
import com.dy.sensor.sys.service.ISysParamService;

public class SysParamAction extends BaseAction<Object> {
	private static final long serialVersionUID = 1L;
	private static Log logger = LogFactory.getLog(SysParamAction.class);
	private ISysParamService sysParamService;
	private SysParamPo sysParamPo;
	private SysParamVo sysParamVo;

	public String updateSysParamPage() {
		return "success";
	}

	public void updateSysParam() {
		try {
			String result = sysParamService.updateSysParam(sysParamPo);
			this.stringOut(result);
		} catch (Exception e) {
			logger.error("操作异常:" + e.getMessage());
		}
	}

	public void findSysParamById() throws Exception {
		String id = this.getRequest().getParameter("id");
		SysParamVo vo = sysParamService.findSysParamById(id);
		this.jsonOut(vo);
	}

	public synchronized SysParamPo getSysParamPo() {
		return sysParamPo;
	}

	public synchronized void setSysParamPo(SysParamPo sysParamPo) {
		this.sysParamPo = sysParamPo;
	}

	public synchronized SysParamVo getSysParamVo() {
		return sysParamVo;
	}

	public synchronized void setSysParamVo(SysParamVo sysParamVo) {
		this.sysParamVo = sysParamVo;
	}

	public synchronized void setSysParamService(ISysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}


}
