/*
 * 创建日期 2004-11-17
 *
 */
package com.dy.sensor.common.tools;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * @author myh
 */
public class StringUtils {

	private static final char hex[] = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	// 将特殊字符（插入数据库前使用）' \ 转换为 \', \\, 。
	// 修改版，将\\ 改成 ''
	public static String addSlashes(String inputString) throws Exception {
		String srcArray[] = new String[2];
		String desArray[] = new String[2];

		srcArray[0] = "'";
		// desArray[0] = "\\'";
		desArray[0] = "''";
		srcArray[1] = "\\";
		desArray[1] = "\\\\";

		int length = srcArray.length;
		boolean charMatch = false;
		String result = "";

		if (inputString == null)
			return null;

		for (int i = 0; i < inputString.length(); i++) {
			charMatch = false;
			for (int j = 0; j < length; j++) {
				if (inputString.substring(i, i + 1).equals(srcArray[j])) {
					result += desArray[j];
					charMatch = true;
					break;
				}
			}
			if (!charMatch)
				result += inputString.substring(i, i + 1);
		}
		return result;
	}

	/**
	 * 判断字符串是否为中文
	 * 
	 * @param target
	 * @return
	 * @throws Exception
	 */
	public static boolean isgb(String target) throws Exception {
		byte[] bytes = new byte[2];
		int byteFrom, byteTo;
		byteFrom = 10 - 96;
		byteTo = 94 - 96;
		boolean check = true;
		try {
			bytes = target.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new Exception(e.getMessage());
		}
		if ((bytes[0] < byteFrom) || (bytes[0] > byteTo)) {
			check = false;
		}
		return check;
	}

	/**
	 * 判断字符串是否包含中文
	 * 
	 * @param target
	 * @return
	 * @throws Exception
	 */
	public static boolean hasGB(String target) throws Exception {
		byte[] bytes = new byte[2];
		int byteFrom, byteTo;
		byteFrom = 10 - 96;
		byteTo = 94 - 96;
		try {
			bytes = target.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new Exception(e.getMessage());
		}
		for (int i = 0; i < bytes.length; i++) {
			if ((bytes[i] > byteFrom) && (bytes[i] < byteTo)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断字符串是否为空;
	 * 
	 * @param str
	 *            字符串;
	 * @return
	 */
	public static boolean is_null(String str) {
		return str != null && str.length() > 0 ? true : false;
	}

	/**
	 * 验证合法用户名
	 * 
	 * @param userId
	 * @return 错误提示信息
	 * @throws Exception
	 */
	public static String checkUserId(String userId) throws Exception {
		String error = "";
		if (userId == null || userId.equals("")) {
			error = "用户名不能为空！";
		}
		if (hasGB(userId))
			error = "用户名中不能包含中文字符！";
		if (!userId.matches("[0-9,a-z,A-Z,_,-]+"))
			error = "用户名只能包含数字、字母及下划线符号\"_\"！";
		return error;
	}

	/** 将字节数组转化为十六进制表示的字符串,不带前面的0x字样，需要自己加 */
	public static final String bytes2HexStr(byte abyte0[]) {
		StringBuffer stringbuffer = new StringBuffer(abyte0.length * 2);
		for (int i = 0; i < abyte0.length; i++) {
			stringbuffer.append(hex[abyte0[i] >>> 4 & 0xf]);
			stringbuffer.append(hex[abyte0[i] & 0xf]);
		}

		return stringbuffer.toString();
	}

	/**
	 * 将 ISO-8859-1 编码的字符串转换为 GBK 编码的字符串。
	 * 
	 * @param inputString
	 * @return
	 * @throws Exception
	 */
	public static String engToChn(String inputString) throws Exception {
		if (inputString == null)
			return null;
		String outputString = new String(inputString.getBytes("ISO-8859-1"),
				"GBK");
		return outputString;
	}

	/**
	 * 将 GBK 编码的字符串转换为 ISO-8859-1 编码的字符串。
	 * 
	 * @param inputString
	 * @return
	 * @throws Exception
	 */
	public static String chnToEng(String inputString) throws Exception {
		if (inputString == null)
			return null;
		String outputString = new String(inputString.getBytes("GBK"),
				"ISO-8859-1");
		return outputString;
	}

	/**
	 * 对中文进行编码
	 * 
	 * @param str
	 * @param enc
	 * @return
	 */
	public static String chinaToEncoder(String str, String enc) {
		String result = "";
		try {
			result = URLEncoder.encode(str, enc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 对中文进行解码;
	 * 
	 * @param str
	 * @param enc
	 * @return
	 */
	public static String chinaToDecoder(String str, String enc) {
		String result = "";
		try {
			result = URLDecoder.decode(str, enc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据文件名的到扩展名
	 * 
	 * @param name
	 * @return
	 */
	public static String getFileExt(String name) {
		int pos = name.lastIndexOf('.');
		if (pos == -1) {
			return "";
		} else {
			return name.substring(pos + 1, name.length());
		}
	}

	/**
	 * 计算两个日期相差多少天
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	public static int getIntervalDays(Date begin, Date end) {
		long in = end.getTime() - begin.getTime();
		int days = (int) (in / 86400000);
		return days;
	}

	/**
	 * 比较开始时间与当前时间的大小;
	 * 
	 * @param begin
	 *            开始时间
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String compareDate(Date begin) {
		try {
			String result = "报名中";
			Date dd = begin;
			dd.setDate(dd.getDate() + 1);
			Date currentTime = new Date(System.currentTimeMillis());
			long in = dd.getTime() - currentTime.getTime();
			if (in <= 0) {
				result = "已结束";
			}
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * 计算两个日期差几个月
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getIntervalMonths(Date date1, Date date2) {

		// 使用日历对象,不断的累加月数,知道日期超过date2

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(date1);
		c2.setTime(date2);
		int months = 0;
		while (c1.before(c2)) {
			c1.add(Calendar.MONTH, 1);
			months++;
		}
		months--;
		return months;
	}

	/**
	 * 随机生成6位的密码,只包括数字
	 * 
	 * @return
	 */
	public static String getRandomPassword() {
		Random random = new Random();
		String result = "";
		for (int i = 0; i < 6; i++) {
			result += (random.nextInt(9) + 1);
		}
		return result;
	}

	/**
	 * 格式化函数，将一个double值格式化成一个金额值（只要是乘除浮点运算可能造成一个偏移)
	 * 
	 * @param input
	 * @return
	 */
	public static double formateAmount(double input) {
		return (double) Math.round(input * 10) / 10d;
	}

	/**
	 * 格式化html格式文档，将 <>内的内容剔除
	 * 
	 * @param content
	 * @return
	 */
	public static String formatHtml(String content) {
		if (content == null || content.equals("")) {
			return "";
		}
		Pattern p = Pattern.compile("<[^>]*>");
		Matcher m = p.matcher(content);
		StringBuffer result = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(result, "");
		}
		m.appendTail(result);
		return result.toString();
	}

	/**
	 * 清除html标签,并截取相应长度的内容;
	 * 
	 * @param content
	 * @param length
	 * @param end
	 * @return
	 */
	public static String subHtml(String content, int length, String end) {
		if (StringUtils.is_null(content)) {
			String clearHtml = formatHtml(content);
			return formatTitle(clearHtml, length, end);
		}
		return content;
	}

	/**
	 * 清除标签,并截取相应长度的内容;
	 * 
	 * @param content
	 * @param length
	 * @param end
	 * @return
	 */
	public static String subZero(BigDecimal bgContent) {

		// 转化成字符串
		String content = bgContent.toString();
		if (StringUtils.is_null(content)) {
			if (!content.contains(".")) {
				return content;
			} else {
				int intBeginIndex = content.length() - 1;
				while (intBeginIndex >= 0
						&& content.charAt(intBeginIndex) == '0') {
					intBeginIndex--;
				}
				if (intBeginIndex > 0) {
					intBeginIndex = content.charAt(intBeginIndex) == '.' ? intBeginIndex
							: intBeginIndex + 1;
					content = content.substring(0, intBeginIndex);
				} else {
					content = "0";
				}
				return content;
			}
		} else {
			return "0";
		}
	}

	/**
	 * 去除字符串中的空格、回车、换行符、制表符 注：\n 回车 \t 水平制表符 \s 空格(\u0008) \r 换行
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	/**
	 * 清除HTML及相应的其他特殊要求的如空格、回车等;
	 * 
	 * @param content
	 * @param length
	 * @param end
	 * @return
	 */
	public static String subHtmlAndOther(String content, int length, String end) {
		if (StringUtils.is_null(content)) {
			return StringUtils.replaceBlank(StringUtils.subHtml(content,
					length, end));
		}
		return content;
	}

	/**
	 * 格式化字符串,去掉字符串中的回车换行,以及引号"
	 * 
	 * @param content
	 * @return
	 */
	public static String convertForJS(String content) {
		if (content == null)
			return "";
		return content.replaceAll("\\\\", "\\\\\\\\").replaceAll("\r", "\\r")
				.replaceAll("\n", "\\n").replaceAll("\"", "\\\"");
	}

	/** 在html页面上,将字符串中的一些字显示显示为红色 */
	public static String converKeyString2Red(String content, String key) {
		StringBuffer sb = new StringBuffer();
		char[] chars = content.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (key.indexOf(chars[i]) >= 0) {
				sb.append("<font color=red >" + chars[i] + "</font>");
			} else {
				sb.append(chars[i]);
			}
		}
		return sb.toString();
	}

	/**
	 * 替换特殊字符，防止SQL注入。
	 * 
	 * @param str
	 * @return
	 */
	public static String htmlspecialchars(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}

	// 将特殊HTML字符 & < > " 转换为 &amp, &lt, &gt, &quot 。（不转换空格）
	public static String htmlChars(String inputString) throws Exception {
		String srcArray[] = new String[4];
		String desArray[] = new String[4];

		srcArray[0] = "&";
		desArray[0] = "&amp;";
		srcArray[1] = ">";
		desArray[1] = "&gt;";
		srcArray[2] = "<";
		desArray[2] = "&lt;";
		srcArray[3] = "\"";
		desArray[3] = "&quot;";

		int length = srcArray.length;
		boolean charMatch = false;
		String result = "";

		if (inputString == null)
			return null;

		for (int i = 0; i < inputString.length(); i++) {
			charMatch = false;
			for (int j = 0; j < length; j++) {
				if (inputString.substring(i, i + 1).equals(srcArray[j])) {
					result += desArray[j];
					charMatch = true;
					break;
				}
			}
			if (!charMatch)
				result += inputString.substring(i, i + 1);
		}
		return result;
	}

	/** 截掉多余的文本，并且加上后缀，如。。。 */
	public static String formatTitle(String input, int length, String end) {
		String result = null;
		if (input.length() > (length + 1)) {
			result = input.substring(0, length);
			if (end != null && !end.equals("")) {
				result += end;
			}
		} else {
			result = input;
		}
		return result;
	}

	/**
	 * 生成随机密码
	 * 
	 * @param pwd_len
	 *            生成的密码的总长度
	 * @return 密码的字符串
	 */

	public static String createRandomPwd() {
		// 35是因为数组是从0开始的，26个字母+10个数字
		final int pwd_len = 6;
		final int maxNum = 36;
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度
		char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
				'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
				'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < pwd_len) {
			// 生成随机数，取绝对值，防止生成负数，

			i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1

			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}

		return pwd.toString();

	}

	/**
	 * 产生一个指定范围内的int随机数
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	public static int randomFromBeginToEnd(int begin, int end) {
		Random rnd = new Random();
		int b = begin < end ? begin - 1 : end - 1;
		int length = Math.abs(end - begin) + 1;
		int[] randoms = new int[length];
		for (int i = 0; i < length; i++) {
			randoms[i] = ++b;
		}
		int thisrnd = rnd.nextInt(length);
		return randoms[thisrnd];
	}

	/**
	 * 分隔字符串
	 * 
	 * @param str
	 *            分隔的字符串
	 * @param dStr
	 *            分隔的字符
	 * @return 获得分隔的字符串集合
	 */
	public static String[] divideStr(String str, String dStr) {
		String[] result = str.split(dStr);
		return result;
	}

	/**
	 * 获得相应的内容;
	 * 
	 * @param str
	 * @param number
	 * @return
	 */
	public static String getMessage(String str, int number) {
		String result = str.replaceAll("姓名：", ",").replaceAll("所在大学：", ",")
				.replaceAll("所在城市：", ",").replaceAll("所在院系：", ",");
		String[] strarray = result.split(",");
		String value = strarray[number].trim();
		return value;
	}

	/**
	 * 获得鲜花的名称;
	 * 
	 * @param flowerName
	 *            鲜花名称的图片;
	 * @return
	 */
	public static String getFlowerName(String flowerName) {
		String result = flowerName.substring(0, flowerName.indexOf("."));
		return result;
	}

	/**
	 * float转化成int
	 * 
	 * @param number
	 * @return
	 */
	public static int floatToInt(float number) {
		Float result = Float.valueOf(number);
		return result.intValue();
	}

	public static String floatToString(float number) {
		Float result = Float.valueOf(number);
		int tmpNum = result.intValue();
		String strResult = "待定";
		if (tmpNum != 0) {
			strResult = tmpNum + "元";
		}
		return strResult;
	}

	/**
	 * 截取字符串开头以br的内容;
	 * 
	 * @param content
	 *            截取的内容;
	 * @return
	 */
	public static String subBeginBr(String content) {
		String value = "";
		if (StringUtils.is_null(content)) {
			value = content.substring(content.indexOf(">") + 1);
		}
		return value;
	}

	/**
	 * 获取分类组合目录;
	 * 
	 * @param result
	 * @param trType
	 * @return
	 */
	public static String getFileDirectory(String result, String trType) {
		StringBuffer fileRoot = new StringBuffer();

		String[] file = result.split("/");
		for (int i = 0; i < file.length; i++) {
			System.out.println(i + ":" + file[i]);
			if (file[i] != null && !file[i].equals("")) {
				if (i == 1) {
					fileRoot.append("/").append(file[i]).append("/")
							.append(trType);
				} else {
					fileRoot.append("/").append(file[i]);
				}
			}
		}
		return fileRoot.toString();
	}

	/**
	 * 对比区间类型的数据;
	 * 
	 * @param comparisonType
	 *            匹配公式
	 * @param a1
	 *            开始值
	 * @param n
	 *            执行脚本获得值;
	 * @param a2
	 *            结束值
	 * @return
	 * @throws Exception
	 */
	public static boolean compareYesScope(String comparisonType, String a1,
			String n, String a2) throws Exception {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");

		if (StringUtils.isNumeric(a1)) {
			engine.put("a1", Double.parseDouble(a1));
		} else {
			if (StringUtils.isBaiFenBi(a1)) {
				// 是百分比;
				engine.put("a1", Double.parseDouble(a1.replace("%", "")));
			} else {
				engine.put("a1", a1);
			}

		}

		if (StringUtils.isNumeric(n)) {
			engine.put("n", Double.parseDouble(n));
		} else {
			if (StringUtils.isBaiFenBi(a1)) {
				engine.put("n", Double.parseDouble(n.replace("%", "")));
			} else {
				engine.put("n", n);
			}

		}

		if (StringUtils.isNumeric(a2)) {
			engine.put("a2", Double.parseDouble(a2));
		} else {
			if (StringUtils.isBaiFenBi(a2)) {
				engine.put("a2", Double.parseDouble(a2.replace("%", "")));
			} else {
				engine.put("a2", a2);
			}

		}

		Object result = engine.eval(comparisonType);
		return Boolean.parseBoolean(result.toString());

	}

	/**
	 * 对比非区间类型的数据;
	 * 
	 * @param comparisonType
	 *            匹配公式
	 * @param a
	 *            输入值
	 * @param n
	 *            执行脚本获得值;
	 * @return
	 * @throws Exception
	 */
	public static boolean compareNoScope(String comparisonType, String a,
			String n) throws Exception {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		if (StringUtils.isNumeric(a)) {
			engine.put("a", Double.parseDouble(a));
		} else {
			if (StringUtils.isBaiFenBi(a)) {
				engine.put("a", Double.parseDouble(a.replace("%", "")));
			} else {
				engine.put("a", a);
			}

		}
		if (StringUtils.isNumeric(n)) {
			engine.put("n", Double.parseDouble(n));
		} else {
			if (StringUtils.isBaiFenBi(n)) {
				engine.put("n", Double.parseDouble(n.replace("%", "")));
			} else {
				engine.put("n", n);
			}

		}
		Object result = engine.eval(comparisonType);
		return Boolean.parseBoolean(result.toString());

	}

	/**
	 * 判断字符串是否为数字;
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断是否为百分比类型;
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBaiFenBi(String str) {
		Pattern pattern = Pattern.compile("([0-9.]+)%");
		return pattern.matcher(str).matches();
	}

	public static void main(String[] args) throws Exception {
		boolean result = StringUtils.compareYesScope("a1<=n && n<=a2", "0%",
				"20%", "10%");

		System.out.println(result);
	}
}
