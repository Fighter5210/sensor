package com.dy.sensor.foundation.common;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class WebApplicationManager {

	public static WebApplicationContext webApplicationContext;
	public static ServletContext servletContext;
	
	/**
	 * 获取注入的对象实例
	 * @param beanid
	 * @return
	 */
	public static Object getBean(String beanid){
		if(webApplicationContext==null && servletContext!=null)
			webApplicationContext= WebApplicationContextUtils.getWebApplicationContext(servletContext);
		if(webApplicationContext!=null)
			return webApplicationContext.getBean(beanid);
		else
			return null;
	}
}
