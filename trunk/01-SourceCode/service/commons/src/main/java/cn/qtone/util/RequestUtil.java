package cn.qtone.util;

import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

/**
 * Request相关工具类
 * 
 * @author 卢俊生
 */
public class RequestUtil {

	/**
	 * 获取用户IP
	 * 
	 * @param request 请求
	 * @return 用户IP
	 */
	public static String getRemoteIp(HttpServletRequest request) {
		return request.getHeader("X-Real-IP") != null ? request.getHeader("X-Real-IP") : request.getRemoteAddr();
	}

	public static String getHost(HttpServletRequest request) {
		return request.getHeader("Host") != null ? request.getHeader("Host") : request.getServerName();
	}

	public static String getUri(HttpServletRequest request) {
		return request.getRequestURI().replaceFirst(request.getContextPath(), "");
	}

	public static String getUriWithQueryString(HttpServletRequest request) {
		String params = "";
		for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
			if (entry.getValue().length == 1) {
				if (StringUtil.hasText(entry.getValue()[0])) {
					params += "&" + entry.getKey() + "=" + entry.getValue()[0];
				}
			} else if (entry.getValue().length > 1) {
				params += "&" + entry.getKey() + "=[" + entry.getValue()[0];
				for (int i = 1; i < entry.getValue().length; i++) {
					params += "," + entry.getValue()[i];
				}
				params += "]";
			}
		}
		if (params.length() > 0) {
			params = params.replaceFirst("&", "?");
		}
		return getUri(request) + params;
	}

	public static String getQueryString(HttpServletRequest request) {
		String params = "";
		for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
			if (entry.getValue().length == 1) {
				if (StringUtil.hasText(entry.getValue()[0])) {
					params += "&" + entry.getKey() + "=" + entry.getValue()[0];
				}
			} else if (entry.getValue().length > 1) {
				params += "&" + entry.getKey() + "=[" + entry.getValue()[0];
				for (int i = 1; i < entry.getValue().length; i++) {
					params += "," + entry.getValue()[i];
				}
				params += "]";
			}
		}
		if (params.length() > 0) {
			params = params.substring(1);
		}
		return params;
	}

	public static String getParams(HttpServletRequest request) {
		String params = "";
		for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
			if (entry.getValue().length == 1) {
				if (StringUtil.hasText(entry.getValue()[0])) {
					params += ";" + entry.getKey() + ":" + entry.getValue()[0];
				}
			} else if (entry.getValue().length > 1) {
				params += ";" + entry.getKey() + ":[" + entry.getValue()[0];
				for (int i = 1; i < entry.getValue().length; i++) {
					params += "," + entry.getValue()[i];
				}
				params += "]";
			}
		}
		if (params.length() > 0) {
			params = params.substring(1);
		}
		return params;
	}

}
