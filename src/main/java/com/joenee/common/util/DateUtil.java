package com.joenee.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtil {

	/**
	 * 格式化时间指定：yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return yyyy
	 */
	public static String parseString(Date date) {
		SimpleDateFormat fort = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return fort.format(date);
	}
	
	/**
	 * 格式化时间指定：yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return yyyy
	 */
	public static Date parseDate(String str) {
		SimpleDateFormat fort = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return fort.parse(str);
		} catch (ParseException e) {
			LoggerUtil.log.error("",e);
			return null;
		}
	}
	
	/**
	 * 格式化时间指定：yyyy-MM-dd
	 * @param date
	 * @return yyyy
	 */
	public static Date parseDateYmd(String str) {
		SimpleDateFormat fort = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fort.parse(str);
		} catch (ParseException e) {
			LoggerUtil.log.error("",e);
			return null;
		}
	}
	
	/**
	 * 格式化时间指定：yyyyMMdd
	 * @param string
	 * @return date
	 */
	public static Date parseDateYmdd(String str) {
		SimpleDateFormat fort = new SimpleDateFormat("yyyyMMdd");
		try {
			return fort.parse(str);
		} catch (ParseException e) {
			LoggerUtil.log.error("",e);
			return null;
		}
	}
	
	/**
	 * 格式化时间指定：yyyy
	 * @param date
	 * @return yyyy
	 */
	public static String parseYear(Date date) {
		SimpleDateFormat fort = new SimpleDateFormat("yyyy");
		return fort.format(date);
	}

	/**
	 * 格式化时间指定：yyyy-MM-dd
	 * @param date
	 * @return yyyy
	 */
	public static String parseYmd(Date date) {
		SimpleDateFormat fort = new SimpleDateFormat("yyyy-MM-dd");
		return fort.format(date);
	}
	
	/**
	 * 格式化时间指定：yyyy-MM-dd
	 * @param date
	 * @return Date
	 */
	public static Date parseYmdd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	/**
	 * Get the previous time, from how many days to now.
	 * 
	 * @param days How many days.
	 * @return The new previous time.
	 */
	public static Date previous(int days) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -days);
		return cal.getTime();
	}
	
	/**
	 * 获取季度
	 * @param date
	 * @return
	 */
	public static int getQuarter(Date date) {
		Calendar cal = Calendar.getInstance();
		if(null != date) cal.setTime(date);
		int month = cal.get(Calendar.MONTH) + 1;
		int quarter = 1;
		if(month >= 1 && month <= 3) quarter = 1;
		else if(month >= 4 && month <= 6) quarter = 2;
		else if(month >= 7 && month <= 9) quarter = 3;
		else if(month >= 10 && month <= 12) quarter = 4;
		return quarter;
	}
	
	/**
	 * 格式化时间指定：yyyyMMddHHmmss
	 * @param date
	 * @return yyyy
	 */
	public static String parseStringTwo(Date date) {
		SimpleDateFormat fort = new SimpleDateFormat("yyyyMMddHHmmss");
		return fort.format(date);
	}
}
