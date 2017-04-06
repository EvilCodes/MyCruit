package com.oucre.core.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtil {

	public static String toString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	public static String toString(Date date, String ymd) {
		SimpleDateFormat sdf = new SimpleDateFormat(ymd);
		return sdf.format(date);
	}

	public static Date toDate(String str) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static java.sql.Date dateToSqlDate(Date date) {
		return new java.sql.Date(date.getTime());
	}

	public static java.sql.Date toSqlDate(String str) {
		return new java.sql.Date(toDate(str).getTime());
	}

	public static java.sql.Date toSqlDate(String str, String ymd) {
		return new java.sql.Date(toDate(str, ymd).getTime());
	}

	public static Date toDate(String str, String ymd) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(ymd);
			return sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date toDate(Timestamp timestamp) {
		Date date = new Date();
		try {
			date = timestamp;
			return date;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获得 开始 结束时间
	 * 
	 * @date 2015-1-31 下午4:13:17
	 * @param year
	 *            年份
	 * @param season
	 *            季节
	 * @param month
	 *            月份
	 * @param period
	 *            旬
	 * @param type
	 *            类型 y年 s季度 m月 p 旬
	 * @return
	 */
	public static Map<String, String> getStartAndEndDate(int year, int season, int month, int period, String type) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("start", toString(getStartDate(year, season, month, period, type), "yyyy-MM-dd"));
		map.put("end", toString(getEndDate(year, season, month, period, type), "yyyy-MM-dd"));
		return map;
	}

	/**
	 * 获得开始日期
	 * 
	 * @param year
	 * @param season
	 * @param month
	 * @param period
	 * @param type
	 * @return
	 */
	public static Date getStartDate(int year, int season, int month, int period, String type) {
		Date result = null;
		Calendar cal = Calendar.getInstance();
		cal.clear();
		if ("y".equalsIgnoreCase(type)) {
			cal.set(year, 0, 1);
		} else if ("s".equalsIgnoreCase(type)) {
			cal.set(year, (season * 3 - 2) - 1, 1);
		} else if ("m".equalsIgnoreCase(type)) {
			cal.set(year, month - 1, 1);
		} else if ("p".equalsIgnoreCase(type)) {
			cal.set(year, month - 1, (period - 1) * 10 + 1);
		}
		result = cal.getTime();
		return result;
	}

	/**
	 * 获得结束日期
	 * 
	 * @param year
	 * @param season
	 * @param month
	 * @param period
	 * @param type
	 * @return
	 */
	public static Date getEndDate(int year, int season, int month, int period, String type) {
		Date result = null;
		Calendar cal = Calendar.getInstance();
		cal.clear();
		if ("y".equalsIgnoreCase(type)) {
			cal.set(year, 11, 31);
		} else if ("s".equalsIgnoreCase(type)) {
			if (season == 2 || season == 3) {
				cal.set(year, season * 3 - 1, 30);
			} else if (season == 1 || season == 4) {
				cal.set(year, season * 3 - 1, 31);
			}
		} else if ("m".equalsIgnoreCase(type)) {
			if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
				cal.set(year, month - 1, 31);
			} else if (month == 4 || month == 6 || month == 9 || month == 11) {
				cal.set(year, month - 1, 30);
			} else if (month == 2) {
				if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
					cal.set(year, month - 1, 29);
				} else {
					cal.set(year, month - 1, 28);
				}
			}
		} else if ("p".equalsIgnoreCase(type)) {
			if (period == 3) {
				if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
					cal.set(year, month - 1, 31);
				} else if (month == 4 || month == 6 || month == 9 || month == 11) {
					cal.set(year, month - 1, 30);
				} else if (month == 2) {
					if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
						cal.set(year, month - 1, 29);
					} else {
						cal.set(year, month - 1, 28);
					}
				}
			} else if (period == 1 || period == 2) {
				cal.set(year, month - 1, period * 10);
			}
		}
		result = cal.getTime();
		return result;
	}
}
