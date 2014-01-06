package cn.qtone.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.logicalcobwebs.proxool.ProxoolDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 从配置文件中加载数据源
 * 
 * <pre>
 * 文件系统当前目录或类路径根目录中的配置文件proxool.properties,当前目录中的配置文件优先级较高
 * 依赖proxool-0.9.1,proxool-cglib-0.9.1
 * </pre>
 * 
 * @author 卢俊生, 2014-1-6
 */
public class ProxoolDataSourceUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProxoolDataSourceUtil.class);

	public static final ProxoolDataSource load() {
		try {
			Properties prop = new Properties();

			// 读取配置文件,当前目录配置文件优先
			File file = new File("proxool.properties");
			if (file.exists()) {
				prop.load(new FileInputStream(file));
			} else {
				prop.load(ProxoolDataSourceUtil.class.getResourceAsStream("/proxool.properties"));
			}

			if (LOGGER.isDebugEnabled()) {
				@SuppressWarnings("unchecked")
				Enumeration<String> names = (Enumeration<String>) prop.propertyNames();
				while (names.hasMoreElements()) {
					String name = names.nextElement();
					LOGGER.debug("proxool : {} = {}", name, prop.getProperty(name));
				}
			}

			ProxoolDataSource dataSource = new ProxoolDataSource();

			dataSource.setAlias(PropertiesUtil.get(prop, "alias"));
			dataSource.setDriver(PropertiesUtil.get(prop, "driver-class", false));
			dataSource.setDriverUrl(PropertiesUtil.get(prop, "driver-url", false));
			dataSource.setUser(PropertiesUtil.get(prop, "user", false));
			dataSource.setPassword(PropertiesUtil.get(prop, "password", false));

			Integer houseKeepingSleepTime = PropertiesUtil.getInt(prop, "house-keeping-sleep-time");
			if (houseKeepingSleepTime != null) {
				dataSource.setHouseKeepingSleepTime(houseKeepingSleepTime);
			}

			Long maximumActiveTime = PropertiesUtil.getLong(prop, "maximum-active-time");
			if (maximumActiveTime != null) {
				dataSource.setMaximumActiveTime(maximumActiveTime);
			}
			Integer maximumConnectionCount = PropertiesUtil.getInt(prop, "maximum-connection-count");
			if (maximumConnectionCount != null) {
				dataSource.setMaximumConnectionCount(maximumConnectionCount);
			}
			Integer minimumConnectionCount = PropertiesUtil.getInt(prop, "minimum-connection-count");
			if (minimumConnectionCount != null) {
				dataSource.setMinimumConnectionCount(minimumConnectionCount);
			}
			Integer simultaneousBuildThrottle = PropertiesUtil.getInt(prop, "simultaneous-build-throttle");
			if (simultaneousBuildThrottle != null) {
				dataSource.setSimultaneousBuildThrottle(simultaneousBuildThrottle);
			}
			Integer prototypeCount = PropertiesUtil.getInt(prop, "prototype-count");
			if (prototypeCount != null) {
				dataSource.setPrototypeCount(prototypeCount);
			}

			return dataSource;
		} catch (Exception e) {
			LOGGER.error("Configure dataSource failed!");
			throw new RuntimeException(e);
		}
	}
}
