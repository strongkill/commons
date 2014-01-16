/**
 * 
 */
package cn.qtone.util;

import java.util.HashMap;

/**
 * @author 卢俊生, 2014-1-16
 */
public class ExtMap extends HashMap<String, Object> {

	private static final long serialVersionUID = 1L;

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
