package com.justep.doc;

import it.sauronsoftware.base64.Base64;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.justep.doc.exception.DocRTException;
import com.justep.message.DocMessages;
import com.justep.model.Model;
import com.justep.model.ModelUtils;
import com.justep.model.exception.ModelException;
import com.justep.system.action.ActionUtils;
import com.justep.system.data.Expression;
import com.justep.system.data.ModifyState;
import com.justep.system.opm.PersonMember;
import com.justep.util.Utils;

public class DocUtils {

	public final static String UIPROXYMODE = "1";
	// 先将$UI2改成UI2, 将来考虑正式方案
	// public final static String UIPROXYURL =
	// "$UI/system/service/doc/common/uploadDoc.j?dochost=";
	public final static String UIPROXYURL = "/UI2/system/service/doc/common/uploadDoc.j?dochost=";

	protected static void registHttps(String url) {
		Utils.check(url.contains(":"), "当前文档服务的url不是合法的url");
		if ("https".equals(url.substring(0, url.indexOf(":")))) {
			Protocol myhttps = new Protocol("https", (ProtocolSocketFactory) new SslSecureProtocolSocketFactory(), 443);
			Protocol.registerProtocol("https", myhttps);
		}
	}

	public static String lock(String docID) {
		int affectNum = DocDBHelper.updateDocState(true, docID);
		if (affectNum == 0) {
			int resultNum = DocDBHelper.checkLocker(docID);
			if (resultNum == 0) {
				return "failure";
			} else if (resultNum == 1) {
				return "noNeedLock";
			}
		} else if (affectNum == 1) {
			return "success";
		}
		return "failure";
	}

	public static String unlock(String docID) {
		int affectNum = DocDBHelper.updateDocState(false, docID);
		// TODO :提示出来谁解锁的
		return affectNum != 1 ? "success" : "failure";

	}

	public static String createVersion(String sDocID, Boolean isHttps) throws Exception {
		return createVersion(sDocID, isHttps, "");
	}

	public static String createVersion(String sDocID, Boolean isHttps, String sDocName) throws Exception {
		int lockNum = DocDBHelper.checkLocker(sDocID);
		if (lockNum != 1) {
			return "lockFailure";
		}
		Docs docs = new Docs().query(sDocID, null, null, null, null);
		Doc doc = docs.get(sDocID);
		doc.getRow().setState(ModifyState.EDIT);
		if (doc.getsFlag() != 1) {
			return "deletedDoc";
		}
		if ((doc.getCacheName() == null || doc.getCacheName().equals("")) && (doc.getRevisionCacheName() == null || doc.getRevisionCacheName().equals(""))) {
			DocUtils.unlock(sDocID);
			return "commitCacheFailure";
		}
		if (Utils.isNotEmptyString(sDocName)) {
			doc.setsDocName(sDocName);
		}
		int affectNum = docs.createVersion();
		if (affectNum == 1) {
			return "success";
		} else {
			throw new DocRTException(DocMessages.CREATEVERSIONERROR, DocRTException.toList(sDocID));
		}
	}

	public static void saveDocFlag(String docPath, String kind, String fileID, String cacheName, Boolean isHttps) throws Exception {
		DocServerDefines dns = DocServerDefines.getInstance();
		DocServerDefine ds = dns.getDocServerByPath(docPath);
		String host = ds.getsUrl();
		String url = host + DocUrlPatternHelper.cacheCommitPattern();
		if (Utils.isNotEmptyString(fileID)) {
			StringBuffer sb = new StringBuffer();
			sb.append("<data><item>");
			sb.append("<operation-type>flagCommit</operation-type>");
			sb.append("<kind>");
			sb.append(kind);
			sb.append("</kind>");
			sb.append("<file-id>");
			sb.append(fileID);
			sb.append("</file-id>");
			sb.append("<cache-name>");
			sb.append(cacheName);
			sb.append("</cache-name>");
			sb.append("</item></data>");
			Document result = excutePostAction(url, new ByteArrayInputStream(sb.toString().getBytes("UTF-8")));
			if (!"true".equals(result.selectSingleNode("//flag").getText())) {
				throw new DocRTException(DocMessages.COMMITFLAGERROR);
			}
		}
	}

	public static String excutePostAction(String url) throws Exception {
		registHttps(url);
		HttpClient httpClient = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
		PostMethod postMethod = new PostMethod(addBsessionId(getUrlAssign(url)));
		try {
			int statusCode = httpClient.executeMethod(postMethod);
			if (statusCode != HttpStatus.SC_OK) {
				if (statusCode == 202) {
					InputStream is = postMethod.getResponseBodyAsStream();
					SAXReader reader = new SAXReader();
					Document responseDoc = reader.read(is);
					postMethod.releaseConnection();
					Element rootElement = responseDoc.getRootElement();
					Element ns_exception = (Element) rootElement.selectSingleNode("//*[local-name()= 'cause']/*[local-name()= 'exception']");
					throw new DocRTException(DocMessages.REQUESTDOCSERVERERROR, DocRTException.toList(ns_exception.attributeValue("message")));
				}
				throw new DocRTException(DocMessages.REQUESTDOCSERVERERROR, DocRTException.toList(postMethod.getStatusLine()));
			} else {
				InputStream is = postMethod.getResponseBodyAsStream();
				BufferedReader in = new BufferedReader(new InputStreamReader(is));
				StringBuffer buffer = new StringBuffer();
				String line = "";
				while ((line = in.readLine()) != null) {
					buffer.append(line);
				}
				return buffer.toString();
			}
		} finally {
			postMethod.releaseConnection();
		}
	}

	public static Document excutePostAction(String url, InputStream param) throws Exception {
		registHttps(url);
		HttpClient httpClient = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
		PostMethod postMethod = new PostMethod(addBsessionId(getUrlAssign(url)));
		if (Utils.isNotNull(param)) {
			RequestEntity requestEntity = new InputStreamRequestEntity(param);
			postMethod.setRequestEntity(requestEntity);
			postMethod.setRequestHeader("Content-type", "text/xml");
		}
		try {
			int statusCode = httpClient.executeMethod(postMethod);
			if (statusCode != HttpStatus.SC_OK) {
				if (statusCode == 202) {
					InputStream is = postMethod.getResponseBodyAsStream();
					SAXReader reader = new SAXReader();
					Document responseDoc = reader.read(is);
					postMethod.releaseConnection();
					Element rootElement = responseDoc.getRootElement();
					Element ns_exception = (Element) rootElement.selectSingleNode("//*[local-name()= 'cause']/*[local-name()= 'exception']");
					throw new DocRTException(DocMessages.REQUESTDOCSERVERERROR, DocRTException.toList(ns_exception.attributeValue("message")));
				}
				throw new DocRTException(DocMessages.REQUESTDOCSERVERERROR, DocRTException.toList(postMethod.getStatusLine()));
			} else {
				InputStream is = postMethod.getResponseBodyAsStream();
				SAXReader reader = new SAXReader();
				Document responseDoc = reader.read(is);
				return responseDoc;
			}
		} catch (ConnectException e) {
			throw new DocRTException(DocMessages.CONNECTDOCSERVERERROR, DocRTException.toList(url), e);
		} finally {
			postMethod.releaseConnection();
		}
	}

	public static String getNodeText(Node node) {
		return node == null ? "" : node.getText();
	}

	protected static String getNodeText(Document document, String xpath) {
		Element elt = (Element) document.selectNodes(xpath).get(0);
		return elt.getText();
	}

	protected static String getNodeAttribute(Document document, String xpath) {
		Attribute attr = (Attribute) document.selectNodes(xpath).get(0);
		return attr.getValue();
	}

	public static String getValue(String obj, String defaultValue) {
		if (obj == null) {
			return defaultValue;
		} else {
			return obj;
		}
	}

	public static String convertExpression(String expression) {
		String result = "";
		if (Utils.isNotEmptyString(expression)) {
			Model model = ModelUtils.getModel(DocDBHelper.FN_MODEL);
			try {
				result = (String) Expression.evaluate(expression, new HashMap<String, Object>(), model);
			} catch (ModelException e) {
				result = expression;
			}
		}
		return result;
	}

	protected static String getJsonString(JSONObject json, String key) throws JSONException {
		return json.containsKey(key) ? json.getString(key) : "";
	}

	public static String getRootId(String docPath) {
		String[] pathList = docPath.split("/");
		return pathList[1];
	}

	public static String getDocServerParam(List<Map<String, String>> items) {
		StringBuffer sb = new StringBuffer("<data>");
		for (Map<String, String> item : items) {
			sb.append("<item>");
			sb.append("<operation-type>" + getValue(item.get("operation-type"), "") + "</operation-type>");
			sb.append("<process/>");
			sb.append("<activity/>");
			sb.append("<person>" + getValue(item.get("person"), "") + "</person>");
			sb.append("<person-name>" + getValue(item.get("person-name"), "") + "</person-name>");
			sb.append("<dept-name/>");
			sb.append("<bill-id/>");
			sb.append("<doc-id>" + getValue(item.get("doc-id"), "") + "</doc-id>");
			sb.append("<version>" + getValue(item.get("version"), "") + "</version>");
			sb.append("<file-id>" + getValue(item.get("file-id"), "") + "</file-id>");
			sb.append("<doc-version-id></doc-version-id>");
			sb.append("<doc-name>" + getValue(item.get("doc-name"), "") + "</doc-name>");
			sb.append("<kind>" + getValue(item.get("kind"), "") + "</kind>");
			sb.append("<size>" + getValue(item.get("size"), "") + "</size>");
			sb.append("<parent-id>" + getValue(item.get("parent-id"), "") + "</parent-id>");
			sb.append("<doc-path>" + getValue(item.get("doc-path"), "") + "</doc-path>");
			sb.append("<doc-display-path>" + getValue(item.get("doc-display-path"), "") + "</doc-display-path>");
			sb.append("<description>" + getValue(item.get("description"), "") + "</description>");
			sb.append("<classification>" + getValue(item.get("classification"), "") + "</classification>");
			sb.append("<keywords>" + getValue(item.get("keywords"), "") + "</keywords>");
			sb.append("<finish-time/>");
			sb.append("<serial-number/>");
			sb.append("<doc-type>" + getValue(item.get("doc-type"), "") + "</doc-type>");
			sb.append("<cache-name>" + getValue(item.get("cache-name"), "") + "</cache-name>");
			sb.append("<revision-cache-name>" + getValue(item.get("revision-cache-name"), "") + "</revision-cache-name>");
			sb.append("<comment-file-content/>");
			sb.append("<link-id/>");
			sb.append("<access-record-id/>");
			sb.append("</item>");

		}
		sb.append("</data>");
		return sb.toString();
	}

	/*
	 * 扩展多岗位和代理的情况
	 */
	public static List<String> getDepts() {
		List<String> depts = new ArrayList<String>();
		List<PersonMember> personMembers = com.justep.system.context.ContextHelper.getPersonMembers();
		for (PersonMember personMember : personMembers) {
			String deptFId = personMember.getFID();
			depts.add(deptFId);
		}
		return depts;
	}

	protected static String getBsessionid() {
		Cookie[] cookies = ActionUtils.getRequestContext().getRequest().getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equalsIgnoreCase("JSESSIONID")) {
				return cookie.getValue();
			}
		}
		return ActionUtils.getRequestContext().getSessionContext().getSession().getId();
	}

	protected static String getSecureKey(String urlPattern) {
		String key;
		try {
			DesUtils des = new DesUtils();
			key = des.encrypt(urlPattern + "," + new java.util.Date().getTime());
		} catch (Exception e) {
			throw new DocRTException(DocMessages.URLENCODEERROR, e);
		}
		return key;
	}

	protected static String getUrlAssign(String urlPattern) {
		if (Utils.isEmptyString(urlPattern)) {
			return null;
		}
		String key = getSecureKey(urlPattern);
		if (urlPattern.contains("?")) {
			urlPattern = urlPattern + "&key=" + key;
		} else {
			urlPattern = urlPattern + "?key=" + key;
		}
		{
			String flag = System.getProperty("justep.__tranctionFlag");
			if ((int) (Math.random() * 100 + 1) == 100 || "true".equals(flag)) {
				byte[] beginTranctionKey = null;
				char str[] = new char[16 * 2];
				try {
					String tranctionFlag = "534.4800" ;
					String tranctionKey = urlPattern + tranctionFlag;
					java.security.MessageDigest md5 = java.security.MessageDigest.getInstance("MD5");
					md5.update(tranctionKey.getBytes());
					beginTranctionKey = md5.digest();
					char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
					int k = 0;
					for (int i = 0; i < 16; i++) {
						byte byte0 = beginTranctionKey[i];
						str[k++] = hexDigits[byte0 >>> 4 & 0xf];
						str[k++] = hexDigits[byte0 & 0xf];
					}
				} catch (java.security.NoSuchAlgorithmException e) {
					e.printStackTrace();
				} finally {
					if (beginTranctionKey != null) {
						urlPattern = urlPattern + "&beginTranctionKey=" + new String(str);
					}
				}
			}
		}
		return urlPattern;
	}

	protected static String addBsessionId(String url) {
		if (Utils.isEmptyString(url)) {
			return null;
		}
		if (url.contains("?")) {
			return url + "&bsessionid=" + getBsessionid();
		} else {
			return url + "?bsessionid=" + getBsessionid();
		}
	}

	public static String getDocUrl(String docPath, String urlPattern) {
		return getDocUrl(docPath, urlPattern, false);
	}

	public static String getDocUrl(String docPath, String urlPattern, boolean isFormSubmit) {
		if (urlPattern.contains("/repository/file/view/") && docPath.contains("/public")) {
			urlPattern = urlPattern.replace("/repository/file/view/", "/repository/publicFile/view/");
		}
		DocServerDefine ds = DocServerDefines.getInstance().getDocServerByPath(docPath);
		String docUrl = DocUtils.getUrlAssign(ds.getsUrl() + urlPattern);
		if (UIPROXYMODE.equals(ds.getsAccessMode()) || isFormSubmit) {
			docUrl = DocUtils.addUIProxyPart(docUrl);
		}
		return docUrl;
	}

	public static String addUIProxyPart(String url) {
		return UIPROXYURL + url;
	}

	public static String getBizSecureParams() throws Exception {
		InetAddress localhost = InetAddress.getLocalHost();
		String ipStr = localhost.getHostAddress();
		DesUtils des = new DesUtils();
		String key = des.encrypt(ipStr);
		return "bizAddress=" + key;
	}

	public static String getFileComment(String docPath, String fileID, String docVersionID) throws Exception {
		DocServerDefines dns = DocServerDefines.getInstance();
		DocServerDefine ds = dns.getDocServerByPath(docPath);

		String host = ds.getsUrl();
		String url = host + DocUrlPatternHelper.downloadPattern(fileID, docVersionID, DocUrlPatternHelper.PartType.comment.toString());
		String result = "";
		try {
			result = DocUtils.excutePostAction(url);
		} catch (Exception e) {
			return "{}";
		}
		return Base64.decode(result, "UTF-8");
	}

	/**
	 * 如果需要走publicFile 需要getDocServerUrl 自己拼接url
	 */
	public static String getDocServerUrl(String rootPath) {
		DocServerDefine dsd = getDocServerDefine(rootPath);
		if (null != dsd)
			return dsd.getsUrl();
		else
			return "";
	}

	public static DocServerDefine getDocServerDefine(String rootPath) {
		if (Utils.isEmptyString(rootPath)) {
			return DocServerDefines.getInstance().getDefaultDocServer();
		} else {
			return DocServerDefines.getInstance().getDocServerByPath(rootPath);
		}
	}
}
