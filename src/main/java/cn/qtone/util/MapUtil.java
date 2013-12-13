package cn.qtone.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Map工具类
 * 
 * @author 卢俊生, 2012-8-30
 */
public class MapUtil {

	/**
	 * 判断Map是否为空(null||empty)
	 * 
	 * @param map
	 * @return boolean
	 */
	public static final boolean isEmpty(Map<?, ?> map) {
		return map == null || map.isEmpty();
	}

	/**
	 * 判断Map是否包含项(size>0)
	 * 
	 * @param map
	 * @return boolean
	 */
	public static final boolean hasItem(Map<?, ?> map) {
		return !isEmpty(map);
	}

	/**
	 * 获取Map的size
	 * 
	 * @param map
	 * @return int 为null时返回0
	 */
	public static final int size(Map<?, ?> map) {
		int size = 0;

		if (!isEmpty(map)) {
			size = map.size();
		}

		return size;
	}

	/**
	 * 构造Map实例(泛型,过滤空值)
	 * 
	 * @param keys 键(数组)
	 * @param values 值(数组)
	 * @return Map
	 */
	public static final <K, V> Map<K, V> toMap(K[] keys, V[] values) {
		Map<K, V> map = new HashMap<K, V>();

		if (ArrayUtil.hasItem(keys) && ArrayUtil.hasItem(values)) {
			values = Arrays.copyOf(values, keys.length);

			for (int i = 0; i < keys.length; i++) {
				if (StringUtil.hasText(keys[i]) && StringUtil.hasText(values[i])) {
					map.put(keys[i], values[i]);
				}
			}
		}

		return map;
	}

	/**
	 * 构造Map实例(过滤空值)
	 * 
	 * @param keys 键(字符串,用","分隔)
	 * @param values 值(数组)
	 * @return Map
	 */
	public static final Map<String, Object> toMap(String keys, Object... values) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (StringUtil.hasText(keys) && ArrayUtil.hasItem(values)) {
			String[] keyArr = keys.split(",");
			values = Arrays.copyOf(values, keyArr.length);

			for (int i = 0; i < keyArr.length; i++) {
				if (StringUtil.hasText(keyArr[i]) && StringUtil.hasText(values[i])) {
					map.put(keyArr[i].trim(), values[i]);
				}
			}
		}

		return map;
	}

	/**
	 * 构造Map实例
	 * 
	 * @param keys 键(数组)
	 * @param values 值(数组)
	 * @return Map
	 */
	public static final <K, V> Map<K, V> toMapIntact(K[] keys, V[] values) {
		Map<K, V> map = new HashMap<K, V>();

		if (ArrayUtil.hasItem(keys)) {
			values = Arrays.copyOf(values, keys.length);

			for (int i = 0; i < keys.length; i++) {
				map.put(keys[i], values[i]);
			}
		}

		return map;
	}

	/**
	 * 构造Map实例
	 * 
	 * @param keys 键(字符串,用","分隔)
	 * @param values 值(数组)
	 * @return Map
	 */
	public static final Map<String, Object> toMapIntact(String key, Object... values) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (StringUtil.hasText(key)) {
			String[] keys = key.split(",");
			values = Arrays.copyOf(values, keys.length);

			for (int i = 0; i < keys.length; i++) {
				map.put(keys[i], values[i]);
			}
		}

		return map;
	}

}
