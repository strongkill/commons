/**
 * 
 */
package cn.qtone.util;

import java.io.File;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;

/**
 * @author 卢俊生, 2013-9-26
 */
public class LogbackConfigurator {

	public static void doConfigure(String path) {
		try {
			File file = new File(path);
			if (file.exists()) {
				System.out.println("LogbackConfigurator => find configuration file : " + file);
				LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
				loggerContext.reset();
				JoranConfigurator joranConfigurator = new JoranConfigurator();
				joranConfigurator.setContext(loggerContext);
				joranConfigurator.doConfigure(file.getPath());
				System.out.println("LogbackConfigurator => doConfigure success!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void doConfigure() {
		doConfigure("logback.xml");
	}

	public static void main(String[] args) {
		doConfigure();
	}

}
