/**
 * 
 */
package cn.qtone.util;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMap<String, Object>扩展,支持
 * @author 卢俊生, 2014-1-16
 */
public class ExtMap extends HashMap<String, Object> {

	private static final long serialVersionUID = 1L;

	public ExtMap() {
		super();
	}

	public ExtMap(int initialCapacity) {
		super(initialCapacity);
	}

	public ExtMap(Map<String, Object> map) {
		super(map);
	}

	/**
	 * 获取指定键所映射的值(忽略大小写,泛型)
	 * <pre>如Map中存在多个可映射的项,其优先级为 1.完全匹配>2.全大写匹配>3.全小写匹配>4.其它</pre>
	 * @param key 键
	 * @param clazz 值类型
	 * @return 指定键所映射的值
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(String key, Class<T> clazz) {
		Object obj = null;
		if (containsKey(key)) {
			obj = get(key);
		} else if (containsKey(key.toUpperCase())) {
			obj = get(key.toUpperCase());
		} else if (containsKey(key.toLowerCase())) {
			obj = get(key.toLowerCase());
		} else {
			for (java.util.Map.Entry<String, Object> entry : entrySet()) {
				if (entry.getKey().equalsIgnoreCase(key)) {
					obj = entry.getValue();
					break;
				}
			}
		}
		return (T) obj;
	}
}
