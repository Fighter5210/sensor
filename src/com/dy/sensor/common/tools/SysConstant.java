package com.dy.sensor.common.tools;

/**
 * 自动化常量类
 * 
 * @ClassName: SysConstant
 * @author myh
 * @date 2014-11-28 上午11:52:55
 */
public class SysConstant {

	public final static String TERRACE_TYPE = "TERRACE_TYPE"; // 平台类型

	public final static String CYCLE_TYPE = "CYCLE_TYPE"; // 周期类型

	public final static String SYS_TYPE_CODE = "appSysTypeCode"; // 词典应用系统分类编码(修改V2.0)

	public final static String SOFT_CLASSIFY_ID = "softClassifyId"; // 软件包分类编号;(修改V2.0)

	public final static String GATHERLOG_CLASSIFY_ID = "gatherLogClassifyId";// 日志收集分类编号;(修改V2.0)

	public final static String COMMON_CLASSIFY_ROOT = "fileServerRoot"; // 文件服务器根目录(修改V2.0)

	public final static String SOFTWARE_ROOT = "/softwareMaintenance";// 软件维护主目录;(修改V2.0)

	public final static String WEBSERVICE_ADDRESS = "webserviceWsdl"; // webservice(修改V2.0)
																		// wsdl请求地址;(修改V2.0)

	public final static String WEBSERVICE_USERNAME = "webserviceUserName"; // webservice登录用户名;(修改V2.0)

	public final static String WEBSERVICE_PASSWORD = "webservicePassword"; // webservice登录密码;(修改V2.0)

	public final static String GATHERDEVICEATTR_CATE = "TERRACE_TYPE";// 设备属性收集分类;

	public final static String GATHERDEVICEATTR_CATE_LINUX = "linux";// 设备属性收集分类编号;

	public final static String GATHERDEVICEATTR_CATE_HPUX = "hpux";// 设备属性收集分类编号;

	public final static String GATHERDEVICEATTR_CATE_AIX = "aix";// 设备属性收集分类编号;

	public final static String GATHERDEVICEATTR_CATE_WINDOWS = "windows";// 设备属性收集分类编号;

	public final static String FILE_SERVER_IP = "ftpServerIP"; // 文件服务器的IP信息;(修改V2.0)

	public final static String FILE_SERVER_NAME = "puppetServerNodeName"; // puppet服务端节点名称(修改V2.0)

	public final static String CREATE_DIR_SCRIPT = "createDirScript";// 创建目录脚本文件;(修改V2.0)

	public final static String CHECK_EXPORT_EXCEL = "/common/file/check/";// 健康检查excel生成文件存放位置;
	
	public final static String LOGFILES_PATH = "/common/file/logFiles/";// 日志下载生成文件存放位置;
	
	public final static String ATTR_XLS = "/pages/system/gatherdevicemgt/xlsFiles/";// 属性excel存放位置;

	public final static int GATHERDEVICEINFO_TEMPLATE_COLUMN_NUM = 17; // 导入主机模板列数

	public final static String SYS_CONFIG_BEAN = "SysConfigServiceImpl"; // 系统配置业务实现类名称;

	public final static String CONFIG_NAME = "configName"; // 安装包的配置文件名称;

	public static final int GATHERDEVICEATTR_TEMPLATE_COLUMN_NUM = 5;  //导入采集项属性模板列数
	
	
	public static final String GATHER_DEVICE_SOFTINFO = "gatherDeviceSoftInfo";//采集设备安装的软件信息脚本;
	
	
	public static final String FILE_SERVER_FTP_IP = "fileServerFTPIP";  //文件服务器FTP IP地址 
	public static final String FILE_SERVER_FTP_PORT = "fileServerFTPPort";  //文件服务器FTP端口号 
	public static final String FILE_SERVER_FTP_PWD = "fileServerFTPPwd";  //文件服务器FTP密码 
	public static final String FILE_SERVER_FTP_USER = "fileServerFTPUser";  //文件服务器FTP账号 

}
