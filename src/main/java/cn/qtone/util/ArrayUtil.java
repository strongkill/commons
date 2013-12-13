package cn.qtone.util;

import java.util.List;

/**
 * Array工具类
 * 
 * @author 卢俊生, 2012-8-31
 */
public class ArrayUtil {

	/**
	 * 判断数组是否为空(null||empty)
	 * 
	 * @param array
	 * @return boolean
	 */
	public static final boolean isEmpty(Object[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * 判断数组是否包含项(length>0)
	 * 
	 * @param map
	 * @return boolean
	 */
	public static final boolean hasItem(Object[] array) {
		return !isEmpty(array);
	}

	/**
	 * 获取数组的length
	 * 
	 * @param array
	 * @return int 为null时返回0
	 */
	public static final int length(Object[] array) {
		int len = 0;

		if (!isEmpty(array)) {
			len = array.length;
		}

		return len;
	}

	/**
	 * 将数组转为字符串
	 * 
	 * @param array 数组
	 * @param separtor 分隔符
	 * @return 字符串
	 */
	public static final String toString(Object[] array, String separtor) {
		StringBuilder sb = new StringBuilder();
		if (array != null && array.length > 0) {
			sb.append(array[0]);
			for (int i = 1; i < array.length; i++) {
				sb.append(separtor).append(array[i]);
			}
		}
		return sb.toString();
	}

	/**
	 * 将数组转为字符串(分隔符:",")
	 * 
	 * @param array 数组
	 * @return 字符串
	 */
	public static final String toString(Object[] array) {
		return toString(array, ",");
	}

	/**
	 * 将数组转为List(泛型)
	 * 
	 * @param array
	 * @return List
	 */
	public static final <E> List<E> asList(E... array) {
		return java.util.Arrays.asList(array);
	}

	public static short[] getIntArray(int size, int val) {
		short array[] = new short[size];
		for (int i = 0; i < size; i++) {
			array[i] = (short) val;
		}
		return array;
	}
}
