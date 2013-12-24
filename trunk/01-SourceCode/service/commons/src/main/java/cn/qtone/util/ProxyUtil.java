package cn.qtone.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 动态代理类(拦截异常,并记录日志) <br>
 *               拦截并处理所有使用本代理的接口实现类中抛出的所有异常,并记录日志. <br>
 *               使用方法:使用如下方式实例化接口时,该接口方法由本代理类代理. <br>
 *               如 Busi为接口,BusiImpl为Busi接口的实现类.
 * 
 *               <pre>
 * Busi busi = IProxy.init().bind(new BusiImpl());
 * </pre>
 * @author 卢俊生
 */
public class ProxyUtil implements InvocationHandler {
	private static final Logger log = LoggerFactory.getLogger(ProxyUtil.class);

	// 无参构造器,访问权限设置为private,外部无法使用构造器自行创建实例
	private ProxyUtil() {
	}

	/**
	 * 被代理接口对象,待处理对象
	 */
	private Object targetObj;

	/**
	 * 初始化,获取代理实例<BR>
	 * <B>此处用单例模式可能导致targetObj被修改,<BR>
	 * 从而出现java.lang.IllegalArgumentException</B>
	 */
	public static ProxyUtil init() {
		return new ProxyUtil();
	}

	/**
	 * 动态生成一个代理类对象,并绑定被代理接口和代理处理器
	 * 
	 * @param targetObj 被代理接口对象
	 * @return 代理类对象
	 */
	@SuppressWarnings("unchecked")
	public <T> T bind(T targetObj) {
		this.targetObj = targetObj;

		return (T) Proxy.newProxyInstance(this.targetObj.getClass().getClassLoader(), this.targetObj.getClass().getInterfaces(), this);
	}

	/**
	 * 实现InvocationHandler.invoke<BR>
	 * 代理动态调用的方法,无需手动调用
	 * 
	 * @param proxy 代理类对象,{@link #bind(Object)}方法的返回值
	 * @param method 被代理接口的方法
	 * @param args 被代理接口的方法实参列表
	 * @return 执行结果,即被代理接口方法的返回值
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String methodName = this.targetObj.getClass().getSimpleName() + "." + method.getName();
		Object result = null;
		long start = 0l, end = 0l;

		start = System.currentTimeMillis();
		log.info("方法:{}, 开始执行", methodName);

		try {
			result = method.invoke(this.targetObj, args);
		} catch (Exception e) {
			log.error("", e);
		} finally {
			end = System.currentTimeMillis();
			log.info(constructMsg(methodName, (end - start), method.getParameterTypes(), args));
		}

		return result;
	}

	/**
	 * 构造日志信息
	 * 
	 * @param methodName 方法名
	 * @param time 执行时间(毫秒)
	 * @param args 方法参数
	 * @return String 格式化的日志信息
	 */
	private String constructMsg(String methodName, long time, Class<?>[] types, Object[] args) {
		StringBuffer msg = new StringBuffer();
		msg.append("方法:").append(methodName).append(", 执行结束, 耗时(毫秒):").append(time);

		if (args != null) {
			msg.append(", 参数:{");

			// 第一个参数,前不需","
			msg.append("[").append(types[0].getSimpleName()).append(":").append(args[0]).append("]");
			for (int i = 1; i < args.length; i++) {
				msg.append(",[").append(types[i].getSimpleName()).append(":").append(args[i]).append("]");
			}
			msg.append("}");
		}

		return msg.toString();
	}

}
