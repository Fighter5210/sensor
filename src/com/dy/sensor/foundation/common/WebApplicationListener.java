package com.dy.sensor.foundation.common;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.dy.sensor.common.interceptor.SysOperLogResolve;

public class WebApplicationListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent sce) {

	}

	/**
	 * 获取ServletContext对象
	 */
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext servletContext = sce.getServletContext();
		WebApplicationManager.servletContext = servletContext;

		// 加载系统操作日志配置
		SysOperLogResolve.initSysOperLogMap();
	}
}
