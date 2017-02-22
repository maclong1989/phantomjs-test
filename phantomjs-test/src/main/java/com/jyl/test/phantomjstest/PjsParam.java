package com.jyl.test.phantomjstest;

import java.util.HashMap;
import java.util.Map;

public class PjsParam {

	private String url;

	private Object data;

	private Map<String, String> headers = new HashMap<String, String>();

	private static String jsPath;

	private String jsName;

	private PjsProxy proxy = new PjsProxy();

	private String method;

	private String userAgent;

	private String fileName;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public String getJsName() {
		return jsName;
	}

	public void setJsName(String jsName) {
		this.jsName = jsName;
	}

	public String getJsFullName() {
		return jsPath + "/" + jsName + ".js";
	}

	public static String getJsPath() {
		return jsPath;
	}

	public static void setJsPath(String jsPath) {
		PjsParam.jsPath = jsPath;
	}

	public PjsProxy getProxy() {
		return proxy;
	}

	public void setProxy(PjsProxy proxy) {
		this.proxy = proxy;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
