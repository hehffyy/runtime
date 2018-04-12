package fsexchange.ws.send;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.log4j.Logger;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.butone.data.BizDataUtils;
import com.butone.flowbiz.SuspendKind;
import com.justep.doc.DocUtils;
import com.justep.model.Config;
import com.justep.model.ModelUtils;
import com.justep.system.action.ActionUtils;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.KSQL;
import com.justep.system.data.Row;
import com.justep.system.data.Table;
import com.justep.system.data.Transaction;
import com.justep.system.util.CommonUtils;

import fsexchange.exception.ExchangeException;
import fsexchange.util.BTFtpClient;
import fsexchange.util.CompareCallBack;
import fsexchange.util.SortUtil;
import fsexchange.util.TimeUtil;
import fsexchange.ws.send.data.AuthenticateXml;
import fsexchange.ws.send.data.Cooperation;
import fsexchange.ws.send.data.DataXml;
import fsexchange.ws.send.data.XmlOut;
import fsexchange.ws.send.data.cooperation.Application;
import fsexchange.ws.send.data.cooperation.Permission;
import fsexchange.ws.send.data.cooperation.permission.Complementnode;
import fsexchange.ws.send.data.cooperation.permission.Complementnode.AcceptNode;
import fsexchange.ws.send.data.cooperation.permission.Complementnode.NotifyNode;
import fsexchange.ws.send.data.cooperation.permission.Material;
import fsexchange.ws.send.data.cooperation.permission.Node;
import fsexchange.ws.send.data.cooperation.permission.NodeDate;
import fsexchange.ws.send.data.cooperation.permission.Specialnode;
import fsexchange.ws.send.data.cooperation.permission.Specialnode.ApplyNode;
import fsexchange.ws.send.data.cooperation.permission.Specialnode.HandleNode;

public abstract class AbstractBusinessCollectTask implements Runnable {
	private static final Logger logger = Logger.getLogger(AbstractBusinessCollectTask.class);
	private static final String EXCHANGE_DATA = "/fsExchange/data";
	private static final String BASE_FLOW_DATA = "/base/core/flow/data";
	private static final String BASE_FLOWOPERATION_DATA = "/base/core/flowOperation/data";
	private static final String BASE_CORE_FN = "/base/core/logic/fn";

	public static enum ExTaskStatus {
		WAITING, SUCCESS, ERROR, SUSPENDED, IGNORE;
	}

	public AbstractBusinessCollectTask() {
		Config config = (Config) ModelUtils.getModelObjectByFullName("/fsExchange/config/ftp", Config.TYPE);
		this.ftpConnectTimeout = Integer.parseInt(config.getValue("connectTimeout"));
		this.ftpHost = config.getValue("host");
		this.ftpPassiveMode = "true".equals(config.getValue("passiveMode"));
		this.ftpPassword = config.getValue("passwd");
		this.ftpPort = Integer.parseInt(config.getValue("port"));
		this.ftpUser = config.getValue("user");

		config = (Config) ModelUtils.getModelObjectByFullName("/fsExchange/config/exchangeService", Config.TYPE);
		this.logDir = config.getValue("logDir");
		this.logFinishCooperationXml = "true".equals(config.getValue("logFinishCooperationXml"));
		this.batchTaskCount = Integer.parseInt(config.getValue("batchTaskCount"));

		config = (Config) ModelUtils.getModelObjectByFullName("/fsExchange/config/defaultDepartment", Config.TYPE);
		this.defaultDepartment = config.getValue();
		for (String name : config.getNames()) {
			this.departmentMap.put(name, config.getValue(name));
		}
	}

	/**
	 * 交换数据排序，正顺，空时间往后排
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	private class NodeDateCompare implements CompareCallBack<NodeDate> {
		public int compare(NodeDate a, NodeDate b) {
			java.util.Date d1 = a.getDate();
			java.util.Date d2 = b.getDate();
			if (d1 != null && d2 != null) {
				return d1.compareTo(d2);
			} else if (d1 == null && d2 == null) {
				return 0;
			} else if (d1 != null && d2 == null) {
				return -1;
			} else {
				return 1;
			}
		}
	}

	// 机构代码表
	protected Map<String, String> departmentMap = new HashMap<String, String>();
	protected String defaultDepartment;

	protected String ftpHost;
	protected int ftpPort;
	protected int ftpConnectTimeout;
	protected String ftpUser;
	protected String ftpPassword;
	protected boolean ftpPassiveMode = true;

	/**
	 * 交换日志相关
	 */
	protected boolean logFinishCooperationXml = true;
	/**
	 * 保存异常时XML内容，包括XML验证失败，XML提交返回0
	 * 
	 * @param now
	 * @param recNo
	 * @param context
	 * @param errorType
	 */
	protected String logDir;

	public void setLogDir(String logDir) {
		this.logDir = logDir;
	}

	/**
	 * 一次加载的待处理的任务数，防止挤压导致vm崩溃
	 */
	protected int batchTaskCount = 100;

	private UserService userService;
	private BusinessCollectService bcService;

	public void setBatchTaskCount(int value) {
		this.batchTaskCount = value;
	}

	public void setUserService(UserService service) {
		this.userService = service;
	}

	public void setBusinessCollectService(BusinessCollectService service) {
		this.bcService = service;
	}

	/**
	 * 加载交换任务,案卷+发生时间倒序
	 * 
	 * @return
	 */
	private Table loadExchangeTassk(int num, String bizRecId) {
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "select Ex_Task.* from Ex_Task Ex_Task where Ex_Task.fStatus='WAITING'";
		if (bizRecId != null) {
			sql += " Ex_Task.fBizRecId=:bizRecId";
			params.put("bizRecId", bizRecId);
		}
		sql += " order by Ex_Task.fCJSJ  LIMIT 0, " + num;
		Table t = KSQL.select(sql, params, ModelUtils.getModel(EXCHANGE_DATA), ModelUtils.getModel(BASE_CORE_FN));
		t.getMetaData().setKeyColumn("Ex_Task");
		t.getMetaData().setStoreByConcept("Ex_Task", true);
		return t;
	}

	/**
	 * 检查 是否存有交换记录，单无交换任务
	 */
	private void checkExchangeRecNoExTask() {
		Table t = KSQL.select("select r from Ex_Rec r where not exists(select t from Ex_Task t where t.fBizRecId=r)", null, ModelUtils.getModel(EXCHANGE_DATA), ModelUtils.getModel(BASE_CORE_FN));
		if (t.size() > 0) {
			Iterator<Row> itor = t.iterator();
			while (itor.hasNext()) {
				logger.warn("一体化交换案卷[" + itor.next().getString("r") + "]没有交换任务!!!");
			}
		}
	}

	/**
	 * 检查是否案卷已办结(b.fFinishKind is not null)但是交换记录未结束(r.fJHJD<>'5')
	 */
	private void checkExchangeRecFinishStatus() {
		KSQL.executeUpdate("insert into Ex_Task t (t,t.fBizRecId,t.fStatus,t.fCJSJ,t.fJHJD)"
				+ " (select :guid(),b,'WAITING',:currentDateTime(),'5' from B_BizRec b join Ex_Rec r on b=r where b.fFinishKind is not null and r.fJHJD<>'5')", null,
				ModelUtils.getModel(EXCHANGE_DATA), ModelUtils.getModel(BASE_CORE_FN));
	}

	/**
	 * 删除超期交换任务，交换记录已完成(终止)的交换任务
	 */
	private void deleteExpiredTask() {
		KSQL.executeUpdate(" DELETE FROM Ex_Task t WHERE exists(select r from Ex_Rec r where r.fJHJD='5')", null, ModelUtils.getModel(EXCHANGE_DATA), ModelUtils.getModel(BASE_CORE_FN));
	}

	private Table getExchangeRec(String bizRecId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizRecId", bizRecId);
		Table t = KSQL.select("select Ex_Rec.* from Ex_Rec Ex_Rec where Ex_Rec=:bizRecId", params, ModelUtils.getModel(EXCHANGE_DATA), ModelUtils.getModel(BASE_CORE_FN));
		t.getMetaData().setKeyColumn("Ex_Rec");
		t.getMetaData().setStoreByConcept("Ex_Rec", true);
		return t;
	}

	/**
	 * 获得申请人信息
	 * 
	 * @param bizRecId
	 * @return
	 */
	protected Row getApplyRec(String bizRecId) {
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> variables = new HashMap<String, Object>();
		params.put("limit", 1);
		params.put("offset", 0);
		params.put("columns", null);
		params.put("variables", variables);
		params.put("filter", "Ex_View_Apply=:bizRecId");
		variables.put("bizRecId", bizRecId);
		Table tab = (Table) ActionUtils.invokeAction("/fsExchange/process/fsExchange/fsExchangeProcess", "ythExchange", "queryEx_View_ApplyAction", "", "", params);
		return tab.iterator().next();
	}

	/**
	 * 查询受理视图，可以修改KSQL满足概念定义的字段，或者建立视图
	 * 
	 * @param bizRecId
	 * @return
	 */
	protected Row getShouLiRec(String bizRecId) {
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> variables = new HashMap<String, Object>();
		params.put("limit", 1);
		params.put("offset", 0);
		params.put("columns", null);
		params.put("variables", variables);
		params.put("filter", "Ex_View_ShouLi=:bizRecId");
		variables.put("bizRecId", bizRecId);
		Table tab = (Table) ActionUtils.invokeAction("/fsExchange/process/fsExchange/fsExchangeProcess", "ythExchange", "queryEx_View_ShouLiAction", "", "", params);
		return tab.iterator().next();
	}

	protected Table queryYewuShenPiTable(String sphj, String bizRecId) {
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> variables = new HashMap<String, Object>();
		params.put("limit", 1);
		params.put("offset", 0);
		params.put("columns", null);
		params.put("variables", variables);
		params.put("filter", "Ex_View_SPB.fBizRecId=:bizRecId and Ex_View_SPB.fSPHJ=:sphj");
		variables.put("bizRecId", bizRecId);
		variables.put("sphj", sphj);
		Table tab = (Table) ActionUtils.invokeAction("/fsExchange/process/fsExchange/fsExchangeProcess", "ythExchange", "queryEx_View_SPBAction", "", "", params);
		return tab;
	}

	/**
	 * 此业务是否暂停交换
	 * 
	 * @param process
	 * @return
	 */
	protected boolean isSuspendExchange(String process) {
		return false;
	}

	/**
	 * 此业务是否交换审批事项，即分局审批事项是否转为市局事项，默认为否
	 * 
	 * @return
	 */
	protected boolean isSwitchShenPiSX(String process, String spsxdxbh) {
		return false;
	}

	protected boolean isEnableFtp() {
		return true;
	}

	/**
	 * 添加受理节点数据
	 * 
	 * @param shouli
	 * @param department
	 * @param nodes
	 * @throws UnsupportedEncodingException
	 */
	private void addShouLiNode(Row shouli, String department, List<XmlOut> nodes) throws UnsupportedEncodingException {
		Node n = new Node();
		n.setNodeType("0000");
		n.setNodeid("0000");
		n.setNodename("受理");
		n.setDepartment(department);
		n.setDate(shouli.getDateTime("fSLSJ"));
		n.setHandlerdate(TimeUtil.dateTimeToString(shouli.getDateTime("fSLSJ"), "yyyy-MM-dd'T'HH:mm:ss"));
		n.setHandleridea(Service.cutString(shouli.getValue("fBZ") == null ? "" : shouli.getValue("fBZ").toString(), 200, "gbk"));
		n.setNodeactor(shouli.getString("fSLR"));
		n.setNodeactorgh(shouli.getString("fSLRGH"));
		n.setNodeactorzwdm("9999");
		n.setNodeactorzwmc("其他");
		nodes.add(n);
	}

	/**
	 * 取最后一次承办信息
	 * 
	 * @param bizRecId
	 * @param department
	 * @param nodes
	 * @throws UnsupportedEncodingException
	 */
	private void addChengBanNode(String bizRecId, String department, List<XmlOut> nodes) throws UnsupportedEncodingException {
		List<Node> tmpList = new ArrayList<Node>();
		Table spb = queryYewuShenPiTable("cb", bizRecId);
		Iterator<Row> itor = spb.iterator();
		while (itor.hasNext()) {
			Row exNode = itor.next();
			Node n = new Node();
			n.setNodeType("0001");
			n.setNodeid("0001");
			n.setNodename("承办");
			n.setDepartment(department);
			n.setDate(exNode.getDateTime("fSPSJ"));
			n.setHandlerdate(TimeUtil.dateTimeToString(n.getDate(), "yyyy-MM-dd'T'HH:mm:ss"));
			n.setNodeactor(exNode.getString("fSPR"));
			n.setHandleridea(Service.cutString(exNode.getValue("fSPYJ") == null ? "同意" : exNode.getValue("fSPYJ").toString(), 200, "gbk"));
			n.setNodeactorzwdm("9999");
			n.setNodeactorzwmc("其他");
			tmpList.add(n);

		}
		if (tmpList.size() > 0) {
			Node[] arrs = new Node[tmpList.size()];
			tmpList.toArray(arrs);
			SortUtil.sort(arrs, new NodeDateCompare());
			nodes.add(arrs[0]);
		}
	}

	private void addShenHeZhunNode(String bizRecId, String department, List<XmlOut> nodes) throws UnsupportedEncodingException {
		List<Node> tmpList = new ArrayList<Node>();
		Table spb = queryYewuShenPiTable("sh", bizRecId);
		Iterator<Row> itor = spb.iterator();
		while (itor.hasNext()) {
			Row exNode = itor.next();
			Node n = new Node();
			n.setNodeType("0002");
			n.setNodeid("0002");
			n.setNodename("审核");
			n.setDepartment(department);
			n.setDate(exNode.getDateTime("fSPSJ"));
			n.setHandlerdate(TimeUtil.dateTimeToString(n.getDate(), "yyyy-MM-dd'T'HH:mm:ss"));
			n.setNodeactor(exNode.getString("fSPR"));
			n.setHandleridea(Service.cutString(exNode.getValue("fSPYJ") == null ? "同意" : exNode.getValue("fSPYJ").toString(), 200, "gbk"));
			n.setNodeactorzwdm("9999");
			n.setNodeactorzwmc("其他");
			tmpList.add(n);

		}
		if (tmpList.size() > 0) {
			Node[] arrs = new Node[tmpList.size()];
			tmpList.toArray(arrs);
			SortUtil.sort(arrs, new NodeDateCompare());
			nodes.add(arrs[0]);
		}
	}

	private void addPiZhunNode(String bizRecId, String department, List<XmlOut> nodes) throws UnsupportedEncodingException {
		List<Node> tmpList = new ArrayList<Node>();
		Table spb = queryYewuShenPiTable("pz", bizRecId);
		Iterator<Row> itor = spb.iterator();
		while (itor.hasNext()) {
			Row exNode = itor.next();
			Node n = new Node();
			n.setNodeType("0003");
			n.setNodeid("0003");
			n.setNodename("批准");
			n.setDepartment(department);
			n.setDate(exNode.getDateTime("fSPSJ"));
			n.setHandlerdate(TimeUtil.dateTimeToString(n.getDate(), "yyyy-MM-dd'T'HH:mm:ss"));
			n.setNodeactor(exNode.getString("fSPR"));
			n.setHandleridea(Service.cutString(exNode.getValue("fSPYJ") == null ? "同意" : exNode.getValue("fSPYJ").toString(), 200, "gbk"));
			n.setNodeactorzwdm("9999");
			n.setNodeactorzwmc("其他");
			tmpList.add(n);

		}
		if (tmpList.size() > 0) {
			Node[] arrs = new Node[tmpList.size()];
			tmpList.toArray(arrs);
			SortUtil.sort(arrs, new NodeDateCompare());
			nodes.add(arrs[0]);
		}
	}

	private Node getBanJieNode(String bizRecId, String department) throws UnsupportedEncodingException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizRecId", bizRecId);
		Table t = KSQL.select("select b.* from B_BJJLB b where b=:bizRecId", params, ModelUtils.getModel(BASE_FLOWOPERATION_DATA), ModelUtils.getModel(BASE_CORE_FN));
		if (t.size() == 0)
			return null;
		// ![CDATA[办结结果代码：0–出证办结（正常产生证照、批文的办结） 1–退回办结（退回或驳回申请的办结）
		// 2–作废办结（指业务处理上无效的纪录） 3–删除办结（指录入错误、操作错误等技术上的无效纪录）
		// 4–转报办结（指转报其他单位或上级单位的办结情况） 5–补交逾期办结（指出现补交告知时，通知之后，申请人长期不来补交材料的办结）
		// 6–其他办结（除以上所述情况外的办结）（补充）
		Row bizRec = t.iterator().next();
		Node n = new Node();
		n.setNodeType("0004");
		n.setNodeid("0004");
		n.setNodename("办结");
		n.setDepartment(department);
		n.setDate(bizRec.getDateTime("fBJSJ"));
		n.setNodeactor(bizRec.getString("fBJRXM"));
		n.setHandleridea(Service.cutString(bizRec.getString("fBJJGMS"), 200, "gbk"));
		n.setSubType(bizRec.getString("fBJJGDM"));
		n.setHandlerdate(TimeUtil.dateTimeToString(n.getDate(), "yyyy-MM-dd'T'HH:mm:ss"));
		n.setNodeactorzwdm("9999");
		n.setNodeactorzwmc("其他");
		return n;
	}

	/**
	 * 添加所有挂起记录，并返回当前挂起类型，未挂起返回null。注意过滤掉办结后产生的挂起记录
	 * 
	 * @param bizRecId
	 * @param department
	 * @param banjieNode
	 * @param nodes
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private String addGuaQiNode(String bizRecId, String department, Node banjieNode, List<XmlOut> nodes) throws UnsupportedEncodingException {
		String result = null;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fBizRecId", bizRecId);
		Table gqjl = KSQL.select("select B_AJGQJLB.* from B_AJGQJLB B_AJGQJLB where B_AJGQJLB.fBizRecId=:fBizRecId order by B_AJGQJLB.fFQSJ", params, ModelUtils.getModel(BASE_FLOWOPERATION_DATA),
				ModelUtils.getModel(BASE_CORE_FN));
		Iterator<Row> itorGQ = gqjl.iterator();
		while (itorGQ.hasNext()) {
			Row gq = itorGQ.next();
			if (banjieNode != null && banjieNode.getDate() != null && gq.getDateTime("fFQSJ").after(banjieNode.getDate())) {
				continue;
			}

			if ((result == null) && (gq.getDateTime("fJSSJ") == null)) {
				result = gq.getString("fGQLX");
			}
			if (SuspendKind.skApprize.name().equals(gq.getString("fGQLX"))) {
				params.clear();
				params.put("gqjl", gq.getString("B_AJGQJLB.fFQSJ"));
				Table t = KSQL.select("select a.* from B_BZCLQD a join B_BZGZ b on b=a.fBZGZ where b.fAJGQJL=:gqjl", params, ModelUtils.getModel(BASE_FLOWOPERATION_DATA),
						ModelUtils.getModel(BASE_CORE_FN));
				Iterator<Row> i = t.iterator();
				StringBuffer sb1 = new StringBuffer();
				StringBuffer sb2 = new StringBuffer();
				while (i.hasNext()) {
					Row r = i.next();
					sb1.append(r.getString("fCLMS")).append("；");
					if ("已确认".equals(r.getString("fCLQR"))) {
						sb2.append(r.getString("fCLMS")).append("；");
					}
				}
				Complementnode node = new Complementnode();
				node.setDate(gq.getDateTime("fFQSJ"));
				NotifyNode notifynode = node.getNotifynode();
				notifynode.setComplementdate(TimeUtil.dateTimeToString(gq.getDateTime("fFQSJ"), "yyyy-MM-dd'T'HH:mm:ss"));
				notifynode.setComplementidea(Service.cutString(gq.getString("fGQYY"), 100, "gbk"));
				notifynode.setComplementlist(sb1.toString());
				notifynode.setNodeactor(gq.getString("fFQRXM"));
				notifynode.setDepartment(department);
				if (gq.getDateTime("fJSSJ") != null) {
					AcceptNode acceptnode = node.new AcceptNode();
					node.setAcceptnode(acceptnode);
					acceptnode.setDepartment(department);
					acceptnode.setHandler(gq.getString("fJSRXM"));
					acceptnode.setHandleraddr("无");
					acceptnode.setHandlerdate(TimeUtil.dateTimeToString(gq.getDateTime("fJSSJ"), "yyyy-MM-dd'T'HH:mm:ss"));
					acceptnode.setHandlerlist(sb2.toString());
				}
				nodes.add(node);
			} else if (SuspendKind.skSpecialProcedure.name().equals(gq.getString("fGQLX"))) {
				Specialnode node = new Specialnode();
				node.setDate(gq.getDateTime("fFQSJ"));
				ApplyNode applyNode = node.getApplynode();
				applyNode.setDepartment(department);
				applyNode.setSpecialidea(Service.cutString(gq.getString("fGQYY"), 50, "gbk"));
				applyNode.setSpecialstartdate(TimeUtil.dateTimeToString(gq.getDateTime("fFQSJ"), "yyyy-MM-dd'T'HH:mm:ss"));
				applyNode.setSpecialunit("G");

				params.clear();
				params.put("gqjl", gq.getString("B_AJGQJLB.fFQSJ"));
				Table t = KSQL.select("select a.* from B_TBCX a where a.fAJGQJL=:gqjl", params, ModelUtils.getModel(BASE_FLOWOPERATION_DATA), ModelUtils.getModel(BASE_CORE_FN));

				Row tbcx = t.iterator().next();
				applyNode.setSpecialcontent(Service.cutString(tbcx.getString("fSQNR"), 100, "gbk"));
				applyNode.setSpeciallimit(tbcx.getValue("fTBCXSX") == null ? "" : gq.getValue("fTBCXSX").toString());
				applyNode.setSpecialtype("延长审批".equals(tbcx.getString("fTBCXZL")) ? "A" : "B");
				applyNode.setSpecialuser(tbcx.getString("fTBCXPZR"));
				applyNode.setSpecialuserphone("无");
				applyNode.setSpecialusertel("无");

				if (gq.getDateTime("fJSSJ") != null) {
					HandleNode handleNode = node.new HandleNode();
					node.setHandlenode(handleNode);
					handleNode.setDepartment(department);
					handleNode.setSpecialenddate(TimeUtil.dateTimeToString(gq.getDateTime("fJSSJ"), "yyyy-MM-dd'T'HH:mm:ss"));
					handleNode.setSpecialpay(tbcx.getValue("fTBCXSFJE") == null ? "" : tbcx.getValue("fTBCXSFJE").toString());
					handleNode.setSpecialresult(tbcx.getString("fTBCXJG"));
					handleNode.setSpecialresultdate(handleNode.getSpecialenddate());
				}
				nodes.add(node);
			}
		}
		return result;
	}

	private boolean uploadBizFile(BTFtpClient ftp, JSONObject doc, String ftpPath) {
		String docUrl = DocUtils.getDocUrl(doc.getString("docPath"), "/repository/file/download/" + doc.getString("fileID") + "/last/content", false);
		HttpClient client = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
		GetMethod method = new GetMethod(docUrl);
		InputStream in = null;
		try {
			client.executeMethod(method);
			in = method.getResponseBodyAsStream();
			ftp.uploadFile(in, ftpPath);
		} catch (Exception e) {
			logger.warn("上传" + doc.getString("docName") + "失败，" + e.getMessage(), e);
			return false;
		} finally {
			method.releaseConnection();
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	/**
	 * 添加要件附件
	 * 
	 * @param list
	 * @param bizRec
	 * @param department
	 */
	private void addImpDocPage(BTFtpClient ftp, List<XmlOut> nodes, String bizRecId, String flgdgxd, String department) {
		try {
			Date now = CommonUtils.getCurrentDate();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("bizRecId", bizRecId);
			Table uploadLogs = KSQL.select("select Ex_MaterialLog.* from Ex_MaterialLog Ex_MaterialLog where Ex_MaterialLog.fBizRecId=:bizRecId", params, ModelUtils.getModel(EXCHANGE_DATA),
					ModelUtils.getModel(BASE_CORE_FN));
			Table materialTable = KSQL.select("select B_Material.* from B_Material B_Material where B_Material.fBizRecId=:bizRecId", params, ModelUtils.getModel("/base/core/material/data"),
					ModelUtils.getModel(BASE_CORE_FN));

			if (materialTable.size() == 0) {
				return;
			}
			Iterator<Row> itor = materialTable.iterator();
			while (itor.hasNext()) {
				Row r = itor.next();
				Row log = uploadLogs.getRow(r.getString("B_Material"));
				if (log == null) {
					log = uploadLogs.appendRow(r.getString("B_Material"));
					log.setString("fJHID", Service.createNewGuid("FSGT", 30));
					log.setText("fUploadFiles", "{}");
				}
				String jhID = log.getString("fJHID");
				JSONObject logFiles = new JSONObject();
				try {
					logFiles = JSON.parseObject(log.getText("fUploadFiles"));
				} catch (Exception e) {
				}
				JSONArray materialFiels = new JSONArray();
				try {
					materialFiels = JSON.parseArray(r.getString("fDocIds"));
				} catch (Exception e) {
				}
				// 材料节点
				Material clNode = new Material();
				nodes.add(clNode);
				clNode.setId(jhID);// 材料实例ID。申报时产生的材料编号。
				clNode.setCopynum(r.getValue("fMtNums") == null ? "0" : r.getValue("fMtNums").toString());
				clNode.setId(jhID);// 材料实例ID。申报时产生的材料编号。
				clNode.setMlid(jhID);// 材料ID。综合管理平台配置材料时产生
				clNode.setMlname(r.getValue("fMaterialName") == null ? "默认材料" : r.getValue("fMaterialName").toString());
				clNode.setIsneed("必要材料".equals(r.getValue("fMaterialType")) ? "1" : "0");
				clNode.setMtype("5");
				clNode.setSelecttype("5");
				clNode.setStatus("1");// 材料状态(0未提交1提交2补交)

				// 材料下的文件节点
				int cnt = 0;
				for (int i = 0; i < materialFiels.size(); i++) {
					JSONObject f = materialFiels.getJSONObject(i);
					String fileID = f.getString("fileID");
					if (fileID != null && !fileID.trim().equals("")) {
						// 上传附件
						String fileJHID = null, extName = f.getString("docName");
						if (extName.lastIndexOf(".") > 0) {
							extName = extName.substring(extName.lastIndexOf("."));
						}
						// [行政区划代码]/[部门组织机构代码]/[四位年份]/[两位月份]/fileID.xxx
						String ftpFile = "/" + flgdgxd + "/" + department + "/" + TimeUtil.yearOf(now) + "/" + TimeUtil.monthOf(now) + "/" + fileJHID + extName;
						if (logFiles.containsKey(f.getString("fileID"))) {
							fileJHID = logFiles.getString(f.getString("fileID"));
						} else {
							fileJHID = Service.createNewGuid("FSGT", 30);
							boolean b = ftp == null || this.uploadBizFile(ftp, f, ftpFile);
							if (b) {
								logFiles.put(fileID, fileJHID);
							} else {
								logger.warn("上传附件失败:" + r.getValue("fMaterialName") + "-" + f.getString("docName"));
								continue;
							}
						}
						cnt++;
						Material fileNode = new Material();
						nodes.add(fileNode);
						//下面两句编译不过去
						//m.setAdjustment(adjustment);// 材料状态为补交时，填写补交意见（补充）
						//m.setBaseinfo(baseinfo);// 审批申报表单，当baseinfo节点不为0时，dataxml节点必填
						fileNode.setCopynum(clNode.getCopynum());
						fileNode.setOrinum(clNode.getOrinum());
						fileNode.setId(fileJHID);// //材料实例ID。申报时产生的材料编号。
						fileNode.setPid(clNode.getId());// 父材料ID。综合管理平台配置时产生。
						fileNode.setMlid(fileJHID);// //材料ID。综合管理平台配置材料时产生
						fileNode.setMlname(f.getString("docName"));
						fileNode.setIsneed(clNode.getIsneed());
						fileNode.setMtype("5");
						fileNode.setSelecttype("5");
						fileNode.setStatus("1");// 材料状态(0未提交1提交2补交)

						fileNode.setFid(fileJHID);
						fileNode.setFpath(ftpFile);
						fileNode.setFname(f.getString("docName"));
					}
				}
				log.setText("fUploadFiles", logFiles.toJSONString());
				clNode.setOrinum(new Integer(cnt).toString());
			}
			uploadLogs.save(EXCHANGE_DATA);
			BizDataUtils.mergeStateAfterSave(uploadLogs);
		} catch (Exception e) {
			logger.error("上传审批材料失败", e);
		}

	}

	/**
	 * 添加不予受理
	 * 
	 * @param bizRec
	 * @param department
	 * @param nodes
	 * @throws UnsupportedEncodingException
	 */
	private Node addBYSLNode(String department, List<XmlOut> nodes, Row exRec) throws UnsupportedEncodingException {
		if (!exRec.getString("fJHJD").equals("0"))
			return null;
		Node n = new Node();
		n.setNodeType("0004");
		n.setNodeid("0004");
		n.setNodename("办结");
		n.setDepartment(department);
		n.setDate(exRec.getDateTime("fBYSLSJ"));
		n.setNodeactor("窗口");
		n.setHandleridea(Service.cutString(exRec.getValue("fBYSLYY") == null ? "" : exRec.getValue("fBYSLYY").toString(), 200, "gbk"));
		n.setHandlerdate(TimeUtil.dateTimeToString(exRec.getDateTime("fBYSLSJ"), "yyyy-MM-dd'T'HH:mm:ss"));
		n.setNodeactorzwdm("9999");
		n.setNodeactorzwmc("其他");
		n.setSubType("1");// 1–退回办结（退回或驳回申请的办结）
		nodes.add(n);
		return n;
	}

	private Row getBizRec(String bizRecId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", bizRecId);
		Table t = KSQL.select("select B.*,T.sProcess as sProcess from B_BizRec B join SA_Task T on T=B.fFlowId and T.sKindID='tkProcessInstance' where B=:id", params,
				ModelUtils.getModel(BASE_FLOW_DATA), ModelUtils.getModel(BASE_CORE_FN));
		if (t.size() == 0)
			return null;
		return t.iterator().next();
	};

	/**
	 * 设置任务状态
	 * 
	 * @param r
	 * @param status
	 */
	void setExTaskStatus(Row exTask, ExTaskStatus status, String message) {
		exTask.setString("fStatus", status.name());
		if (message != null)
			exTask.setText("fCause", message);
	}

	public void run() {
		BTFtpClient ftp = isEnableFtp() ? getFtpClient() : null;
		checkExchangeRecNoExTask();
		checkExchangeRecFinishStatus();
		deleteExpiredTask();
		int n = 0;

		while (true) {
			Table data = loadExchangeTassk(batchTaskCount, null);
			if (data.size() == 0)
				return;
			Iterator<Row> itor = data.iterator();
			while (itor.hasNext()) {
				logger.debug("开始执行交换任务[" + (++n) + "]");
				Row exTask = itor.next();
				processSingleExTask(data, exTask, ftp);
			}
		}
	}

	public void run(String bizRecId) {
		BTFtpClient ftp = isEnableFtp() ? getFtpClient() : null;
		while (true) {
			Table data = loadExchangeTassk(batchTaskCount, bizRecId);
			if (data.size() == 0)
				return;
			Iterator<Row> itor = data.iterator();
			while (itor.hasNext()) {
				Row exTask = itor.next();
				processSingleExTask(data, exTask, ftp);
			}
		}
	}

	/**
	 * 处理单条交换任务
	 * 
	 * @param data
	 * @param exTask
	 * @param ftp
	 */
	private void processSingleExTask(Table data, Row exTask, BTFtpClient ftp) {
		Transaction t = ContextHelper.getTransaction();
		boolean rollback = false;
		try {
			t.begin();
			rollback = true;
			sendData(exTask, ftp);
			data.save(EXCHANGE_DATA);
			t.commit();
			rollback = false;
			// 合并保存状态
			BizDataUtils.mergeStateAfterSave(data);
		} catch (Exception e) {
			logger.error("保存交换控制数据异常," + e.getMessage(), e);
			if (t != null && rollback) {
				try {
					t.rollback();
				} catch (Exception ee) {
				}
			}
		}
	}

	private boolean isFinished(String jhjd) {
		return "5".equals(jhjd);
	}

	private void registeUser(Row exRec, String recNo, String sqrxm, String sqrsfzh) throws Exception {

		String loginName = Service.cutString(recNo, 100, "gbk");
		String password = null;
		String userName = Service.cutString(sqrxm, 50, "gbk");
		if (userName == null) {
			userName = "没有";
		}

		if (loginName.length() >= 4) {
			password = loginName.substring(loginName.length() - 4);
		} else {
			password = "888888";
		}
		String userPid = Service.cutString(sqrsfzh, 18, "gbk");
		if (userPid == null) {
			userPid = "无";
		}
		int t = 0;
		int loginNameLen = loginName.length();
		Random r = new Random();
		while (true) {
			t++;
			if (!userService.checkName(loginName)) {
				// 用户不存在
				String uid = userService.registerUser(userPid, loginName, userName, password, null);
				if (uid != null) {
					exRec.setString("ythAccount", loginName);
					exRec.setString("ythPasswd", password);
					exRec.setString("ythUserID", uid);
					logger.debug("registerUser(" + userPid + "," + loginName + "," + userName + "," + password + ") 成功");
					break;
				}
			} else {
				// 用户存在尝试拿回用户账号(用户注册成功但是保存失败)
				String uid = userService.getIdByUserLogon(loginName, password);
				if ("0".equals(uid)) {
					logger.warn("取回账号[" + loginName + "]ID失败，密码不匹配");
				} else {
					exRec.setString("ythAccount", loginName);
					exRec.setString("ythUserID", uid);
					exRec.setString("ythPasswd", password);
					logger.debug("取回账号[" + loginName + "]ID成功");
					break;
				}
			}
			if (t >= 10) {
				logger.warn("[" + recNo + "]尝试10次随机用户名注册失败");
				break;
			}
			loginName = loginName.substring(0, loginNameLen) + "-" + r.nextInt(100);
		}
	}

	private static final Set<String> VALID_JHJD = new HashSet<String>(Arrays.asList(new String[] { "0", "1", "2", "3", "4", "5" }));

	private void sendData(Row exTask, BTFtpClient ftp) {
		Table exRecTable = null;
		try {
			String jhjd = exTask.getString("fJHJD");
			if (!VALID_JHJD.contains(jhjd)) {
				this.setExTaskStatus(exTask, ExTaskStatus.ERROR, "交换任务的交换阶段错误！");
				return;
			}
			Timestamp now = CommonUtils.getCurrentDateTime();
			exTask.setDateTime("fExecuteTime", now);

			String bizRecId = exTask.getString("fBizRecId");
			exRecTable = this.getExchangeRec(bizRecId);
			Row exRec = exRecTable.size() > 0 ? exRecTable.iterator().next() : null;
			if (exRec == null) {
				this.setExTaskStatus(exTask, ExTaskStatus.ERROR, "无交换控制表");
				return;
			}

			if (exRec.getDateTime("fExTaskTime") != null && exRec.getDateTime("fExTaskTime").compareTo(exTask.getDateTime("fCJSJ")) >= 0) {
				// 交换任务已处理
				this.setExTaskStatus(exTask, ExTaskStatus.IGNORE, exRec.getString("fExTaskId"));
				return;
			}

			exRec.setDateTime("fExTaskTime", exTask.getDateTime("fCJSJ"));

			boolean noAccept = jhjd.equals("0");

			Row bizRec = null, shouLi = null, apply = null;
			// 机构代码
			String recNo = null, department = null, spsxdxbh = null;
			if (!noAccept) {
				// 案卷受理后
				bizRec = this.getBizRec(bizRecId);
				if (bizRec == null) {
					this.setExTaskStatus(exTask, ExTaskStatus.ERROR, "无案卷数据");
					return;
				}

				// 未发生过交换 fLastExTime == null，检查交换是否暂停。已交换过的，必须强制交换，否则监察缺数据
				if (this.isSuspendExchange(bizRec.getString("sProcess")) && exRec.getDateTime("fLastExTime") == null) {
					this.setExTaskStatus(exTask, ExTaskStatus.SUSPENDED, null);
					return;
				}

				shouLi = this.getShouLiRec(bizRecId);
				if (shouLi == null) {
					this.setExTaskStatus(exTask, ExTaskStatus.ERROR, "无受理信息");
					return;
				}

				recNo = shouLi.getString("fRecNo");

				apply = this.getApplyRec(bizRecId);
				if (apply == null) {
					this.setExTaskStatus(exTask, ExTaskStatus.ERROR, "无申请人信息");
					return;
				}

				if (this.departmentMap != null) {
					// 法律规定管辖地
					department = this.departmentMap.get(shouLi.getString("fFLGDGXD"));
				}
				if (department == null || "".equals(department.trim())) {
					department = this.defaultDepartment;
				}

				// 审批事项大项编号
				spsxdxbh = shouLi.getString("fSPSXDXBH");
				if (spsxdxbh == null || "".equals(spsxdxbh.trim())) {
					this.setExTaskStatus(exTask, ExTaskStatus.ERROR, "受理表[" + shouLi.getString("fRecNo") + "]审批事项大项不存在");
					return;
				}

				if (this.isSwitchShenPiSX(bizRec.getString("sProcess"), spsxdxbh)) {
					spsxdxbh = "FS" + spsxdxbh.substring(2);
				}

				// 审批事项子项编号
				if (shouLi.getString("fSPSXZXBH") != null && !("000".equals(shouLi.getString("fSPSXZXBH").trim()))) {
					// 如果含审批子项，且不是000开头，审批事项 = 大项 + "-" + 子项
					spsxdxbh = spsxdxbh + "-" + shouLi.getString("fSPSXZXBH");
				}
			} else {
				// 案卷受理前
				if (this.departmentMap != null) {
					// 法律规定管辖地
					department = this.departmentMap.get(exRec.getString("fFLGDGXD"));
				}
				if (department == null || "".equals(department.trim())) {
					department = this.defaultDepartment;
				}

				// 审批事项大项编号
				spsxdxbh = exRec.getString("fSPSXDXBH");
				if (spsxdxbh == null || "".equals(spsxdxbh.trim())) {
					this.setExTaskStatus(exTask, ExTaskStatus.ERROR, "交换控制表[" + exRec.getString("fRecNo") + "]审批事项大项不存在");
					return;
				}

				if (this.isSwitchShenPiSX(null, spsxdxbh)) {
					spsxdxbh = "FS" + spsxdxbh.substring(2);
				}

				// 审批事项子项编号
				if (exRec.getString("fSPSXZXBH") != null && !("000".equals(exRec.getString("fSPSXZXBH").trim()))) {
					// 如果含审批子项，且不是000开头，审批事项 = 大项 + "-" + 子项
					spsxdxbh = spsxdxbh + "-" + exRec.getString("fSPSXZXBH");
				}

				recNo = exRec.getString("fRecNo");
			}
			// 如果交换已经结束
			if (this.isFinished(jhjd)) {
				this.setExTaskStatus(exTask, ExTaskStatus.SUCCESS, null);
				return;
			}

			if (recNo == null) {
				this.setExTaskStatus(exTask, ExTaskStatus.ERROR, "案卷[" + bizRecId + "]" + (noAccept ? "交换控制表" : "受理表") + "的受理编号不存在");
				return;
			}

			// 补充必要字段
			if (exRec.getString("ythbsid") == null) {
				exRec.setString("ythbsid", Service.createNewGuid("FSGT", 30));
			}
			// 设置关联的交换任务GUID
			exRec.setString("fExTaskId", exTask.getString("fGuid"));

			String sqrxm = noAccept ? exRec.getString("fSQRXM") : shouLi.getString("fSQRXM");
			String sqrsfzh = noAccept ? exRec.getString("fSQRSFZH") : shouLi.getString("fSQRSFZH");
			if (exRec.getString("ythUserID") == null) {
				try {
					this.registeUser(exRec, recNo, sqrxm, sqrsfzh);
					if (exRec.getString("ythUserID") == null) {
						setExTaskStatus(exTask, ExTaskStatus.ERROR, "注册用户失败,请联系一体化平台");
						return;
					}
				} catch (Exception e) {
					this.setExTaskStatus(exTask, ExTaskStatus.ERROR, "注册用户失败:" + e.getMessage());
					return;
				}
			}

			List<XmlOut> nodes = new ArrayList<XmlOut>();
			Node banjieNode = null;
			Node byslNode = null;
			String guaqiKind = null;
			if (noAccept) {
				// 添加不予受理
				byslNode = addBYSLNode(department, nodes, exRec);
			} else {
				// 添加审批过程
				addShouLiNode(shouLi, department, nodes);
				addChengBanNode(bizRecId, department, nodes);
				addShenHeZhunNode(bizRecId, department, nodes);
				addPiZhunNode(bizRecId, department, nodes);
				banjieNode = getBanJieNode(bizRecId, department);
				guaqiKind = addGuaQiNode(bizRecId, department, banjieNode, nodes);
			}

			Cooperation coop = new Cooperation();
			Application app = coop.getApplication();
			String srqdz = noAccept ? exRec.getString("fSQRDZ") : apply.getString("fSQRDZ");
			app.setAddress(srqdz);
			Date sqsj = noAccept ? exRec.getDateTime("fSQSJ") : apply.getDateTime("fSQSJ");
			app.setAppdate(TimeUtil.dateTimeToString(sqsj, "yyyy-MM-dd'T'HH:mm:ss"));
			app.setAppid(exRec.getString("ythUserID"));// 申请人信息ID（申请人在网上审批大厅注册的用户ID）C10-50

			app.setAppname(Service.cutString(sqrxm, 50, "gbk"));
			app.setCardid(sqrsfzh);
			String sqdw = noAccept ? exRec.getString("fSQDW") : apply.getString("fSQDW");
			app.setApporg(sqdw);
			String sqryx = noAccept ? exRec.getString("fSQRYX") : apply.getString("fSQRYX");
			app.setEmail(sqryx);
			// edit by 李泽华2013.8.8 id 统一改成收件编号
			app.setId(recNo); // bsnum 收件编号
			// 申请人ID（在网上审批大厅自动生成）C10-30
			// app.setId(exchangeRec.getUserId());//
			String sqrsj = noAccept ? exRec.getString("fSQRSJ") : apply.getString("fSQRSJ");
			app.setMobilephone(sqrsj);
			// app.setPassword(password);
			String sqrdh = noAccept ? exRec.getString("fSQRDH") : apply.getString("fSQRDH");
			app.setPhone(sqrdh);
			app.setUsertype("1");

			Permission perm = coop.getPermission();
			perm.setBsid(exRec.getString("ythbsid"));// 业务ID（一体化申报时产生的主键）C8-C30
			perm.setBsnum(recNo);// 业务流水号（一体化申报时提供的业务流水号，供申请人查询用）

			// 所有节点时间排序(不包含办结节点)

			NodeDate[] allNodes = new NodeDate[nodes.size()];
			nodes.toArray(allNodes);
			if (allNodes.length > 1) {
				SortUtil.sort(allNodes, new NodeDateCompare());
			} else if (allNodes.length == 0 && banjieNode == null) {
				setExTaskStatus(exTask, ExTaskStatus.SUCCESS, null);
				logger.debug("[" + recNo + "]无环节数据");
			}

			// 空日期修正：allNodes 时间正序，空时间在后面 。按顺序取出最大时间
			// 修正所有处理人、处理意见
			// 添加到Permission.nodes
			Date maxNotNullDate = null;
			String lastNodeactor = noAccept ? null : shouLi.getString("fSLR");
			for (int i = 0; i < allNodes.length; i++) {
				Object o = allNodes[i];
				if (o instanceof Node) {
					Node node = (Node) o;
					if (node.getDate() != null) {
						maxNotNullDate = node.getDate();
					}
					if (node.getDate() == null && maxNotNullDate != null) {
						node.setHandlerdate(TimeUtil.dateTimeToString(maxNotNullDate == null ? now : maxNotNullDate, "yyyy-MM-dd'T'HH:mm:ss"));
					}
					if (node.getNodeactor() != null) {
						lastNodeactor = node.getNodeactor();
					} else {
						node.setNodeactor(lastNodeactor);
					}
					if (node.getHandleridea() == null) {
						node.setHandleridea("同意");
					}
				}

				if (o instanceof XmlOut) {
					perm.getNodes().add((XmlOut) o);
				}
			}

			// 添加办结节点，并设置办结类型
			if (banjieNode != null) {
				if (banjieNode.getDate() == null) {
					banjieNode.setHandlerdate(TimeUtil.dateTimeToString(maxNotNullDate == null ? now : maxNotNullDate, "yyyy-MM-dd'T'HH:mm:ss"));
				}
				if (banjieNode.getNodeactor() == null) {
					banjieNode.setNodeactor(lastNodeactor);
				}
				if (banjieNode.getHandleridea() == null) {
					banjieNode.setHandleridea("同意");
				}
				perm.getNodes().add((XmlOut) banjieNode);
				perm.setDealresults(banjieNode.getSubType());
			}
			perm.setDepartment(department);
			String flgdgxd = noAccept ? exRec.getString("fFLGDGXD") : shouLi.getString("fFLGDGXD");
			perm.setFlgdgxd(flgdgxd);
			perm.setFsywgxd(noAccept ? exRec.getString("fFSYWGXD") : shouLi.getString("fFSYWGXD"));
			perm.setHandler("1");// 是否一体化平台处理（0为一体化系统，1为否）
			perm.setHzbh(recNo);// 受理回执编号或不受理回执编号
			perm.setId(spsxdxbh);

			// 0：未预审；1：预审未通过；2：在办；3：办结；4：预审通过；5：受理不通过；6：办理不通过；7:补交挂起；8:特别程序挂起；-1:业务无效
			if (byslNode != null) {
				perm.setIsfinish("1");
			} else if (banjieNode != null) {
				perm.setIsfinish("3");
			} else if ("补交告知".equals(guaqiKind)) {
				perm.setIsfinish("7");
			} else if ("特别程序申请".equals(guaqiKind)) {
				perm.setIsfinish("8");
			} else {
				perm.setIsfinish("2");
			}

			perm.setName(noAccept ? exRec.getString("fSPSXMC") : shouLi.getString("fSPSXMC"));
			// 前置行政区街道
			perm.setRegionarea(noAccept ? exRec.getString("fQZXZQJD") : shouLi.getString("fQZXZQJD"));
			// 受理具体地点(受理单位承办此受理业务人员所在的办公地点，精确到房间号或者窗口号)
			perm.setSljtdd(noAccept ? exRec.getString("fSLJTDD") : shouLi.getString("fSLJTDD"));
			// perm.setStagebh(stagebh);
			perm.setStatus("0");
			// 此笔业务提交方式（0-窗口提交(现场申办) 1-网上提交(网上大厅) 2-信函3-电报 4-电传 5-传真 6-邮件
			// 7-电子数据交换 8-窗口协办 9 - 村居代办 10 - 自助终端 11 - 移动终端)（补充）
			perm.setTjfs(exRec.getString("fTJFS") == null ? "1" : exRec.getString("fTJFS"));
			// 项目名称
			String xmmc = noAccept ? exRec.getString("fXMMC") : shouLi.getString("fXMMC");
			if (xmmc == null) {
				perm.setXmmc("无数据");
			} else {
				perm.setXmmc(xmmc);
			}

			// 添加要件附件(审批材料节点,至少一条记录)
			List<XmlOut> list = perm.getMaterials();
			this.addImpDocPage(ftp, list, bizRecId, flgdgxd, department);

			if (list.size() == 0) {
				Material m = new Material();
				// m.setAdjustment(adjustment)
				// m.setBaseinfo(baseinfo)
				m.setCopynum("0");
				m.setId("NULL");
				m.setIsneed("0");
				m.setMlid("NULL");
				m.setMlname("NULL");
				m.setMtype("5");
				m.setOrinum("0");
				m.setSelecttype("5");
				m.setStatus("0");
				list.add(m);
			}
			// TJFS 0 窗口 1网上提交(网上大厅)
			DataXml in0 = new DataXml();
			if ("1".equals(exRec.getString("fTJFS"))) {
				in0.setBstype("0");
			} else {
				in0.setBstype("1");
			}
			in0.setAreaid(flgdgxd);
			if (bizRec != null) {
				in0.setBsnum(bizRec.getString("fBizId"));
			} else {
				// 不予受理直接使用审批事项编号
				in0.setBsnum(spsxdxbh);
			}

			// 审批业务状态：0申报，1受理，2承办，3审核，4批准，5办结
			if (allNodes.length == 0) {
				in0.setBusistatus(jhjd);
				exRec.setString("fJHJD", jhjd);
				exRec.setString("fJHJDMC", "申报");
			} else {
				if (byslNode != null) {
					// 不予受理
					in0.setBusistatus("5");
					exRec.setString("fJHJD", "5");
					exRec.setString("fJHJDMC", "办结");
				} else if (banjieNode != null) {
					// 已办结
					in0.setBusistatus("5");
					exRec.setString("fJHJD", "5");
					exRec.setString("fJHJDMC", "办结");
				} else {
					in0.setBusistatus(jhjd);
					exRec.setString("fJHJD", jhjd);
					if ("1".equals(jhjd)) {
						exRec.setString("fJHJDMC", "受理");
					} else if ("2".equals(jhjd)) {
						exRec.setString("fJHJDMC", "承办");
					} else if ("3".equals(jhjd)) {
						exRec.setString("fJHJDMC", "审核");
					} else if ("4".equals(jhjd)) {
						exRec.setString("fJHJDMC", "批准");
					}
				}
			}
			// 测试每次交换是否需要保持一致
			in0.setOrderid(createOrderId(spsxdxbh, flgdgxd, recNo));// 任务表主键，调用函数CREATEORDERID生成
			in0.setPermid(spsxdxbh);
			in0.setSubpermid(noAccept ? exRec.getString("fSPSXZXBH") : shouLi.getString("fSPSXZXBH"));
			AuthenticateXml in2 = new AuthenticateXml();
			in2.setAuthenticateid(flgdgxd);
			in2.setPermid(spsxdxbh);
			// 验证XML
			String coopText = getElementXml(coop.getElement());
			/*
			 * 暂时屏蔽验证 try { this.validateXml(coopText); } catch
			 * (ExchangeException e) { // 保存交换XML到 验证异常 目录
			 * saveCooperationXml(now, recNo, coopText, "验证异常");
			 * this.setExTaskStatus(exTask, ExTaskStatus.ERROR, "交换xml验证异常:" +
			 * e.getMessage()); return; }
			 */
			// 上报数据
			String ret = null;
			try {
				ret = bcService.postData(getElementXml(in0.getElement()), getElementXml(coop.getElement()), getElementXml(in2.getElement()));
				// 保存交换XML到 交换信息 目录
				saveCooperationXml(now, recNo, coopText, "交换信息");
			} catch (Exception e) {
				// 保存SOAP异常信息
				saveErrorCause(now, recNo, e, "SOAP异常");
				this.setExTaskStatus(exTask, ExTaskStatus.ERROR, e.getMessage());
				return;
			}
			// 判断上报是否成功
			if ("1".equals(ret)) {
				this.setExTaskStatus(exTask, ExTaskStatus.SUCCESS, null);
				exRec.setDateTime("fLastExTime", now);
				if (exRec.getString("fJHJD").equals("5")) {
					if (this.logFinishCooperationXml)
						saveCooperationXml(now, recNo, coopText, "办结数据");
				}
				logger.info("[" + recNo + "]上报成功,当期环节:" + exRec.getString("fJHJDMC"));
			} else {
				saveCooperationXml(now, recNo, coopText, "提交失败");
				this.setExTaskStatus(exTask, ExTaskStatus.ERROR, "提交失败，服务端返回失败标示:" + ret);
			}
		} catch (Exception e) {
			logger.warn("案卷[" + exTask.getString("fBizRecId") + "]交换异常" + e.getMessage(), e);
			this.setExTaskStatus(exTask, ExTaskStatus.ERROR, e.getMessage());
		} finally {
			if (exRecTable != null) {
				try {
					exRecTable.save(EXCHANGE_DATA);
					BizDataUtils.mergeStateAfterSave(exRecTable);
				} catch (Exception e) {
					logger.error("保存交换控制表失败," + e.getMessage(), e);
				}
			}
		}
	}

	private BTFtpClient getFtpClient() {
		BTFtpClient ftp = null;
		try {
			ftp = new BTFtpClient(ftpHost, ftpPort);
			ftp.setDataTimeout(10000);
			if (ftpConnectTimeout > 0) {
				ftp.setConnectTimeout(ftpConnectTimeout);
			}
			ftp.login(ftpUser, ftpPassword);
			if (ftpPassiveMode) {
				if (!ftp.enterRemotePassiveMode()) {
					logger.error("FTP服务器无法进入PassiveMode...");
					try {
						ftp.disconnect();
					} catch (Exception e) {
					}
					ftp = null;
				}
			}
		} catch (Exception e) {
			logger.error("FTP服务异常，忽略要件附件处理:" + e.getMessage(), e);
			ftp = null;
			// return;
		}
		return ftp;
	}

	/**
	 * 交换的OrderId
	 * 
	 * @param sxid
	 * @param areaid
	 * @param bsnum
	 * @return
	 */
	private String createOrderId(String sxid, String areaid, String bsnum) {
		String preStr = (sxid + "-" + areaid).toUpperCase();
		String md5Str = (sxid + bsnum).toUpperCase();
		String str = null;
		try {
			str = preStr + getMD5Digest(md5Str);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 取输入字符串的 MD5 摘要（字符串形式）
	 * 
	 * @param str
	 *            String 输入字符串
	 * @throws NoSuchAlgorithmException
	 * @return String MD5 摘要（字符串形式）
	 */
	private String getMD5Digest(String str) throws java.security.NoSuchAlgorithmException {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

		byte[] md = getMD5Digest(str.getBytes());
		int j = md.length;
		char strOut[] = new char[j * 2];
		int k = 0;
		for (int i = 0; i < j; i++) {
			byte byte0 = md[i];
			strOut[k++] = hexDigits[byte0 >>> 4 & 0xf];
			strOut[k++] = hexDigits[byte0 & 0xf];
		}
		return new String(strOut);
	}

	/**
	 * 取输入的字节的 MD5 摘要
	 * 
	 * @param strBytes
	 *            byte[] 输入的字节
	 * @throws java.security.NoSuchAlgorithmException
	 * @return byte[] MD5 摘要
	 */
	private byte[] getMD5Digest(byte[] strBytes) throws java.security.NoSuchAlgorithmException {
		java.security.MessageDigest mdTemp = java.security.MessageDigest.getInstance("MD5");
		mdTemp.update(strBytes);
		return mdTemp.digest();
	}

	/**
	 * 替换无效的xml字符
	 * 
	 * @param ele
	 * @return
	 * @throws IOException
	 */
	private String getElementXml(Element ele) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		OutputFormat frt = new OutputFormat();
		frt.setEncoding("gbk");
		XMLWriter wrt = new XMLWriter(out, frt);
		wrt.write(ele);
		return out.toString("gbk").replaceAll("[\\x00-\\x08\\x0b-\\x0c\\x0e-\\x1f]", "");
	}

	/**
	 * 验证XML
	 * 
	 * @param targetXML
	 * @throws ExchangeException
	 */
	private void validateXml(String targetXML) throws ExchangeException {
		Validator v = getXmlValidator();
		if (v != null) {
			try {
				v.validate(new StreamSource(new StringReader(targetXML)));
			} catch (Exception e) {
				logger.warn(e.getMessage(), e);
				throw new ExchangeException("xml验证失败:" + e.getMessage(), e);
			}
		} else {
			throw new ExchangeException("xml验证器初始化失败");
		}
	}

	/**
	 * XML验证者
	 * 
	 * @return
	 */
	private Validator xmlValidator;

	private Validator getXmlValidator() {
		if (this.xmlValidator == null) {
			Source schemaFile = new StreamSource(this.getClass().getClassLoader().getResourceAsStream("/fsexchange/ws/schema/exchange.xsd"));
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			try {
				Schema schema = factory.newSchema(schemaFile);
				this.xmlValidator = schema.newValidator();
			} catch (SAXException e) {
				logger.error(e);
			}

		}
		return this.xmlValidator;
	}

	private void saveCooperationXml(Date now, String recNo, String context, String type) {
		if (this.logDir == null) {
			return;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		File file = new File(this.logDir + File.separator + type + File.separator + sdf.format(now) + File.separator + recNo + ".xml");
		file.getParentFile().mkdirs();
		if (file.exists()) {
			file.delete();
		}
		try {
			if (file.createNewFile()) {
				FileOutputStream fileOut = new FileOutputStream(file);
				try {
					fileOut.write(context.getBytes("gbk"));
					fileOut.flush();
				} finally {
					fileOut.close();
				}
			}
		} catch (Exception e) {
			logger.warn("记录交换实体文件发生异常:" + e.getMessage());
		}
	}

	/**
	 * 保存异常信息，包括XML验证异常、SOAP异常、空指针错误？？
	 * 
	 * @param now
	 * @param recNo
	 * @param err
	 * @param errorType
	 */
	private void saveErrorCause(Date now, String recNo, Exception err, String errorType) {
		if (this.logDir == null) {
			return;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		File file = new File(this.logDir + File.separator + errorType + File.separator + sdf.format(now) + File.separator + recNo + ".log");
		file.getParentFile().mkdirs();
		if (file.exists()) {
			file.delete();
		}
		try {
			if (file.createNewFile()) {
				FileOutputStream fileOut = new FileOutputStream(file);
				PrintStream ps = new PrintStream(fileOut);
				try {
					err.printStackTrace(ps);
					fileOut.flush();
				} finally {
					fileOut.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
