package com.dy.sensor.common.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * java针对shell命令处理的工具类
 * 
 * @author myh
 * @version 1.0.1
 * @time Jul 30, 2014 3:00:46 PM
 */
public class JavaUtilShell {

	private static Logger logger = LoggerFactory.getLogger(JavaUtilShell.class);

	/**
	 * 处理执行shell脚本
	 * 
	 * @param cmd
	 *            命令脚本文件：shell.sh文件,绝对全路径,例：/home/ty/t.sh
	 */
	public static StringBuffer processShell(String cmd) {
		Process process;
		StringBuffer result = new StringBuffer();
		try {
			// 使用Runtime来执行command，生成Process对象
			Runtime runtime = Runtime.getRuntime();
			process = runtime.exec(cmd);
			// 取得命令结果的输出流
			InputStream is = process.getInputStream();
			// 用一个读输出流类去读
			InputStreamReader isr = new InputStreamReader(is);
			// 用缓冲器读行
			BufferedReader br = new BufferedReader(isr);
			String line = null;

			while ((line = br.readLine()) != null) {
				logger.info(line);
				result.append(line);
			}
			// 执行关闭操作
			is.close();
			isr.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 根据命令，执行linux命令;
	 * 
	 * @param cmd
	 *            命令文本;
	 * @return
	 */
	public StringBuffer processShellByCmd(String cmd) {
		Process process;
		StringBuffer result = new StringBuffer();
		try {
			// 使用Runtime来执行command，生成Process对象
			Runtime runtime = Runtime.getRuntime();
			process = runtime.exec(cmd);
			// 取得命令结果的输出流
			InputStream is = process.getInputStream();
			// 用一个读输出流类去读
			InputStreamReader isr = new InputStreamReader(is);
			// 用缓冲器读行
			BufferedReader br = new BufferedReader(isr);
			String line = null;

			while ((line = br.readLine()) != null) {
				logger.info(line);
				result.append(line);
			}
			// 执行关闭操作
			is.close();
			isr.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 根据命令，执行linux命令;
	 * 
	 * @param cmd
	 * @param str
	 *            每一行加入的字符串;
	 * @return
	 */
	public static StringBuffer processShellByCmdBackStr(String cmd, String str) {
		Process process;
		StringBuffer result = new StringBuffer();
		try {
			// 使用Runtime来执行command，生成Process对象
			Runtime runtime = Runtime.getRuntime();
			process = runtime.exec(cmd);
			// 取得命令结果的输出流
			InputStream is = process.getInputStream();
			// 用一个读输出流类去读
			InputStreamReader isr = new InputStreamReader(is);
			// 用缓冲器读行
			BufferedReader br = new BufferedReader(isr);
			String line = null;

			while ((line = br.readLine()) != null) {
				logger.info(line + str);
				result.append(line).append(str);
			}
			// 执行关闭操作
			is.close();
			isr.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	public String processShells(String str) {
		String[] cmdarrays = new String[] { "/bin/sh", "-c", str };
		BufferedReader error_br = null;
		BufferedReader br = null;
		StringBuffer result = new StringBuffer();
		try {
			Process process = Runtime.getRuntime().exec(cmdarrays, null, null);
			error_br = new BufferedReader(new InputStreamReader(
					process.getErrorStream()));
			String error_line = null;
			List<String> error_list = new ArrayList<String>();
			while ((error_line = error_br.readLine()) != null) {
				error_list.add(error_line);
			}
			if (!error_list.isEmpty()) {
				throw new Exception("执行:[" + str + "]\n错误信息："
						+ error_list.toString());
			}
			br = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String line = null;
			List<String> line_list = new ArrayList<String>();

			while ((line = br.readLine()) != null) {
				line_list.add(line);
				if (line != null && line.length() > 0) {
					result.append(line.trim()).append("&&");
				}
			}
			logger.debug("执行：[" + str + "]\n返回信息：" + line_list.toString());
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (error_br != null) {
				try {
					error_br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result.toString();
	}

	public static void main(String[] args) {
		String cmd = "/home/ty/t.sh";// 这里必须要给文件赋权限 chmod u+x fileName;
	}
}
