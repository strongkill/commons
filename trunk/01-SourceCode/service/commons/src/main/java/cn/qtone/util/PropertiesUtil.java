package cn.qtone.util;

import java.util.Properties;

public class PropertiesUtil {

	public static String get(Properties prop, String name) {
		return get(prop, name, true);
	}

	public static String get(Properties prop, String name, boolean allowNull) {
		String value = prop.getProperty(name);
		if (!allowNull && (value == null || "".equals(value))) {
			throw new NullPointerException(name);
		}
		return value;
	}

	public static Integer getInt(Properties prop, String name) {
		Integer result = null;
		String value = get(prop, name);
		if (value != null && !"".equals(value)) {
			result = Integer.parseInt(value);
		}
		return result;
	}

	public static int getInt(Properties prop, String name, int min, int max, int def) {
		int result = def;
		String value = get(prop, name);
		if (value != null && !"".equals(value)) {
			try {
				result = Integer.parseInt(value);
				if (result < min || result > max) {
					result = def;
				}
			} catch (NumberFormatException e) {
				result = def;
			}
		}
		return result;
	}

	public static Long getLong(Properties prop, String name) {
		Long result = null;
		String value = get(prop, name);
		if (value != null && !"".equals(value)) {
			result = Long.parseLong(value);
		}
		return result;
	}

	public static Long getLong(Properties prop, String name, long min, long max, long def) {
		long result = def;
		String value = get(prop, name);
		if (value != null && !"".equals(value)) {
			try {
				result = Long.parseLong(value);
				if (result < min || result > max) {
					result = def;
				}
			} catch (NumberFormatException e) {
				result = def;
			}
		}
		return result;
	}

	public static boolean getBoolean(Properties prop, String name, boolean def) {
		boolean result = def;
		String value = get(prop, name);
		if (value != null) {
			value = value.trim().toLowerCase();
			if ("true".equals(value)) {
				result = true;
			} else if ("false".equals(value)) {
				result = false;
			}
		}

		return result;
	}
}
