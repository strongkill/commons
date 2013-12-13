package cn.qtone.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Date操作工具类
 * 
 * @author 卢俊生, 2012-8-31
 */
public class DateUtil {
	/** 格式: yyyy-MM-dd **/
	private static final String PATTERN_DATE = "yyyy-MM-dd";
	/** 格式: HH:mm:ss **/
	private static final String PATTERN_TIME = "HH:mm:ss";
	/** 格式: yyyy-MM-dd HH:mm:ss **/
	private static final String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";
	/** 格式: yyyy-MM-dd HH:mm:ss.SSS **/
	private static final String PATTERN_FULL = "yyyy-MM-dd HH:mm:ss.SSS";

	/**
	 * 解析时间/日期字符串
	 * 
	 * @param pattern 格式
	 * @param source 时间/日期字符串
	 * @return Date
	 */
	public static final Date parse(String pattern, String source) {
		try {
			return new SimpleDateFormat(pattern, Locale.US).parse(source);
		} catch (ParseException e) {
			throw new RuntimeException("parse date error : ", e);
		}
	}

	/**
	 * 解析时间/日期字符串,格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param source 时间/日期字符串
	 * @return Date
	 */
	public static final Date parseDateTime(String source) {
		try {
			return new SimpleDateFormat(PATTERN_DATETIME).parse(source);
		} catch (ParseException e) {
			throw new RuntimeException("parse date error : ", e);
		}
	}

	/**
	 * 解析日期字符串,格式：yyyy-MM-dd
	 * 
	 * @param source 日期字符串
	 * @return Date
	 */
	public static final Date parseDate(String source) {
		try {
			return new SimpleDateFormat(PATTERN_DATE).parse(source);
		} catch (ParseException e) {
			throw new RuntimeException("parse date error : ", e);
		}
	}

	/**
	 * 解析时间字符串,格式：HH:mm:ss
	 * 
	 * @param source 时间字符串
	 * @return Date
	 */
	public static final Date parseTime(String source) {
		try {
			return new SimpleDateFormat(PATTERN_TIME).parse(source);
		} catch (ParseException e) {
			throw new RuntimeException("parse date error : ", e);
		}
	}

	/**
	 * 解析时间字符串,格式：yyyy-MM-dd HH:mm:ss.SSS
	 * 
	 * @param source 时间字符串
	 * @return Date
	 */
	public static final Date parseFull(String source) {
		try {
			return new SimpleDateFormat(PATTERN_FULL).parse(source);
		} catch (ParseException e) {
			throw new RuntimeException("parse date error : ", e);
		}
	}

	/**
	 * 格式化Date
	 * 
	 * @param pattern 格式
	 * @param date date对象
	 * @return String 格式化日期字符串
	 */
	public static final String format(String pattern, Date date) {
		return new SimpleDateFormat(pattern, Locale.US).format(date);
	}

	/**
	 * 格式化Date,格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date date对象
	 * @return String
	 */
	public static final String formatDateTime(Date date) {
		return new SimpleDateFormat(PATTERN_DATETIME).format(date);
	}

	/**
	 * 格式化Date,格式：yyyy-MM-dd
	 * 
	 * @param date date对象
	 * @return String
	 */
	public static final String formatDate(Date date) {
		return new SimpleDateFormat(PATTERN_DATE).format(date);
	}

	/**
	 * 格式化Date,格式：HH:mm:ss
	 * 
	 * @param date date对象
	 * @return String
	 */
	public static final String formatTime(Date date) {
		return new SimpleDateFormat(PATTERN_TIME).format(date);
	}

	/**
	 * 格式化Date,格式：yyyy-MM-dd HH:mm:ss.SSS
	 * 
	 * @param date date对象
	 * @return String
	 */
	public static final String formatFull(Date date) {
		return new SimpleDateFormat(PATTERN_FULL).format(date);
	}

	/**
	 * 格式化时间/日期字符串
	 * 
	 * @param outPatt 输出格式
	 * @param inPatt 输入格式(与source对应)
	 * @param source 时间/日期字符串(与inPatt对应)
	 * @return String
	 */
	public static final String format(String outPatt, String inPatt, String source) {
		return format(outPatt, parse(inPatt, source));
	}

	/**
	 * 按指定格式取得当前日期(时间)
	 * 
	 * @param pattern 格式
	 * @return String
	 */
	public static final String getTimestamp(String pattern) {
		return format(pattern, new Date());
	}

	/**
	 * 计算两个日期年份的差值
	 * 
	 * @param fromDate 起始日期
	 * @param toDate 截止日期
	 * @return 年份差值
	 */
	public static final int calDValueOfYear(Date fromDate, Date toDate) {
		Calendar sCal = Calendar.getInstance();
		Calendar eCal = Calendar.getInstance();
		sCal.setTime(fromDate);
		eCal.setTime(toDate);

		return eCal.get(Calendar.YEAR) - sCal.get(Calendar.YEAR);
	}

	/**
	 * 计算两个日期月份的差值
	 * 
	 * @param fromDate 起始日期
	 * @param toDate 截止日期
	 * @return 月份差值
	 */
	public static final int calDValueOfMonth(Date fromDate, Date toDate) {
		Calendar sCal = Calendar.getInstance();
		Calendar eCal = Calendar.getInstance();
		sCal.setTime(fromDate);
		eCal.setTime(toDate);

		return 12 * (eCal.get(Calendar.YEAR) - sCal.get(Calendar.YEAR)) + (eCal.get(Calendar.MONTH) - sCal.get(Calendar.MONTH));
	}

	/**
	 * 计算两个日期的差值
	 * 
	 * @param fromDate 起始日期
	 * @param toDate 截止日期
	 * @return 相差天数
	 */
	public static final int calDValueOfDay(Date fromDate, Date toDate) {
		return (int) ((toDate.getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24));
	}

	/**
	 * 根据日期取得所在月的第一天
	 * 
	 * @param date 日期
	 * @return Date 月第一天
	 */
	public static final Date getFirstDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	/**
	 * 根据日期取得所在周的第一天(周第一天为周一)
	 * 
	 * @param date 日期
	 * @return Date 周第一天
	 */
	public static final Date getFirstDayOfWeek(Date date) {
		return getFirstDayOfWeek(date, Calendar.MONDAY);
	}

	/**
	 * 根据日期取得所在周的最后一天(周第一天为周一)
	 * 
	 * @param date 日期
	 * @return Date 周最后一天
	 */
	public static final Date getLastDayOfWeek(Date date) {
		return getLastDayOfWeek(date, Calendar.MONDAY);
	}

	/**
	 * 根据日期取得所在周的第一天
	 * 
	 * @param date 日期
	 * @param firstDayOfWeek 周第一天(如Calendar.SUNDAY为周日,Calendar.MONDAY为周一)
	 * @return Date 周第一天
	 */
	public static final Date getFirstDayOfWeek(Date date, int firstDayOfWeek) {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(firstDayOfWeek);

		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	/**
	 * 根据日期取得所在周的最后一天
	 * 
	 * @param date 日期
	 * @param firstDayOfWeek 周第一天(如Calendar.SUNDAY为周日,Calendar.MONDAY为周一)
	 * @return Date 周最后一天
	 */
	public static final Date getLastDayOfWeek(Date date, int firstDayOfWeek) {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(firstDayOfWeek);

		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		calendar.add(Calendar.DAY_OF_YEAR, 7);
		calendar.add(Calendar.MILLISECOND, -1);

		return calendar.getTime();
	}

	/**
	 * 根据日期取得所在周的第一天和最后一天(周第一天为周一)
	 * 
	 * @param date 日期
	 * @return Date[]
	 */
	public static final Date[] getWeek(Date date) {
		return getWeek(date, Calendar.MONDAY);
	}

	/**
	 * 根据日期取得所在周的第一天和最后一天(周第一天为周一)
	 * 
	 * @param date 日期
	 * @param firstDayOfWeek 周第一天(如Calendar.SUNDAY为周日,Calendar.MONDAY为周一)
	 * @return Date[]
	 */
	public static final Date[] getWeek(Date date, int firstDayOfWeek) {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(firstDayOfWeek);

		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date firstDate = calendar.getTime();

		calendar.add(Calendar.DAY_OF_YEAR, 7);
		calendar.add(Calendar.MILLISECOND, -1);
		Date lastDate = calendar.getTime();

		return new Date[] { firstDate, lastDate };
	}

	/**
	 * 将java.util.Date对象转换成java.sql.Date对象
	 * 
	 * @param date java.util.Date
	 * @return java.sql.Date
	 */
	public static java.sql.Date toSQLDate(Date date) {
		return new java.sql.Date(date.getTime());
	}

	/**
	 * 获取当前时间(java.util.Date)
	 * 
	 * @return java.util.Date
	 */
	public static java.sql.Date getSQLDate() {
		return new java.sql.Date(System.currentTimeMillis());
	}

	/**
	 * 加减Date各字段的值
	 * 
	 * @param date Date
	 * @param field 字段,如Calendar.DAY_OF_YEAR,Calendar.MINUTE等
	 * @param increment 增量,可为负值
	 * @return Date
	 */
	public static Date add(Date date, int field, int increment) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.add(field, increment);
		return cal.getTime();
	}

	/**
	 * 设置Date各字段的值
	 * 
	 * @param date Date
	 * @param field 字段,如Calendar.DAY_OF_YEAR,Calendar.MINUTE等
	 * @param increment 增量,可为负值
	 * @return Date
	 */
	public static Date set(Date date, int field, int value) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(field, value);
		return cal.getTime();
	}

//	public static void main(String[] args) {
//		System.out.println(formatFull(new Date()));
//	}
}
