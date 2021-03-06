package com.dy.sensor.common.model;

import org.apache.struts2.ServletActionContext;

/**
 * zTree的图标枚举类
 * @author mijia
 * @date 2014-10-13
 * @version v1.0
 *
 */
public enum ZtreeIconEnum {

	APP_SYS("appsys"),//应用系统
	B_ADDRESS("b_address"),//b段地址
	C_ADDRESS("c_address"),//c段地址
	CDP("cdp"),//cdp
	CLUSTER("cluster"),//集群
	DATA_CENTER("datacenter"),//数据中心
	DU("du"),//部署单元
	HOST("host"),//物理机
	IP_ADDRESS("ip_address"),//ip地址
	RES_POOL("respool"),//资源池
	SCRIPT("script"),//脚本
	SCRIPT_PACKAGE("scriptpackage"),//脚本包
	STORAGE_DEVICE("storagedev"),//存储设备
	VM("vm"),//虚拟机
	ROOM("room"),//机房
	CABINET("cabinet"),//机柜
	U("u"),//U位	
	DATASTORE("datastore"),//数据存储设备	
	ONELEVELMENU("onelevelmenu"),//菜单管理：一级菜单
	TWOLEVELMENU("twolevelmenu"),//菜单管理：二级菜单
	FUNCTION("function"),//菜单管理：功能
	ORGAN("organ");//机构管理
	
	
	private final String icon;
	
	private final String ICON_PATH = "/css/zTreeStyle/img/icons/";
	
	private ZtreeIconEnum(String icon){
		this.icon=icon;
	}
	
	public String getIcon() {
		return ServletActionContext.getRequest().getContextPath()+ICON_PATH+icon+".png";
	}
}
