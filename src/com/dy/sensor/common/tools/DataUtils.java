package com.dy.sensor.common.tools;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * 
 * @ClassName: DataUtils
 * @author myh
 * @date 2014-12-19 下午2:21:23
 * 
 */
public class DataUtils {

	public static final String DATE_STYLE_Date = "yyyy-MM-dd";

	public static final String DATE_STYLE_Datetime = "yyyy-MM-dd HH:mm:ss";

	public static final String DATE_STYLE_Datetime_01 = "yyyy-MM-dd HH:mm";

	/**
	 * 字符串转换成指定的日期格式;
	 * 
	 * @param str
	 * @param dateType
	 *            例：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date convertStringToDate(String str, String dateType) {

		SimpleDateFormat formatter = new SimpleDateFormat(dateType);
		Date date = null;
		try {
			date = formatter.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String getCurrentDateForStyle(String style) {

		return getDateTimeForStyle(new Date(), style);
	}

	public static String getDateTimeForStyle(Date date, String style) {

		SimpleDateFormat sdf = new SimpleDateFormat(style);
		return sdf.format(date);
	}

	public static String getFormatDateTime(Date date) {
		if (date == null)
			return "";
		return getDateTimeForStyle(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String getCurrentDateTime() {

		return getDateTimeForStyle(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	public static String getFormatDate(Date date) {

		return getDateTimeForStyle(date, "yyyy-MM-dd");
	}

	public static String getFormatTime(Date date) {

		return getDateTimeForStyle(date, "HH:mm:ss");
	}
	
	/**
	 * 日期转换成时间，不含有秒:HH:mm
	 * @param date
	 * @return
	 */
	public static String getFormatTimeNoss(Date date) {

		return getDateTimeForStyle(date, "HH:mm");
	}

	public static Timestamp getDateTimeForDelay(int days) {

		long second = System.currentTimeMillis() + 1000 * 60 * 60 * 24 * days;
		return new Timestamp(second);
	}

	public static String converSecondsToTime(long seconds, String style) {

		if (seconds < 0) {
			return null;
		}
		String res = style;
		seconds = seconds / 1000;
		String second = String.valueOf(seconds % 60);
		String minute = String.valueOf(seconds / 60 % 60);
		String hour = String.valueOf(seconds / (60 * 60));
		res = res.replaceAll("hh", hour).replaceAll("mm", minute)
				.replaceAll("ss", second);
		return res;
	}

	public static String converSecondsToTime(long seconds) {

		if (seconds < 0) {
			return null;
		}
		return converSecondsToTime(seconds, "hh:mm:ss");
	}

	public static Date convertStringToDate(String str) {

		SimpleDateFormat formatter = new SimpleDateFormat(DATE_STYLE_Datetime);
		Date date = null;
		try {
			date = formatter.parse(str);
		} catch (ParseException e) {
		}
		return date;
	}

	/**
	 * 获得上周日的日期;
	 * 
	 * @return
	 */
	public static String getLastSunday() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, 1);
		return sdf.format(cal.getTime());
	}

	/**
	 * 获得上周结束的日期;(上周六)
	 * 
	 * @return
	 */
	public static String getLastEnd() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.WEEK_OF_MONTH, -1);
		cal.set(Calendar.DAY_OF_WEEK, 7);
		return sdf.format(cal.getTime());
	}

	/**
	 * 获得上周开始的日期;(上上周日)
	 * 
	 * @return
	 */
	public static String getLastStart() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.WEEK_OF_MONTH, -1);
		cal.set(Calendar.DAY_OF_WEEK, 1);
		return sdf.format(cal.getTime());
	}

	/**
	 * 获得本周开始的日期;(上周日)
	 * 
	 * @return
	 */
	public static String getCurrentStart() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, 1);
		return sdf.format(cal.getTime());
	}

	/**
	 * 获得本周结束的日期;(本周六)
	 * 
	 * @return
	 */
	public static String getCurrentEnd() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, 7);
		return sdf.format(cal.getTime());
	}

	/**
	 * 获得上周一的日期;
	 * 
	 * @return
	 */
	public static String getLastMonday() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.WEEK_OF_MONTH, -1);
		cal.set(Calendar.DAY_OF_WEEK, 2);
		return sdf.format(cal.getTime());
	}

	/**
	 * 获得上个月的第一天的日期;
	 * 
	 * @return
	 */
	public static String getLastMonthFrist() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return sdf.format(cal.getTime());
	}

	/**
	 * 获得上个月的最后一天的日期;
	 * 
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String getLastMonthLast() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(cal.MONTH, -1);
		int value = cal.getActualMaximum(cal.DAY_OF_MONTH);
		cal.set(cal.DAY_OF_MONTH, value);
		return sdf.format(cal.getTime());
	}

	/**
	 * 获得本月的第一天的日期;
	 * 
	 * @return
	 */
	public static String getMonthFrist() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return sdf.format(cal.getTime());
	}

	/**
	 * 获得本月的最后一天的日期;
	 * 
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String getMonthEnd() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		int value = cal.getActualMaximum(cal.DAY_OF_MONTH);
		cal.set(cal.DAY_OF_MONTH, value);
		return sdf.format(cal.getTime());
	}

	/**
	 * 获得今天的日期;
	 * 
	 * @return
	 */
	public static String getNowDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		return sdf.format(cal.getTime());
	}

	public static int compareDate(String DATE1, String DATE2) {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				System.out.println("当前时间：" + DATE1 + " 比定时时间: " + DATE2
						+ " 晚,无须执行");
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				System.out.println("当前时间：" + DATE1 + " 比定时时间： " + DATE2
						+ " 早,无须执行");
				return -1;
			} else {
				System.out.println("当前时间 与 定时时间相同!");
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	/**
	 * 对比运行巡检的时间;
	 * 
	 * @param currentTime
	 *            当前系统时间;
	 * @param runTime
	 *            定时时间;
	 * @return
	 */
	public static int compareDateCheck(String currentTime, String runTime) {

		DateFormat df = new SimpleDateFormat("HH:mm");
		try {
			String timeRunTime = DataUtils.getFormatTimeNoss(DataUtils.convertStringToDate(runTime));
			String timeCurrentTime = DataUtils.getFormatTimeNoss(DataUtils.convertStringToDate(currentTime));
			Date dt1 = df.parse(timeCurrentTime);
			Date dt2 = df.parse(timeRunTime);
			if (dt1.getTime() > dt2.getTime()) {
				System.out.println("当前时间：" + currentTime + " 比定时时间: " + runTime
						+ " 晚,无须执行");
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				System.out.println("当前时间：" + currentTime + " 比定时时间： " + runTime
						+ " 早,无须执行");
				return -1;
			} else {
				System.out.println("当前时间 与 定时时间相同!");
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	public static void main(String[] args) throws Exception {

		String runTime = "2015-04-01 15:31:00";
		String currentTime = "2015-04-01 15:36:00";
	
		
		String timeRunTime = DataUtils.getFormatTimeNoss(DataUtils.convertStringToDate(runTime));
		String timeCurrentTime = DataUtils.getFormatTimeNoss(DataUtils.convertStringToDate(currentTime));
		int result = DataUtils.compareDateCheck(timeCurrentTime, timeRunTime);
		System.out.println(result);

	}

}
