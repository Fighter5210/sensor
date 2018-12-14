package com.dy.sensor.common.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

/**
 * 日期处理工具类(吸收建行经验)
 * 
 * @author myh
 * @version 1.0.1
 * @time Jul 23, 2014 3:17:48 PM
 */
public class ExDateUtils extends DateUtils {

	public static final String PATTREN_DATE = "yyyy-MM-dd";

	public static final String PATTREN_TIME = "HH:mm";

	public static final String PATTREN_TIME_MIT = "HH:mm";

	public static final String PATTREN_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 返回yyyy-MM-dd HH:mm:ss 格式的字符串;
	 * 
	 * @return
	 */
	public static String getCurrentStringDateTime() {
		return new SimpleDateFormat(PATTREN_DATE_TIME, java.util.Locale.CHINA)
				.format(getCurrentDateTime());
	}

	/**
	 * 取当前日期时间[date类型]
	 * 
	 * @return
	 */
	public static Date getCurrentDateTime() {
		return java.util.Calendar.getInstance().getTime();
	}

	/**
	 * 取当前时间长整数[long]毫秒表示的时间
	 * 
	 * @return
	 */
	public static long getCurrentLongDateTime() {
		return java.util.Calendar.getInstance().getTimeInMillis();
	}

	/**
	 * 返回今天日期，时间部分为0，例如：2006-4-9 00:00:00
	 * 
	 * @return
	 */
	public static Date getToday() {
		return truncate(new Date(), Calendar.DATE);
	}

	/**
	 * 返回今天日期，时间部分为：23:59:59:999.例如：2006-9-19 3:59:59:999
	 * 
	 * @return
	 */
	public static Date getTodayEnd() {
		return getDayEnd(new Date());
	}

	/**
	 * 获取给定日的最后一时刻;
	 * 
	 * @param when
	 *            给定日
	 * @return 最后一时刻，例如：2006-9-19 3:59:59:999
	 */
	public static Date getDayEnd(Date when) {
		Date date = truncate(when, Calendar.DATE);
		date = addDays(date, 1);
		date.setTime(date.getTime() - 1);
		return date;
	}

	/**
	 * 计算给定的日期加上给定的天数
	 * 
	 * @param when
	 *            给定的日期
	 * @param amount
	 *            给定的天数
	 * @return 计算后的日期
	 */
	public static Date addDays(Date when, int amount) {
		return add(when, Calendar.DAY_OF_YEAR, amount);
	}

	/**
	 * 日期加法
	 * 
	 * @param when
	 *            被计算的日期
	 * @param field
	 *            the time field 在calendar 中定义的常数，例如：calendar.date
	 * @param amount
	 *            加数
	 * @return 计算后的日期
	 */
	public static Date add(Date when, int field, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(when);
		calendar.add(field, amount);
		return calendar.getTime();
	}

	/**
	 * calendar 转 date
	 * 
	 * @param cldDate
	 *            calendar类型的日期
	 * @return date类型日期
	 */
	public static Date calendarToDate(Calendar cldDate) {
		return cldDate.getTime();
	}

	/**
	 * calendar 转long（毫秒）
	 * 
	 * @param cldDate
	 *            calendar 类型的日期
	 * @return 毫秒数
	 */
	public static long calendarToMilisNum(Calendar cldDate) {
		return cldDate.getTimeInMillis();
	}

	/**
	 * Calendar转long (秒)
	 * 
	 * @param cldDate
	 *            Calendar类型的日期
	 * @return 秒数
	 */
	public static long calendarToSecondNum(Calendar cldDate) {
		return cldDate.getTimeInMillis() / 1000;

	}

	/**
	 * long(毫秒)转Calendar
	 * 
	 * @param milisNum
	 *            以毫秒表示的时间
	 * @return Calendar日期
	 */
	public static Calendar milisNumToCalendar(long milisNum) {
		Calendar ca = Calendar.getInstance();
		ca.setTimeInMillis(milisNum);
		return ca;
	}

	/**
	 * long(秒)转Calendar
	 * 
	 * @param secNum
	 *            以秒表示的时间
	 * @return Calendar日期
	 */
	public static Calendar secondNumToCalendar(long secNum) {
		Calendar ca = Calendar.getInstance();
		ca.setTimeInMillis(secNum * 1000);
		return ca;
	}

	/**
	 * date 转Calendar
	 * 
	 * @param dt
	 *            date类型日期
	 * @return Calendar日期
	 */
	public static Calendar dateToCalendar(Date dt) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(dt);
		return ca;
	}

	/**
	 * date to long(毫秒)
	 * 
	 * @param dt
	 *            date 类型日期
	 * @return long 毫秒数
	 */
	public static long dateToMilisNum(Date dt) {
		return dt.getTime();
	}

	/**
	 * date to long
	 * 
	 * @param dt
	 *            date类型日期
	 * @return long秒数
	 */
	public static long dateToSecondNum(Date dt) {
		return dt.getTime() / 1000;
	}

	/**
	 * long to date
	 * 
	 * @param secNum
	 *            long秒数
	 * @return date类型日期
	 */
	public static Date secondNumToDate(long secNum) {
		Date d = new Date();
		d.setTime(secNum * 1000);
		return d;
	}

	/**
	 * long to date
	 * 
	 * @param millisNum
	 *            long 毫秒数
	 * @return date类型日期
	 */
	public static Date milisNumToDate(long millisNum) {
		Date d = new Date();
		d.setTime(millisNum);
		return d;
	}

	/**
	 * 将字符串转换为日期(不包括时间)
	 * 
	 * @param dateString
	 *            "yyyy-MM-dd"格式的日期字符串
	 * @return 日期;
	 */
	public static Date converToDate(String dateString) {
		try {
			return new SimpleDateFormat(PATTREN_DATE, java.util.Locale.CHINA)
					.parse(dateString);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 检查字符串是否为日期格式:yyyy-MM-dd
	 * 
	 * @param dateString
	 * 
	 * @return true=是;false=否
	 */
	public static boolean checkDateString(String dateString) {
		return (converToDate(dateString) != null);
	}

	/**
	 * 将字符串转换为日期（包括时间）
	 * 
	 * @param dateString
	 *            yyyy-MM-dd HH:mm:ss 格式的日期字符串
	 * @return 日期时间
	 */
	public static Date converToDateTime(String dateString) {
		try {
			return new SimpleDateFormat(PATTREN_DATE_TIME,
					java.util.Locale.CHINA).parse(dateString);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 只有时间这一块
	 * 
	 * @param dateString
	 *            HH:mm:ss 格式的日期字符串
	 * @return 日期时间 HH:mm:ss
	 */
	public static Date converToTime(String dateString) {
		try {
			return new SimpleDateFormat(PATTREN_TIME, java.util.Locale.CHINA)
					.parse(dateString);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 检查字符口串是否为日期时间格式:yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateString
	 * @return true=是;false=否
	 */
	public static boolean checkDateTimeString(String dateString) {
		return (converToDateTime(dateString) != null);
	}

	/**
	 * 计算给定的日期加上给定的月数;
	 * 
	 * @param when
	 *            给定的日期
	 * @param amount
	 *            给定的天数
	 * @return 计算后的日期
	 */
	public static Date addMonths(Date when, int amount) {
		return add(when, Calendar.MONTH, amount);
	}

	/**
	 * 取得指定日期的同一周内日期
	 * 
	 * @param Date
	 *            日期字符串格式 ：yyyy-MM-dd
	 * @param dateValue
	 *            要显示的日期(周一到周日)
	 * @param flag
	 *            标志：1-向前查找日期 2-向后查找日期
	 * @return 指定周几的日期字符串<code>
	 * getWeekDay("2009-5-25",Calendar.MONDAY,1)返回2009-5-25
	 * getWeekDay("2009-5-25",Calendar.SUNDAY,2)返回2009-5-31
	 * @throws ParseException
	 */
	public static String getWeekDay(String Date, int dateValue, int flag)
			throws ParseException {
		Calendar calObj = Calendar.getInstance();
		SimpleDateFormat sfObj = new SimpleDateFormat("yyyy-MM-dd");
		calObj.setTime(sfObj.parse(Date));
		if (dateValue != Calendar.SATURDAY) {
			if (flag == 1) {
				// 周一
				calObj.setFirstDayOfWeek(dateValue);
			} else {
				// 周日;
				calObj.setFirstDayOfWeek(dateValue + 1);
			}
		}
		calObj.set(Calendar.DAY_OF_WEEK, dateValue);
		return sfObj.format(calObj.getTime());
	}

	/**
	 * 查找指定日期的月初和月末日期
	 * 
	 * @param date
	 *            日期字符串格式 ：yyyy-MM-dd
	 * @param flag
	 *            1-月初1号，2-月末<code>
	 *      getMonth("2009-02-25",1) 返回2009-02-01
	 *      getMonth("2009-02-25",2) 返回2009-02-28
	 * @return
	 * @throws Exception
	 */
	public static Date getMonth(String date, int flag) throws Exception {
		Calendar ca = Calendar.getInstance();
		SimpleDateFormat sfObj = new SimpleDateFormat(PATTREN_DATE);
		ca.setTime(sfObj.parse(date));
		Date rtDate = null;
		if (flag == 1) {
			// 月初
			ca.set(Calendar.DAY_OF_MONTH, 1);
			rtDate = ca.getTime();
		} else {
			// 月底;
			ca.set(Calendar.DAY_OF_MONTH, 1);
			rtDate = ca.getTime();
			ca.add(Calendar.MONTH, 1);
			ca.add(Calendar.DAY_OF_MONTH, -1);
			rtDate = ca.getTime();
		}
		return rtDate;
	}

	/**
	 * 计算两个日期之间的分钟数
	 * 
	 * @param date1
	 *            日期1 格式：yyyy-MM-dd HH:mm:ss 要求比日期2大
	 * @param date2
	 *            日期2 格式：yyyy-MM-dd HH:mm:ss
	 * @return 分钟差 如：2009-06-12 09:30:00 与2009-06-12 08:00:00 结果为：90
	 * @throws Exception
	 */
	public static int calMinutes(Date date1, Date date2) throws Exception {
		int times = 0;
		times = (int) ((date1.getTime() - date2.getTime()) / (60 * 1000));
		return times;
	}

	/**
	 * 转换某天为星期几
	 * 
	 * @param day
	 * @return
	 */
	public static String converDay2Week(Date day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(day);
		int iday = cal.get(Calendar.DAY_OF_WEEK);
		String dayStr = "";
		if (iday == 1) {
			dayStr = "星期日";
		} else if (iday == 2) {
			dayStr = "星期一";
		} else if (iday == 3) {
			dayStr = "星期二";
		} else if (iday == 4) {
			dayStr = "星期三";
		} else if (iday == 5) {
			dayStr = "星期四";
		} else if (iday == 6) {
			dayStr = "星期五";
		} else if (iday == 7) {
			dayStr = "星期六";
		}

		return dayStr;
	}

	/**
	 * 根据字符串获取到相应的时间的小时;
	 * 
	 * @param str
	 *            时间点:08:09
	 * @return
	 */
	public static int getHourByString(String str) {
		int hour = 0;
		if (str != null && str.length() > 0) {
			String[] temp = str.split(":");
			String tempHour = temp[0].trim();
			if (tempHour != null) {
				hour = Integer.parseInt(tempHour);
			}
		}
		return hour;
	}

	/**
	 * 根据字符串获取到相应的时间的分钟;
	 * 
	 * @param str
	 *            时间点:08:09
	 * @return
	 */
	public static int getMinuteByString(String str) {
		int hour = 0;
		if (str != null && str.length() > 0) {
			String[] temp = str.split(":");
			String tempHour = temp[1].trim();
			if (tempHour != null) {
				hour = Integer.parseInt(tempHour);
			}
		}
		return hour;
	}

}
