package com.dy.sensor.common.interceptor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.ProceedingJoinPoint;

import com.dy.sensor.common.dao.ILogDao;
import com.dy.sensor.common.model.LogPo;
import com.dy.sensor.foundation.util.UUIDGenerator;
import com.dy.sensor.sys.model.po.SysUserPo;

/**
 * 系统日志拦截器;
 * 
 * @ClassName: IcmsInterceptor
 * @Description:
 * @author: myh
 * @date: 2015-3-9 下午3:47:44
 * 
 */
public class IcmsInterceptor {

	private ILogDao logDao;

	public Object doBasicProfiling(ProceedingJoinPoint joinPoint)
			throws Throwable {
		// 根据类名确定模块名称
		String clazzName = joinPoint.getTarget().getClass().getName();
		String moduleName = "";
		String moduleType = "";
		String operateName = "";
		String operateType = "";
		String operateContent = "";
		boolean isRecord = true;
		ModuleModel module = SysOperLogMap.getSysOperLogMap().getModuleModel(
				clazzName);
		if (module != null) { // 若为空，则未配置拦截此类
			moduleName = module.getModuleName();
			moduleType = module.getModuleCode();

			// 根据方法名确定操作类型
			String methodName = joinPoint.getSignature().getName();
			OperateModel operate = SysOperLogMap.getSysOperLogMap()
					.getOperateModel(module, methodName);
			if (operate != null) { // 若为空，则未配置拦截此方法
				operateName = operate.getOperateName();
				operateType = operate.getOperateType();

				// 根据参数解析操作内容
				Object[] obj = joinPoint.getArgs();

				if (operateType.equals(OperateEnum.INSERT.getValue())) {
					operateContent = ResolveObjectUtils.getObjectFieldString(
							obj.length == 1 ? obj[0] : obj[0], null);
				} else if (operateType.equals(OperateEnum.UPDATE.getValue())) {
					if (obj.length > 0) {
						Object hisObj = this.queryBusinessObject(operate,
								joinPoint.getTarget(), obj[0]);
						operateContent = ResolveObjectUtils
								.getObjectDifferentFieldString(hisObj, obj[0],
										null);
					}

				} else if (operateType.equals(OperateEnum.DELETE.getValue())) {
					if (obj.length > 0) {
						String businessNames = "";
						List<String> pkList = this.getBusinessPkList(obj[0]);
						Object businessObj;
						int len = pkList == null ? 0 : pkList.size();
						for (int i = 0; i < len; i++) {
							businessObj = this.queryBusinessObject(operate,
									joinPoint.getTarget(), pkList.get(i));
							businessNames += "、"
									+ this.getObjectValue(businessObj,
											operate.getBusinessName());
						}
						if (businessNames.length() > 0) {
							operateContent = "删除了【"
									+ businessNames.substring(1) + "】";
						}
					}
				} else if (operateType.equals(OperateEnum.SPECIAL.getValue())) {
					operateContent = this.getSpecialContent(
							joinPoint.getTarget(), operate, obj);
				}
			} else {
				isRecord = false;
			}
		} else {
			isRecord = false;
		}
		Object result = null;
		try {
			result = joinPoint.proceed();
			if (isRecord) {
//				insertSystemLog(moduleName, moduleType, operateName,
//						operateType, operateContent);
			}
		} catch (Exception e) {
			// throw new Exception(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	private Object queryBusinessObject(OperateModel operate,
			Object targetObject, Object paramObj) {
		Object obj = null; // 业务对象
		try {
			Object value = null; // 业务主键Id
			if (paramObj instanceof String) { // 如果传入的对象是String，可以直接做为业务主键Id
				value = paramObj;
			} else { // 默认传入的是业务对象，如下获取业务主键Id
				value = getObjectValue(paramObj, operate.getPkId());
			}
			// 查询业务对象
			Method method = targetObject.getClass().getMethod(
					operate.getGetBeanMethod(), new Class[] { String.class });
			obj = method.invoke(targetObject, new Object[] { value });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	private Object getObjectValue(Object paramObj, String key) {
		Object value = null;
		try {
			String getter = "get" + key.substring(0, 1).toUpperCase()
					+ key.substring(1);
			Method method = paramObj.getClass().getMethod(getter,
					new Class[] {});
			value = method.invoke(paramObj, new Object[] {});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	@SuppressWarnings("unchecked")
	private List<String> getBusinessPkList(Object paramObj) {
		List<String> pkList = null;
		if (paramObj instanceof List) {
			pkList = (List<String>) paramObj;
		} else if (paramObj instanceof String) {
			String pkStr = (String) paramObj;
			String[] pkArr = pkStr.split(",");
			pkList = new ArrayList<String>();
			for (int i = 0; i < pkArr.length; i++) {
				pkList.add(pkArr[i]);
			}
		} else if (paramObj instanceof String[]) {
			String[] pkArr = (String[]) paramObj;
			pkList = new ArrayList<String>();
			for (int i = 0; i < pkArr.length; i++) {
				pkList.add(pkArr[i]);
			}
		}
		return pkList;
	}

	@SuppressWarnings("rawtypes")
	private String getSpecialContent(Object targetObject, OperateModel operate,
			Object[] args) {
		String operateContent = "";
		Object[] paramArr = null;
		Class[] clazzArr = null;
		if ("".equals(operate.getParamOrder())) {
			paramArr = args;
			clazzArr = new Class[args.length];
			for (int i = 0; i < args.length; i++) {
				clazzArr[i] = args[i].getClass();
			}
		} else {
			clazzArr = new Class[] { args[Integer.valueOf(operate
					.getParamOrder())].getClass() };
			paramArr = new Object[] { args[Integer.valueOf(operate
					.getParamOrder())] };
		}
		try {
			Method method = targetObject.getClass().getMethod(
					operate.getLogMethod(), clazzArr);
			Object obj = method.invoke(targetObject, paramArr);
			if (obj != null) {
				operateContent = (String) obj;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return operateContent;
	}

	/**
	 * 添加操作日志的信息;
	 * 
	 * @param moduleName
	 * @param moduleType
	 * @param operateName
	 * @param operateType
	 * @param operateContent
	 */
	private void insertSystemLog(String moduleName, String moduleType,
			String operateName, String operateType, String operateContent) {
		LogPo log = new LogPo();

		log.setId(UUIDGenerator.getUUID());
		log.setModuleType(moduleType);
		log.setModuleName(moduleName);
		log.setOperateType(operateType);
		log.setOperateName(operateName);
		log.setOperateContent(operateContent);
		SysUserPo sysUserPo = (SysUserPo) SecurityUtils.getSubject()
				.getPrincipal();
		if (sysUserPo != null) {
			log.setOperator(sysUserPo.getFirstName() + sysUserPo.getLastName());
		}
		log.setOperateTime(new Date(System.currentTimeMillis()));
		String ipAddress = ServletActionContext.getRequest().getRemoteAddr();
		log.setIpAddr(ipAddress);
		try {

			this.logDao.save("insertSystemLog", log);
			System.out.println("添加操作日志：" + log);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setLogDao(ILogDao logDao) {
		this.logDao = logDao;
	}

	public ILogDao getLogDao() {
		return logDao;
	}
}
