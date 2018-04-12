package fsexchange.ws.send;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;

import fsexchange.util.UUIDMaker;

public abstract class Service {
	private static final Logger logger = Logger.getLogger(Service.class);

	public URL getWsdlLocation(String wsUrl) {
		URL url = null;
		try {
			URL baseUrl;
			baseUrl = Service.class.getResource(".");
			url = new URL(baseUrl, wsUrl);
		} catch (MalformedURLException e) {
			logger.warn("Failed to create URL for the wsdl Location: '" + wsUrl + "', retrying as a local file");
			logger.warn(e.getMessage());
		}
		return url;
	}

	public URL getWsdlLocation(String serviceUrl, String intferfaceName) {
		URL url = null;
		String wsUrl = serviceUrl + "/" + intferfaceName + ".wsdl";
		try {
			URL baseUrl;
			baseUrl = Service.class.getResource(".");
			url = new URL(baseUrl, wsUrl);
		} catch (MalformedURLException e) {
			logger.warn("Failed to create URL for the wsdl Location: '" + wsUrl + "', retrying as a local file");
			logger.warn(e.getMessage());
		}
		return url;
	}

	public static String createNewGuid(String prev, int length) {
		String s = prev + UUIDMaker.generate();
		return pad(s.toUpperCase(), length, '0', false);
	}

	public static String pad(String str, int length, char p, boolean isprefixed) {
		if (str == null)
			str = "";
		int str_size = str.length();
		int pad_len = length - str_size;
		StringBuffer retvalue = new StringBuffer();
		for (int i = 0; i < pad_len; i++) {
			retvalue.append(p);
		}
		if (isprefixed)
			return retvalue.append(str).toString();
		return retvalue.insert(0, str).toString();
	}

	/**
	 * 字节截断
	 * 
	 * @param original
	 * @param byteLen
	 * @param charsetName
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String cutString(String original, int byteLen, String charsetName) throws UnsupportedEncodingException {
		if (original == null || byteLen <= 0) {
			return null;
		}
		byte[] bt = original.getBytes(charsetName);
		if (byteLen > bt.length) {
			return original;
		}
		// 按照指定字节长度截断，再转成临时String，计算长度。
		int tempLen = new String(bt, 0, byteLen, charsetName).length();
		// 根据该长度右截取原字符串。
		String result = original.substring(0, tempLen);
		// 超过预订字节长度，则减一个字符截取。
		if (result != null && !"".equals(result.trim()) && result.getBytes(charsetName).length > byteLen) { // 判断字节长度。
			result = original.substring(0, tempLen - 1); // 超长，去掉一个字符
		}
		return result;
	}

}
