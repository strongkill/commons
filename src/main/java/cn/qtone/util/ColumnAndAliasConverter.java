package cn.qtone.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字段别名转换工具类
 * 
 * @author Junson
 */
public class ColumnAndAliasConverter {

	/**
	 * 字段别名转换
	 * 
	 * <pre>
	 * name(忽略大小写) => name
	 * user_name(忽略大小写) => userName
	 * </pre>
	 * 
	 * @param column 字段名(下划线"_"连接)
	 * @return 别名(驼峰命名)
	 */
	public static String columnToAlias(String column) {
		StringBuffer buf = new StringBuffer();
		if (column != null && !"".equals(column)) {
			String[] strArr = column.split("_");

			buf.append(strArr[0].toLowerCase());

			for (int i = 1; i < strArr.length; i++) {
				buf.append(strArr[i].substring(0, 1).toUpperCase()).append(strArr[i].substring(1).toLowerCase());
			}
		}

		return buf.toString();
	}

	/**
	 * 字段别名转换
	 * 
	 * <pre>
	 * name => name
	 * userName => user_name
	 * 注:全大写或者包含下划线不转换
	 * </pre>
	 * 
	 * @param alias 别名(驼峰命名)
	 * @return 字段名(下划线"_"连接)
	 */
	public static String aliasToColumn(String alias) {
		if (alias != null && !"".equals(alias) && alias.matches(".*[a-z].*")) {
			StringBuffer buf = null;

			if (alias.matches(".*[_|\\s].*")) {
				buf = new StringBuffer(alias.replaceAll("\\s", "_"));
			} else {
				buf = new StringBuffer(alias);
				Pattern u = Pattern.compile("[A-Z]");
				Matcher matcher = u.matcher(alias);

				int index = 0;
				while (matcher.find()) {
					buf.insert((matcher.start() + index), "_");        // 插入字符串
					index++;
				}
			}
			return buf.toString().toLowerCase();
		} else {
			return alias;
		}

	}

	/**
	 * 字段别名转换
	 * 
	 * @param alias 别名字符串(多个用","分隔)
	 * @return 字段名字符串(多个用","分隔)
	 */
	public static String aliasToColumns(String alias) {
		String[] array = alias.split(",");
		for (int i = 0; i < array.length; i++) {
			array[i] = aliasToColumn(array[i]);
		}
		return ArrayUtil.toString(array);
	}

	public static void main(String[] args) {
		System.out.println(columnToAlias("AAA_BBB_CCC"));
		System.out.println(aliasToColumn("aaaBbbCcc"));
		System.out.println(aliasToColumns("aaBb,ccDd"));
	}

}
