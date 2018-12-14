package com.dy.sensor.common.interceptor;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;

import com.dy.sensor.common.support.XmlParser;

public class SysOperLogResolve {
	@SuppressWarnings("unchecked")
	public static void initSysOperLogMap() {
		XmlParser xml = null;
		try {
			xml = new XmlParser(SysOperLogMap.getSysOperLogMap().getRootPath() + "/config/sysOperLogCfg.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(xml != null) {
			// 首先清除所有模块配置信息
			SysOperLogMap.getSysOperLogMap().clearModuleMap();
			try {
				List<Element> elementList = xml.getElement("module");
				Element element;
				ModuleModel module;
				List<Element> subElementList;
				Element subElement;
				List<OperateModel> operateList;
				OperateModel operate;
				String clazz;
				int len = elementList == null ? 0 : elementList.size();
				for(int i=0 ; i<len ; i++) {
					element = elementList.get(i);
					module = new ModuleModel();
					clazz = element.getAttributeValue("class");
					if(clazz == null) {
						continue;
					}
					// 封装模块对象
					module.setClazz(clazz);
					module.setModuleCode(getCheckStr(element.getAttributeValue("moduleCode")));
					module.setModuleName(getCheckStr(element.getAttributeValue("moduleName")));
					// 封装模块包含的操作对象
					operateList = new ArrayList<OperateModel> ();
					subElementList = element.getChildren("operate");
					int subLen = subElementList == null ? 0 : subElementList.size();
					for(int j=0 ; j<subLen ; j++) {
						subElement = subElementList.get(j);
						operate = new OperateModel();
						operate.setOperateType(getCheckStr(subElement.getAttributeValue("operateType")));
						operate.setOperateName(getCheckStr(subElement.getAttributeValue("operateName")));
						operate.setStartsWith(getCheckStr(subElement.getAttributeValue("startsWith")));
						operate.setInterceptMethod(getCheckStr(subElement.getAttributeValue("interceptMethod")));
						operate.setLogMethod(getCheckStr(subElement.getAttributeValue("logMethod")));
						operate.setParamOrder(getCheckStr(subElement.getAttributeValue("paramOrder")));
						operate.setPkId(getCheckStr(subElement.getAttributeValue("pkId")));
						operate.setGetBeanMethod(getCheckStr(subElement.getAttributeValue("getBeanMethod")));
						operate.setBusinessName(getCheckStr(subElement.getAttributeValue("businessName")));
						operateList.add(operate);
					}
					module.setOperateList(operateList);
					// 添加到内存中
					SysOperLogMap.getSysOperLogMap().put(clazz, module);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static String getCheckStr(String str) {
		if(str == null) {
			str = "";
		}
		return str;
	}
	
	public static void main(String[] args) {
		SysOperLogResolve.initSysOperLogMap();
	}
}
