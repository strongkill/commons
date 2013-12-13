package cn.qtone.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.qtone.concurrent.ThreadTimer;

public class NumberUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(ThreadTimer.class);

	/**
	 * 转换为整型(Integer)
	 * 
	 * @param obj
	 *            待转换对象
	 * @param defaultVal
	 *            默认值(无法转换时返回该值)
	 * @return Integer
	 */
	public static final Integer parseInt(Object obj, int defaultVal) {
		try {
			return Integer.parseInt(obj.toString());
		} catch (Exception e) {
			LOGGER.warn("parse integer failed : {}, defaultVal : {}", obj, defaultVal);
			return defaultVal;
		}
	}

	/**
	 * 转换为整型(Integer)
	 * 
	 * @param obj
	 *            待转换对象
	 * @return Integer
	 */
	public static final Integer parseInt(Object obj) {
		try {
			return Integer.parseInt(obj.toString());
		} catch (Exception e) {
			LOGGER.warn("parse integer failed : {}", obj);
			return null;
		}
	}

	/**
	 * 转换为长整型(Long)
	 * 
	 * @param obj
	 *            待转换对象
	 * @param defaultVal
	 *            默认值(无法转换时返回该值)
	 * @return Long
	 */
	public static final Long parseLong(Object obj, long defaultVal) {
		try {
			return Long.parseLong(obj.toString());
		} catch (Exception e) {
			LOGGER.warn("parse long failed : {}, defaultVal : {}", obj, defaultVal);
			return defaultVal;
		}
	}

	/**
	 * 转换为长整型(Long)
	 * 
	 * @param obj
	 *            待转换对象
	 * @return Long
	 */
	public static final Long parseLong(Object obj) {
		try {
			return Long.parseLong(obj.toString());
		} catch (Exception e) {
			LOGGER.warn("parse long failed : {}", obj);
			return null;
		}
	}

	/**
	 * 转换为浮点数(Float)
	 * 
	 * @param obj
	 *            待转换对象
	 * @param defaultVal
	 *            默认值(无法转换时返回该值)
	 * @return Float
	 */
	public static final Float parseFloat(Object obj, float defaultVal) {
		try {
			return Float.parseFloat(obj.toString());
		} catch (Exception e) {
			LOGGER.warn("parse float failed : {}, defaultVal : {}", obj, defaultVal);
			return defaultVal;
		}
	}

	/**
	 * 转换为浮点数(Float)
	 * 
	 * @param obj
	 *            待转换对象
	 * @param defaultVal
	 *            默认值(无法转换时返回该值)
	 * @return Float
	 */
	public static final Float parseFloat(Object obj) {
		try {
			return Float.parseFloat(obj.toString());
		} catch (Exception e) {
			LOGGER.warn("parse float failed : {}", obj);
			return null;
		}
	}

	/**
	 * 转换为双精度浮点数(Double)
	 * 
	 * @param obj
	 *            待转换对象
	 * @param defaultVal
	 *            默认值(无法转换时返回该值)
	 * @return Double
	 */
	public static final Double parseDouble(Object obj, double defaultVal) {
		try {
			return Double.parseDouble(obj.toString());
		} catch (Exception e) {
			LOGGER.warn("parse double failed : {}, defaultVal : {}", obj, defaultVal);
			return defaultVal;
		}
	}

	/**
	 * 转换为双精度浮点数(Double)
	 * 
	 * @param obj
	 *            待转换对象
	 * @return Double
	 */
	public static final Double parseDouble(Object obj) {
		try {
			return Double.parseDouble(obj.toString());
		} catch (Exception e) {
			LOGGER.warn("parse double failed : {}", obj);
			return null;
		}
	}

	/**
	 * 将字符串转换为Integer集合
	 * 
	 * @param obj
	 *            待转换字符串
	 * @param separtor
	 *            分隔符
	 * @return List<Integer>
	 */
	public static final List<Integer> parseIntList(Object obj, String separtor) {
		List<Integer> list = new ArrayList<Integer>();
		if (StringUtil.hasText(obj)) {
			String[] temp = StringUtil.trim(obj).split(separtor);
			for (String str : temp) {
				Integer i = parseInt(str);
				if (i != null) {
					list.add(i);
				}
			}
		}
		return list;
	}

	/**
	 * 将字符串转换为Integer集合(分隔符:",")
	 * 
	 * @param obj
	 *            待转换字符串
	 * @return List<Integer>
	 */
	public static final List<Integer> parseIntList(Object obj) {
		return parseIntList(obj, ",");
	}

	/**
	 * 将字符串转换为Integer数组
	 * 
	 * @param obj
	 *            待转换字符串
	 * @param separtor
	 *            分隔符
	 * @return Integer[]
	 */
	public static final Integer[] parseIntArray(Object obj, String separtor) {
		return parseIntList(obj, separtor).toArray(new Integer[0]);
	}

	/**
	 * 将字符串转换为Integer数组(分隔符:",")
	 * 
	 * @param obj
	 *            待转换字符串
	 * @return Integer[]
	 */
	public static final Integer[] parseIntArray(Object obj) {
		return parseIntList(obj, ",").toArray(new Integer[0]);
	}

	/**
	 * 将字符串转换为Long集合
	 * 
	 * @param obj
	 *            待转换字符串
	 * @param separtor
	 *            分隔符
	 * @return List<Long>
	 */
	public static final List<Long> parseLongList(Object obj, String separtor) {
		List<Long> list = new ArrayList<Long>();
		if (StringUtil.hasText(obj)) {
			String[] temp = StringUtil.trim(obj).split(separtor);
			for (String str : temp) {
				Long l = parseLong(str);
				if (l != null) {
					list.add(l);
				}
			}
		}
		return list;
	}

	/**
	 * 将字符串转换为Long集合(分隔符:",")
	 * 
	 * @param obj
	 *            待转换字符串
	 * @return List<Long>
	 */
	public static final List<Long> parseLongList(Object obj) {
		return parseLongList(obj, ",");
	}

	/**
	 * 将字符串转换为Long数组
	 * 
	 * @param obj
	 *            待转换字符串
	 * @param separtor
	 *            分隔符
	 * @return Long[]
	 */
	public static final Long[] parseLongArray(Object obj, String separtor) {
		return parseLongList(obj, separtor).toArray(new Long[0]);
	}

	/**
	 * 将字符串转换为Long数组(分隔符:",")
	 * 
	 * @param obj
	 *            待转换字符串
	 * @return Long[]
	 */
	public static final Long[] parseLongArray(Object obj) {
		return parseLongList(obj, ",").toArray(new Long[0]);
	}
}
