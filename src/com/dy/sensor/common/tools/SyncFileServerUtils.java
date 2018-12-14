package com.dy.sensor.common.tools;

import javax.servlet.http.HttpServletRequest;

/**
 * 同步文件服务器工具类
 * 
 * @ClassName: SyncFileServerUtils
 * @author myh
 * @date 2014-12-12 下午1:27:29
 * 
 */
public class SyncFileServerUtils {

	/**
	 * 创建文件目录;
	 * 
	 * @return
	 */
	public static void createFileDir(String filelocation, String createDirScript) {
		JavaUtilShell shell = new JavaUtilShell();

		// 1、先创建目录;
		System.out.println("1、创建目录：" + filelocation);
		String createFile = shell.processShells(createDirScript + " "
				+ filelocation);
		System.out.println("   创建目录结果：" + createFile);

	}

	/**
	 * 同步文件目录到服务器
	 * 
	 * @param request
	 * @param filelocation
	 */
	@SuppressWarnings("deprecation")
	public static void syncFileDir(HttpServletRequest request,
			String filelocation, String puppetServerName) {
		JavaUtilShell shell = new JavaUtilShell();
		// 2、同步文件目录;
		String tongmu = filelocation.substring(19); // 同步的目录;
		StringBuffer cmd = new StringBuffer();
		cmd.append("mco shell -j \"chmod -R 755 ")
				.append(request.getRealPath(filelocation)).append("/* && ");
		cmd.append("rsync -vzrtopg --password-file=/etc/rsyncd.password");
		cmd.append(" ").append(request.getRealPath(filelocation)).append("/*");
		cmd.append(" ").append("root@fileserver.itaom.com::MyFileService/")
				.append(tongmu).append("\"");
		cmd.append(" ").append("-I").append(" ");
		cmd.append(puppetServerName);

		String result = shell.processShells(cmd.toString());
		System.out.println("2、miaoyouhu_同步文件命令：" + cmd.toString());
		System.out.println("   miaoyouhu_同步文件结果：" + result);
	}

}
