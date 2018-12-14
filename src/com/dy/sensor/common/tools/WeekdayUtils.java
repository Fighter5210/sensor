package com.dy.sensor.common.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/****
 * TimerTask与Timer
 * 
 * @author bruceleey
 * 
 */
public class WeekdayUtils {

	private static final int FIRST_DAY = Calendar.MONDAY;

	public static String getWeekdays(int week) {
		Calendar calendar = Calendar.getInstance();
		String weekDate = "";
		setToFirstDay(calendar);
		for (int i = 0; i < week; i++) {
			weekDate = printDay(calendar);
			calendar.add(Calendar.DATE, 1);
		}
		return weekDate;
	}

	private static void setToFirstDay(Calendar calendar) {
		while (calendar.get(Calendar.DAY_OF_WEEK) != FIRST_DAY) {
			calendar.add(Calendar.DATE, -1);
		}
	}

	private static String printDay(Calendar calendar) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(calendar.getTime());
	}

	public static void main(String[] args) {
		// 1表示周一的日期;
		System.out.println(getWeekdays(1));
	}

}