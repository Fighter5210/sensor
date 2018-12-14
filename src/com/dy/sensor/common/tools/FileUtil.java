package com.dy.sensor.common.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.struts2.ServletActionContext;


/**
 * 文件及文件夹操作工具类
 * 
 * @author myh
 * 
 */
public class FileUtil {

	private boolean flag;

	private File file = null;

	/**
	 * 根据路径删除指定的目录或文件，无论存在与否
	 * 
	 * @param sPath
	 *            要删除的目录或文件
	 * @return 删除成功返回 true，否则返回 false。
	 */
	public boolean deleteFolder(String sPath) {
		flag = false;
		file = new File(sPath);
		// 判断目录或文件是否存在
		if (!file.exists()) { // 不存在返回 false
			return flag;
		} else {
			// 判断是否为文件
			if (file.isFile()) { // 为文件时调用删除文件方法
				return deleteFile(sPath);
			} else { // 为目录时调用删除目录方法
				return deleteDirectory(sPath);
			}
		}
	}

	/**
	 * 删除单个文件
	 * 
	 * @param sPath
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public boolean deleteFile(String sPath) {
		flag = false;
		file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * 
	 * @param sPath
	 *            被删除目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public boolean deleteDirectory(String sPath) {
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} // 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag)
			return false;
		// 删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 方法追加文件：使用FileWriter
	 * 
	 * @param fileName
	 *            文件名称
	 * @param content
	 *            追加的文件内容
	 */
	public static void appendMethod(String fileName, String content) {
		try {
			// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			FileWriter writer = new FileWriter(fileName, true);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建文件
	 * 
	 * @return
	 */
	public boolean createFile(String spath) {
		File newFile = new File(spath);
		boolean result = false;
		if (!newFile.exists()) {
			try {
				result = newFile.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 创建文件目录
	 * 
	 * @param spath
	 * @return
	 */
	public boolean createDir(String spath) {
		File newFile = new File(spath);
		boolean result = false;
		if (!newFile.exists()) {
			try {
				result = newFile.mkdirs();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 生成UTF-8文件. 如果文件内容中没有中文内容，则生成的文件为ANSI编码格式； 如果文件内容中有中文内容，则生成的文件为UTF-8编码格式。
	 * 
	 * @param fileName
	 *            待生成的文件名（含完整路径）
	 * @param fileBody
	 *            文件内容
	 * @return
	 */
	public static boolean writeUTFFile(String fileName, String fileBody) {
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		try {
			fos = new FileOutputStream(fileName);
			osw = new OutputStreamWriter(fos, "UTF-8");
			osw.write(fileBody);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (osw != null) {
				try {
					osw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public String FormetFileSize(long fileS) {// 转换文件大小
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "K";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}

	/**
	 * 查看指定的文件夹下，文件列表的集合;
	 * 
	 * @param path
	 * @return
	 */
	public static List getFileList(String path) {
		File file = new File(path);
		List list = new ArrayList();
		if (file.isDirectory()) {
			File[] dirFile = file.listFiles();
			for (File f : dirFile) {
				if (f.isDirectory())
					getFileList(f.getAbsolutePath());
				else {
					list.add(f.getName());
				}
			}
		}

		return list;
	}

//	/**
//	 * 将数据导出到Excel
//	 * 
//	 * @param list
//	 */
//	public static void exportToExcel(List<AbnormalResultVo> list,
//			String sheetTitle, String realPath) {
//		// 第一步，创建一个webbook，对应一个Excel文件
//		HSSFWorkbook wb = new HSSFWorkbook();
//		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
//		HSSFSheet sheet = wb.createSheet(sheetTitle);
//		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
//		HSSFRow row = sheet.createRow((int) 0);
//		// 第四步，创建单元格，并设置值表头
//		// 创建表头格式
//		HSSFCellStyle styleTitle = wb.createCellStyle();
//		styleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//		styleTitle.setFillBackgroundColor(HSSFColor.PALE_BLUE.index);
//		HSSFFont font = wb.createFont();
//		font.setFontName("黑体");
//		styleTitle.setFont(font);
//
//		HSSFCell cell = row.createCell(0);
//		cell.setCellValue("任务名称");
//		cell.setCellStyle(styleTitle);
//		cell = row.createCell(1);
//		cell.setCellValue("运行时间");
//		cell.setCellStyle(styleTitle);
//		cell = row.createCell(2);
//		cell.setCellValue("巡检规则");
//		cell.setCellStyle(styleTitle);
//		cell = row.createCell(3);
//		cell.setCellValue("设备名称");
//		cell.setCellStyle(styleTitle);
//		cell = row.createCell(4);
//		cell.setCellValue("指标名称");
//		cell.setCellStyle(styleTitle);
//		cell = row.createCell(5);
//		cell.setCellValue("项目状态");
//		cell.setCellStyle(styleTitle);
//		cell = row.createCell(6);
//		cell.setCellValue("原因");
//		cell.setCellStyle(styleTitle);
//
//		// 第五步，写入实体数据 实际应用中这些数据从数据库得到
//		int i = 1;
//		for (AbnormalResultVo vo : list) {
//			row = sheet.createRow(i);
//			// 第六步，创建单元格，并设置值
//			row.createCell(0).setCellValue(vo.getTaskName());
//			row.createCell(1).setCellValue(
//					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(vo
//							.getRunTime()));
//			row.createCell(2).setCellValue(vo.getScriptIndex());
//			row.createCell(3).setCellValue(vo.getDeviceName());
//			row.createCell(4).setCellValue(vo.getScriptName());
//			String isNormal = "";
//			if (vo.getIsNormal().equals(
//					CheckResultEnum.CHECK_RESULT_ABNORMAL.getValue())) {
//				isNormal = "异常";
//			} else if (vo.getIsNormal().equals(
//					CheckResultEnum.CHECK_RESULT_NORMAL.getValue())) {
//				isNormal = "正常";
//			} else if (vo.getIsNormal().equals(
//					CheckResultEnum.CHECK_RESULT_OTHERS.getValue())) {
//				isNormal = "其他";
//			}
//			row.createCell(5).setCellValue(isNormal);
//			row.createCell(6).setCellValue(vo.getReason());
//			i += 1;
//		}
//		// 第七步，设置单元格宽度为自适应
//		for (int j = 0; j < 7; j++) {
//			sheet.autoSizeColumn(j);
//		}
//		// 第八步，将文件存到指定位置
//		
//		FileUtil util = new FileUtil();
//		String dirPath = ServletActionContext.getServletContext().getRealPath(
//				SysConstant.CHECK_EXPORT_EXCEL);
//		util.createDir(dirPath);
//		
//		try {
//			FileOutputStream fout = new FileOutputStream(realPath);
//			wb.write(fout);
//			fout.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 复制单个文件
//	 * 
//	 * @param oldPath
//	 *            String 原文件路径 如：c:/fqf.txt
//	 * @param newPath
//	 *            String 复制后路径 如：f:/fqf.txt
//	 * @return boolean
//	 */
//	public void copyFile(String oldPath, String newPath) {
//		try {
//			int byteread = 0;
//			File oldfile = new File(oldPath);
//			if (oldfile.exists()) { // 文件存在时
//				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
//				FileOutputStream fs = new FileOutputStream(newPath);
//				byte[] buffer = new byte[1444];
//				while ((byteread = inStream.read(buffer)) != -1) {
//					fs.write(buffer, 0, byteread);
//				}
//				inStream.close();
//			}
//		} catch (Exception e) {
//			System.out.println("复制单个文件操作出错");
//			e.printStackTrace();
//
//		}
//	}
//
//	public static void main(String[] args) {
//		String temp = "ftp://10.100.3.71/opt/MyFileService/informationCollection/linux/logs/itaomlog_getlog_messages_puppetserver.itaom.com.tar.gz";
//		//String result = temp.replaceAll(SysConstant.FILE_SERVER_IP, "");
//		
//		//System.out.println(result);
//	}

}
