package cn.qtone.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 字符串处理工具类
 * 
 * @author 卢俊生 2012/7/27
 */
public class StringUtil {

	/**
	 * 判断对象是否为空
	 * 
	 * @param obj 待判断对象
	 * @return boolean true-空(null/空字符串/纯空白字符),false-非空
	 */
	public static final boolean isBlank(Object obj) {
		return obj == null || obj.toString().trim().length() == 0;
	}

	/**
	 * 判断字符串是否包含内容(与isNull方法相反)
	 * 
	 * @param obj 待判断对象
	 * @return boolean true-有内容,false-空(null/空字符串/纯空白字符)
	 */
	public static final boolean hasText(Object obj) {
		return !isBlank(obj);
	}

	/**
	 * 转换为字符串
	 * 
	 * @param obj 待转换对象
	 * @return String obj为空时返回空字符串
	 */
	public static final String toString(Object obj) {
		return obj == null ? "" : obj.toString().trim();
	}

	public static final String toString(Object[] array) {
		StringBuilder sb = new StringBuilder();
		if (array != null && array.length > 0) {
			sb.append(array[0]);
			for (int i = 1; i < array.length; i++) {
				sb.append(",").append(array[i]);
			}
		}
		return sb.toString();
	}

	public static final String toString(List<Object> list) {
		StringBuilder sb = new StringBuilder();
		if (list != null && !list.isEmpty()) {
			sb.append(list.get(0));
			for (int i = 1; i < list.size(); i++) {
				sb.append(",").append(list.get(i));
			}
		}
		return sb.toString();
	}

	/**
	 * 返回一个对象的字符串,多数是处理字符串的.
	 * 
	 * @param obj
	 *            对象
	 * @return 字符串
	 */
	public static final String trim(Object obj) {
		return obj == null ? "" : String.valueOf(obj).trim();
	}

	public static final List<String> toList(Object obj, String separtor) {
		List<String> list = new ArrayList<String>();
		if (hasText(obj)) {
			String[] temp = trim(obj).split(separtor);
			for (String str : temp) {
				if (hasText(str)) {
					list.add(str);
				}
			}
		}
		return list;
	}

	public static final List<String> toList(Object obj) {
		return toList(obj, ",");
	}

	public static final String[] toArray(Object obj, String separtor) {
		return toList(obj, separtor).toArray(new String[0]);
	}

	public static final String[] toArray(Object obj) {
		return toList(obj, ",").toArray(new String[0]);
	}

	public static final String toLowerCase(Object obj) {
		if (hasText(obj)) {
			return toString(obj).toLowerCase();
		} else {
			return null;
		}
	}

	public static final String toUpperCase(Object obj) {
		if (hasText(obj)) {
			return toString(obj).toUpperCase();
		} else {
			return null;
		}
	}

	/**
	 * 对比两个对象是否相同
	 * 
	 * <pre>
	 * 当obj1==null或obj2==null时,返回false;
	 * 否则返回 obj1.equals(obj2)
	 * </pre>
	 * 
	 * @param obj1
	 *            对象1
	 * @param obj2
	 *            对象2
	 * @return boolean
	 */
	public static final boolean equals(Object obj1, Object obj2) {
		boolean equals = false;

		if (obj1 != null && obj2 != null) {
			equals = obj1.equals(obj2);
		}

		return equals;
	}

	/**
	 * 对比两个字符串是否相同(忽略大小写)
	 * 
	 * <pre>
	 * 当obj1==null或obj2==null时,返回false;
	 * 否则返回 obj1.toString().equalsIgnoreCase(obj2.toString())
	 * </pre>
	 * 
	 * @param obj1
	 *            对象1
	 * @param obj2
	 *            对象2
	 * @return boolean
	 */
	public static final boolean equalsIgnoreCase(Object obj1, Object obj2) {
		boolean equals = false;

		if (obj1 != null && obj2 != null) {
			equals = obj1.toString().equalsIgnoreCase(obj2.toString());
		}

		return equals;
	}

	public static String beanToString(Object bean) {
		return ToStringBuilder.reflectionToString(bean, ToStringStyle.MULTI_LINE_STYLE);
	}

}
