package com.dy.sensor.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dy.sensor.common.dao.ICommonDAO;
import com.dy.sensor.common.exception.RollbackableBizException;
import com.dy.sensor.common.model.AdminParamPo;
import com.dy.sensor.common.service.IParameterService;
import com.dy.sensor.foundation.util.PwdUtil;

/**
 * 查询参数信息SERVICE层实现类
 * @author liangshuang
 * @date 2014-9-17 上午11:22:16
 * @version v1.0
 *
 */
public class ParameterServiceImpl implements IParameterService{

	public ICommonDAO comDAO;

	public ICommonDAO getComDAO() {
		return comDAO;
	}

	public void setComDAO(ICommonDAO comDAO) {
		this.comDAO = comDAO;
	}

	private static Log logger = LogFactory.getLog(ParameterServiceImpl.class);

	@Override
	public <T extends AdminParamPo> List<T> getParams(AdminParamPo po)
			throws Exception {
		logger.info("获取参数信息 Service Begin......");
		HashMap<String,String> map=new HashMap<String,String>();
		map.put("paramId", po.getParamId());
		map.put("paramName", po.getParamName());
		map.put("paramValue", po.getParamValue());
		map.put("encryptParamValue", PwdUtil.encryption(po.getParamValue()));
		List<T> paramList=comDAO.findListByParam("getParams", map);
		for(T param:paramList){
			if(param.getIsEncryption()!=null && 
				!param.getIsEncryption().equals("") && 
				param.getIsEncryption().equals("Y")){
				String strDcpt=PwdUtil.decryption(param.getParamValue());
				param.setParamValue(strDcpt);
			}
		}
		return paramList;
	}
	
	public String getParamValueByName(String paramName) throws RollbackableBizException {
		Map<String, Object> paramMap = new HashMap<String, Object> ();
		paramMap.put("paramName", paramName);
		List<AdminParamPo> paramList = comDAO.findListByParam("getParams", paramMap);
		String paramValue = null;
		if(paramList != null && paramList.size() > 0) {
			paramValue = paramList.get(0).getParamValue();
		}
		return paramValue;
	}

}
