package cn.qtone.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Array工具类
 * 
 * @author 卢俊生, 2012-8-31
 */
public class ListUtil {

	/**
	 * 判断List是否为空(null||empty)
	 * 
	 * @param list
	 * @return boolean
	 */
	public static final boolean isEmpty(List<?> list) {
		return list == null || list.isEmpty();
	}

	/**
	 * 判断List是否包含项(size>0)
	 * 
	 * @param list
	 * @return boolean
	 */
	public static final boolean hasItem(List<?> list) {
		return !isEmpty(list);
	}

	/**
	 * 获取List的size
	 * 
	 * @param list
	 * @return int 为null时返回0
	 */
	public static final int size(List<?> list) {
		int size = 0;

		if (!isEmpty(list)) {
			size = list.size();
		}

		return size;
	}

	/**
	 * 将List转为字符串
	 * 
	 * @param list
	 * @param separtor 分隔符
	 * @return 字符串
	 */
	public static final String toString(List<?> list, String separtor) {
		StringBuilder sb = new StringBuilder();
		if (list != null && !list.isEmpty()) {
			sb.append(list.get(0));
			for (int i = 1; i < list.size(); i++) {
				sb.append(separtor).append(list.get(i));
			}
		}
		return sb.toString();
	}

	/**
	 * 将List转为字符串(分隔符:",")
	 * 
	 * @param list
	 * @return 字符串
	 */
	public static final String toString(List<?> list) {
		return toString(list, ",");
	}

	/**
	 * 将List转为数组
	 * 
	 * @param list
	 * @param es 数组实例
	 * @return
	 */
	public static final <E> E[] toArray(List<E> list, E[] es) {
		return list.toArray(es);
	}

	public static final <E> List<E> parse(E... es) {
		List<E> list = new ArrayList<E>();
		for (E e : es) {
			list.add(e);
		}
		return list;
	}

	public static final List<Map<String, Object>> replace(List<Map<String, Object>> target, List<Map<String, Object>> src, String key) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(target);
		if (ListUtil.hasItem(src) && ListUtil.hasItem(list)) {
			int index = 0;
			for (int i = 0; i < list.size(); i++) {
				if (index < src.size()) {
					Map<String, Object> item = src.get(index);
					Map<String, Object> tmp = list.get(i);
					if (item.get(key).equals(tmp.get(key))) {
						list.set(i, item);
						index++;
					}
				} else {
					break;
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		System.out.println(Map.class);
	}

//	public static final <E> List<E[]> pushPerRow(List<E[]> list, int index, E e) {
//		return null;
//	}

}
