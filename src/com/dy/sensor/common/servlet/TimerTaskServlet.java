package com.dy.sensor.common.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dy.sensor.foundation.common.WebApplicationManager;

/**
 * 初始化采集的任务;
 * 
 * @ClassName: TimerTaskServlet
 * @author myh
 * @date 2014-12-18 下午1:35:48
 * 
 */
public class TimerTaskServlet extends HttpServlet {

	private static final long serialVersionUID = 2524511813718701974L;

	public void init() throws ServletException {

//		// 1 、例行维护(获取例行维护的时间表集合数据)
//		List<MaintenancePlanDatePo> mpdts = new ArrayList<MaintenancePlanDatePo>();
//
//		IMaintPlanService service = (IMaintPlanService) WebApplicationManager
//				.getBean("maintPlanServiceImpl");
//
//		mpdts = service.selPlanDateList();
//
//		for (int i = 0; i < mpdts.size(); i++) {
//			AutoTimer.addMainTenanceTimer(mpdts.get(i));
//		}
//
//		// 2、创建运行巡检进程(获取所有的模板集合)
//		List<CheckTemplatePo> ctps = new ArrayList<CheckTemplatePo>();
//		IHealthTaskService htService = (IHealthTaskService) WebApplicationManager
//				.getBean("healthTaskServiceImpl");
//
//		ctps = htService.queryAllTemplatePo();
//
//		for (int i = 0; i < ctps.size(); i++) {
//			AutoTimer.addCheckTimer(ctps.get(i));
//		}
//
//		System.out.println("初始化_巡检_定时器的数量："
//				+ AutoTimer.getAllCheckTimer().size());
//		System.out.println("初始化_例行_定时器的数量："
//				+ AutoTimer.getAllMainTenanceTimer().size());

	}
}
