/**
 * 
 */
package cn.qtone.util;

import java.util.HashMap;
import java.util.Map;

/**
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

	@SuppressWarnings("unchecked")
	public <T> T get(String key, Class<T> clazz) {
		Object obj = null;
		for (java.util.Map.Entry<String, Object> entry : entrySet()) {
			if (entry.getKey().equalsIgnoreCase(key)) {
				obj = entry.getValue();
				break;
			}
		}
		return (T) obj;
	}
}
