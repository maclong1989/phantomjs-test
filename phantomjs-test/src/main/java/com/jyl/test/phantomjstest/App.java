package com.jyl.test.phantomjstest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

import com.alibaba.fastjson.JSON;

/**
 * Hello world!
 *
 */
public class App {

	public static String getAjaxCotnent(PjsParam param) throws IOException {

		// 调用命令行运行phantomjs来执行s.js文件，这里的路径需要写全，否则是没有办法运行的，具体原因就不去考察了
		// 通过此方法返回的就是把AJAX页面完全加载之后的浏览器的内容，以字符串的形式返回

		Runtime rt = Runtime.getRuntime();

		String cmd = "D:/work/git/phantomjs/phantomjs-test/bin/phantomjs.exe " + param.getJsFullName() + " "
				+ JSON.toJSONString(param).replaceAll(" ", "^@@^").replace("\"", "\"\"\"").replace("%", "%%");

		System.out.println(cmd);

		Process p = rt.exec(cmd);

		InputStream is = p.getInputStream();

		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		StringBuffer sbf = new StringBuffer();

		String tmp = "";

		while ((tmp = br.readLine()) != null) {

			sbf.append(tmp);
		}
		return sbf.toString();
	}

	public static void main(String[] args) {

		try {

			PjsParam.setJsPath("D:/work/git/phantomjs/phantomjs-test/script");

			PjsParam param = new PjsParam();

			param.getProxy().setUse(false);
			param.getProxy().setHost("proxy.asiainfo.com");
			param.getProxy().setPort("8080");

			param.setUrl("https://y.qq.com/n/yqq/toplist/4.html");
			param.setData(new Object());
			param.setMethod("get");
			param.setFileName("D:/tmp/" + UUID.randomUUID().toString() + ".html");

			// param.getHeaders().put("Host", "www.sina.com.cn");
			param.getHeaders().put("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0");
			param.getHeaders().put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			param.getHeaders().put("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
			// param.getHeaders().put("Accept-Encoding", "deflate");
			param.getHeaders().put("Referer", "https://www.baidu.com");
			param.getHeaders().put("Connection", "keep-alive");
			// param.getHeaders().put("Host", "www.sina.com.cn");
			// param.getHeaders().put("Host", "www.sina.com.cn");

			param.setJsName("JsService");
			// param.setJsName("simple");

			String html = getAjaxCotnent(param);
			System.out.println(html);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
