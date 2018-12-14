package com.dy.sensor.common.tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 * FTP操作工具类对象;
 * 
 * @ClassName: FTPClientUtils
 * @Description:
 * @author: myh
 * @date: 2015-4-3 下午3:09:40
 * 
 */
public class FTPClientUtils {

	public FTPClient ftp;
	public ArrayList<String> arFiles;

	/**
	 * 重载构造函数
	 * 
	 * @param isPrintCommmand
	 *            是否打印与FTPServer的交互命令
	 */
	public FTPClientUtils(boolean isPrintCommmand) {
		ftp = new FTPClient();
		arFiles = new ArrayList<String>();
		if (isPrintCommmand) {
			ftp.addProtocolCommandListener(new PrintCommandListener(
					new PrintWriter(System.out)));
		}
	}

	/**
	 * 登陆FTP服务器
	 * 
	 * @param host
	 *            FTPServer IP地址
	 * @param port
	 *            FTPServer 端口
	 * @param username
	 *            FTPServer 登陆用户名
	 * @param password
	 *            FTPServer 登陆密码
	 * @return 是否登录成功
	 * @throws IOException
	 */
	public boolean login(String host, int port, String username, String password)
			throws IOException {
		this.ftp.connect(host, port);
		if (FTPReply.isPositiveCompletion(this.ftp.getReplyCode())) {
			if (this.ftp.login(username, password)) {
				this.ftp.setControlEncoding("GBK");
				return true;
			}
		}
		if (this.ftp.isConnected()) {
			this.ftp.disconnect();
		}
		return false;
	}

	/**
	 * 关闭数据链接
	 * 
	 * @throws IOException
	 */
	public void disConnection() throws IOException {
		if (this.ftp.isConnected()) {
			this.ftp.disconnect();
		}
	}

	/**
	 * 递归遍历出目录下面所有文件
	 * 
	 * @param pathName
	 *            需要遍历的目录，必须以"/"开始和结束
	 * @throws IOException
	 */
	public void List(String pathName) throws IOException {
		if (pathName.startsWith("/") && pathName.endsWith("/")) {
			String directory = pathName;
			// 更换目录到当前目录
			this.ftp.changeWorkingDirectory(directory);
			FTPFile[] files = this.ftp.listFiles();
			for (FTPFile file : files) {
				if (file.isFile()) {
					// arFiles.add(directory + file.getName());//整个目录的全路径;
					arFiles.add(file.getName());// 只有文件名称;

				} else if (file.isDirectory()) {
					List(directory + file.getName() + "/");
				}
			}
		}
	}

	/**
	 * 递归遍历目录下面指定的文件名
	 * 
	 * @param pathName
	 *            需要遍历的目录，必须以"/"开始和结束
	 * @param ext
	 *            文件的扩展名
	 * @throws IOException
	 */
	public void List(String pathName, String ext) throws IOException {
		if (pathName.startsWith("/") && pathName.endsWith("/")) {
			String directory = pathName;
			// 更换目录到当前目录
			this.ftp.changeWorkingDirectory(directory);
			FTPFile[] files = this.ftp.listFiles();
			for (FTPFile file : files) {
				if (file.isFile()) {
					if (file.getName().endsWith(ext)) {
						arFiles.add(directory + file.getName());
					}
				} else if (file.isDirectory()) {
					List(directory + file.getName() + "/", ext);
				}
			}
		}
	}

	/**
	 * 获取给定文件名的文件
	 * 
	 * @param pathName
	 *            需要遍历的目录，必须以"/"开始和结束
	 * @param fileName
	 *            需要获取的文件名
	 * @param localPath
	 *            获取文件保存到的路径
	 * @return
	 * @throws IOException
	 */
	public boolean getFile(String pathName, String fileName, String localPath)
			throws IOException {
		boolean successFlag = false;
		if (pathName.startsWith("/") && pathName.endsWith("/")) {
			String directory = pathName;
			// 更换目录到当前目录
			this.ftp.changeWorkingDirectory(directory);
			File localFile = new File(localPath + "/" + fileName);
			OutputStream is = new FileOutputStream(localFile);
			this.ftp.retrieveFile(fileName, is);
			is.close();
			ftp.logout();
			successFlag = true;
		}
		return successFlag;
	}

	/**
	 * 创建FTP目录;
	 * 
	 * @param pathname
	 */
	public void createDir(String pathname) {
		try {
			// 检测目录是否存在;
			boolean changeResult = this.ftp.changeWorkingDirectory(pathname);

			if (!changeResult) {
				this.ftp.makeDirectory(pathname);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 复制一个源文件到目标路径;
	 * 
	 * @param source
	 * @param goal
	 */
	public void copyFile(String sourceFile, String goal) {
//		FileUtil util = new FileUtil();
//		util.copyFile(sourceFile, goal);
		ByteArrayOutputStream fos=new ByteArrayOutputStream();
		try {
			this.ftp.retrieveFile(sourceFile, fos);
			ByteArrayInputStream in=new ByteArrayInputStream(fos.toByteArray());
			this.ftp.storeFile(goal, in);
			fos.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public static void main(String[] args) throws Exception {
		FTPClientUtils f = new FTPClientUtils(true);
		if (f.login("10.100.3.71", 21, "ftpuser", "ftpuser")) {
			f.copyFile(
					"/opt/MyFileService/miaoyouhu/monitor.log",
					"/opt/MyFileService/miaoyouhu/haha/test.log");

		}
		f.disConnection();

	}
}