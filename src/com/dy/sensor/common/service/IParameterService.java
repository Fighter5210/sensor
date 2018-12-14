package com.dy.sensor.common.service;

import java.util.List;

import com.dy.sensor.common.exception.RollbackableBizException;
import com.dy.sensor.common.model.AdminParamPo;
import com.dy.sensor.common.service.IService;

/**
 * 查询参数信息SERVICE层接口类
 * @author liangshuang
 * @date 2014-9-17 上午11:22:16
 * @version v1.0
 *
 */
public interface IParameterService extends IService {

	/**
	 * 查询参数信息，查询条件包括参数ID、参数名、参数值
	 * @param po 查询条件
	 * @return
	 * @throws Exception
	 */
	public <T extends AdminParamPo> List<T> getParams(AdminParamPo po) throws Exception;
	
	/**
	 * 根据参数名称获取参数值
	 * @Title: getParamValueByName
	 * @Description: TODO
	 * @field: @param paramName
	 * @field: @return
	 * @field: @throws RollbackableBizException
	 * @return String
	 * @throws
	 */
	public String getParamValueByName(String paramName) throws RollbackableBizException;

}
