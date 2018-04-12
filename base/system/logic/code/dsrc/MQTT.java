import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.butone.flowbiz.FlowBizConsts;
import com.butone.message.MqttMessageDispatcher;
import com.butone.message.MqttMessageDispatcher.DeliveryComplete;
import com.butone.x5Impl.BusinessServerImpl;
import com.justep.common.SystemUtils;
import com.justep.exception.BusinessException;
import com.justep.filesystem.FileSystemWrapper;
import com.justep.message.dispatcher.Message;
import com.justep.model.Config;
import com.justep.model.ModelUtils;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.KSQL;
import com.justep.system.data.Row;
import com.justep.system.data.Table;
import com.justep.system.data.Transaction;
import com.justep.system.opm.OrgUnit;
import com.justep.system.opm.OrgUtils;
import com.justep.system.opm.Person;
import com.justep.system.process.Task;
import com.justep.system.process.TaskDB;
import com.justep.system.process.TaskEngine;
import com.justep.system.process.TaskKind;
import com.justep.system.util.CommonUtils;
import com.justep.util.JustepConfig;
import com.justep.util.Utils;

public class MQTT {

	private static String mqttServerHost;
	private static int mqttServerPort;
	private static boolean allowMultiClient;
	static {
		reloadMQTTConfig();
	}

	/**
	 * 保存并重载配置
	 * 
	 * @param config
	 */
	public static void saveMQTTConfig(Map<String, Object> config) {
		File file = new File(FileSystemWrapper.instance().getRealPath("/base/system/messageCenter/mqtt.config.m"));
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(file);
			Element mqtt = (Element) doc.selectSingleNode("//*[local-name(.)='config'][@name='mqtt']");
			Element e = (Element) mqtt.selectSingleNode("./*[local-name(.)='item'][@name='host']");
			Attribute attr = e.attribute("value");
			attr.setText(String.valueOf(config.get("host")));

			attr = ((Element) mqtt.selectSingleNode("./*[local-name(.)='item'][@name='port']")).attribute("value");
			attr.setText(String.valueOf(config.get("port")));

			attr = ((Element) mqtt.selectSingleNode("./*[local-name(.)='item'][@name='allowMultiClient']")).attribute("value");
			attr.setText(String.valueOf(config.get("allowMultiClient")));

			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("utf-8");
			XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
			writer.write(doc);
			writer.close();
			reloadMQTTConfig();
		} catch (Exception e) {
			throw new BusinessException("读写配置文件异常", e.getCause());
		}

	}

	/**
	 * 重新加载服务配置
	 */
	public static void reloadMQTTConfig() {
		Config config = (Config) ModelUtils.getModelObjectByFullName("/base/system/messageCenter/mqtt", Config.TYPE);
		mqttServerHost = config.getItem("host").getValue();
		mqttServerPort = Integer.parseInt(config.getItem("port").getValue());
		allowMultiClient = "true".equals(config.getItem("allowMultiClient").getValue());
	}

	/**
	 * 查询服务配置，用于浏览器监听
	 * 
	 * @return
	 */
	public static Map<String, Object> queryMQTTConfig() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("host", mqttServerHost);
		result.put("port", mqttServerPort);
		result.put("allowMultiClient", allowMultiClient);
		return result;
	}

	/**
	 * 发送消息
	 * 
	 * @param targets
	 *            personIDorFID，多个人员逗号分隔
	 * @param type
	 *            终端类型
	 * @param title
	 *            消息标题
	 * @param url
	 *            消息url
	 * @param exts
	 *            消息扩展属性
	 * @param kind
	 *            消息类型
	 */
	public static boolean sendMessage(final String targets, String type, final String title, String url, final Map<String, Object> exts, String kind) {
		if (SystemUtils.isEmptyString(targets) || "undefined".equals(targets) || SystemUtils.isEmptyString(title) || "undefined".equals(title))
			return false;
		if ("all".equals(type) || "default".equals(type))
			type = "";
		Set<String> orgs = new HashSet<String>();
		for (String target : targets.split(",")) {
			target = target.trim();
			if (SystemUtils.isNotEmptyString(target)) {
				orgs.add(target);
			}
		}
		if (orgs.isEmpty())
			return false;
		if ("undefined".equals(url))
			url = null;
		if (SystemUtils.isNotEmptyString(url)) {
			url = url.trim();
			if (url.startsWith("/")) {
				url = JustepConfig.getUIServer() + url;
			} else if (url.startsWith("$UI")) {
				url = url.replace("$UI", "/UI2");
				url = JustepConfig.getUIServer() + url;
			}
		}
		Person p = ContextHelper.getPerson();
		final String senderID = p.getID();
		final String messaageUrl = url;
		final String msgID = CommonUtils.createShortGUID();
		final String msgKind = "undefined".equals(kind) || "null".equals(kind) || Utils.isEmptyString(kind) ? null : kind;
		Map<String, Object> messageExts = new HashMap<String, Object>();
		if (exts != null)
			messageExts.put("exts", exts);

		messageExts.put("msgID", msgID);
		messageExts.put("senderID", senderID);
		messageExts.put("senderName", p.getName());
		if (SystemUtils.isNotEmptyString(msgKind))
			messageExts.put("kind", msgKind);
		// 后期微信消息有URL显示详细，暂时屏蔽
		url = null;
		Message msg = new Message(title, url, type, messageExts);
		try {
			new MqttMessageDispatcher().sendMessage(msg, orgs, new DeliveryComplete() {
				@Override
				public void onComplete(IMqttDeliveryToken token) {
					try {
						String sExts = null;
						if (exts != null) {
							JSONObject mbody = new JSONObject();
							mbody.putAll(exts);
							sExts = mbody.toJSONString();
						}
						saveMessage(msgID, senderID, targets, msgKind, title, sExts, messaageUrl);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 发送流程消息
	 * 
	 * @param flowGuid
	 * @param type
	 * @param title
	 * @param url
	 * @param exts
	 * @param kind
	 */
	public static boolean sendFlowMessage(String flowGuid, String type, String title, String url, Map<String, Object> exts, String kind) {
		Map<String, Task> tasks = TaskDB.loadFlowByTask(flowGuid);
		Set<String> targets = new HashSet<String>();
		for (Task t : tasks.values()) {
			pickupActivationExcutorFormTask(t, targets, false);
		}
		if (targets.size() > 0) {
			return sendMessage(StringUtils.join(targets, ","), type, title, url, exts, kind);
		}
		return false;
	}

	/**
	 * 发送案卷消息
	 * 
	 * @param flowGuid
	 * @param type
	 * @param title
	 * @param url
	 * @param exts
	 * @param kind
	 */
	public static void sendBizRecMessage(String bizRecId, String type, String title, String url, Map<String, Object> exts, String kind) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("guid", bizRecId);
		Table table = KSQL.select("select b.fFlowId as fFlowId from B_BizRec b where b=:guid", params, FlowBizConsts.DATA_MODEL_CORE_FLOW, null);
		Iterator<Row> i = table.iterator();
		if (i.hasNext()) {
			sendFlowMessage(i.next().getString("fFlowId"), type, title, url, exts, kind);
		}
	}

	/**
	 * 发送任务消息
	 * 
	 * @param flowGuid
	 * @param type
	 * @param title
	 * @param url
	 * @param exts
	 * @param kind
	 */
	public static boolean sendTaskMessage(String taskGuid, String type, String title, String url, Map<String, Object> exts, String kind) {
		Map<String, Task> tasks = TaskDB.loadFlowByTask(taskGuid);
		Task t = tasks.get(taskGuid);

		Set<String> targets = new HashSet<String>();
		pickupActivationExcutorFormTask(t, targets, false);
		for (Task child : t.getChildren()) {
			pickupActivationExcutorFormTask(child, targets, false);
		}

		if (targets.size() > 0) {
			return sendMessage(StringUtils.join(targets, ","), type, title, url, exts, kind);
		}
		return false;
	}

	public static boolean sendNextStepTaskMessage(String taskGuid, String type, String title, String url, Map<String, Object> exts, String kind) {
		Map<String, Task> tasks = TaskDB.loadFlowByTask(taskGuid);
		Task t = tasks.get(taskGuid);
		if (t.getKind().equals(TaskKind.EXECUTOR)) {
			t = t.getParent();
		}
		Set<String> targets = new HashSet<String>();
		for (Task n : t.getNexts()) {
			pickupActivationExcutorFormTask(n, targets, true);
		}
		if (targets.size() > 0) {
			return sendMessage(StringUtils.join(targets, ","), type, title, url, exts, kind);
		}
		return false;
	}

	private static void pickupActivationExcutorFormTask(Task task, Set<String> executors, boolean next) {

		if (!task.activation())
			return;
		if (TaskKind.NOTICE.equals(task.getKind()) || TaskKind.PROCESSINSTANCE.equals(task.getKind()))
			return;
		if (Utils.isNotEmptyString(task.getExecutorPersonID())) {
			executors.add(task.getExecutorPersonID());
		} else {
			List<OrgUnit> orgs = OrgUtils.findOrgChildren2(task.getExecutorFID(), null, null, false, true, true);
			for (OrgUnit org : orgs) {
				executors.add(OrgUtils.getPersonIDByFID(org.getFID()));
			}
		}
		if (next) {
			for (Task n : task.getNexts()) {
				pickupActivationExcutorFormTask(n, executors, true);
			}
		}
	}

	/**
	 * 保存消息
	 * 
	 * @param msgID
	 * @param senderID
	 * @param targets
	 * @param kind
	 * @param title
	 * @param exts
	 * @param url
	 * @throws Exception
	 */
	private static void saveMessage(String msgID, String senderID, String targets, String kind, String title, String exts, String url)
			throws Exception {
		BusinessServerImpl.createRequestContext2(null, null, null, null, null);
		Transaction t = ContextHelper.getTransaction();
		try {
			t.begin();

			Map<String, Object> varMap = new HashMap<String, Object>();
			varMap.put("guid", msgID);
			varMap.put("createtime", CommonUtils.getCurrentDateTime());
			varMap.put("senderid", senderID);
			varMap.put("url", url);
			varMap.put("title", title);
			varMap.put("exts", exts);
			varMap.put("kind", kind);
			KSQL.executeUpdate(
					"insert into B_Message b (b,b.version,b.fCreateTime,b.fSenderID,b.fSenderName,b.fURL,b.fTitle,b.fExts,b.fKind) values (:guid,0,:createtime,:senderid,(select p.sName from SA_OPPerson p where p=:senderid),:url,:title,:exts,:kind)",
					varMap, "/base/system/data", null, t);

			for (String target : targets.split(",")) {
				target = target.trim();
				if (SystemUtils.isEmptyString(target))
					continue;
				varMap.clear();
				varMap.put("guid", CommonUtils.createShortGUID());
				varMap.put("mid", msgID);
				if (target.endsWith(".psm"))
					target = OrgUtils.getPersonIDByFID(target);
				varMap.put("targetID", target);
				varMap.put("statusid", "rsReady");
				varMap.put("statusname", "尚未处理");
				KSQL.executeUpdate(
						"insert into B_MessageRecord b (b,b.version,b.fMessageID,b.fTargetID,b.fTargetName,b.fStatusID,b.fStatusName) values (:guid,0,:mid,:targetID,(select p.sName from SA_OPPerson p where p=:targetID),:statusid,:statusname)",
						varMap, "/base/system/data", null, t);
			}
			t.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (t != null) {
				t.rollback();
			}
		} finally {
			BusinessServerImpl.removeRequestContext();
		}

	}

	/**
	 * 统计消息数量
	 * 
	 * @return
	 */
	public static Map<String, Object> statisticsNoReadMessage() {
		JSONObject result = new JSONObject();
		Map<String, Object> varMap = new HashMap<String, Object>();
		varMap.put("pid", ContextHelper.getPerson().getID());
		Table t = KSQL
				.select("select b.fKind as fKind,count(b) as fCnt from B_Message b join B_MessageRecord r on b = r.fMessageID where r.fTargetID=:pid and r.fStatusID='rsReady' group by b.fKind",
						varMap, "/base/system/data", null);
		int totle = 0;
		JSONArray items = new JSONArray();
		if (t.size() > 0) {
			Iterator<Row> i = t.iterator();
			while (i.hasNext()) {
				Row r = i.next();
				int cnt = r.getDecimal("fCnt").intValue();
				totle += cnt;
				JSONObject item = new JSONObject();
				String kind = r.getString("fKind");
				if (SystemUtils.isEmptyString(kind))
					kind = "其他";
				item.put("name", kind);
				item.put("count", cnt);
				items.add(item);
			}
		}
		result.put("items", items);
		result.put("total", totle);
		return result;

	}

	public static void deleteMessage(String ids, String kind) {
		ids = ids.replaceAll(",", "','");
		String sql = "update B_MessageRecord r set r.fStatusID='rsFinished',r.fStatusName='已完成'" + " where r.fTargetID = '"
				+ ContextHelper.getPerson().getID() + "' and r.fStatusID='rsReady'";
		if (Utils.isNotEmptyString(ids))
			sql += " and r.FGUID in ('" + ids + "')";
		else {
			if (Utils.isNotEmptyString(kind) && kind.equals("全部消息")) {
				sql += " and 1=1";
			} else if (Utils.isNotEmptyString(kind) && kind.equals("其他")) {
				sql += " and exists (select 1 from B_Message m where r.fMessageID=m.FGUID and m.fKind is null)";
			} else {
				sql += " and exists (select 1 from B_Message m where r.fMessageID=m.FGUID and m.fKind = '" + kind + "')";
			}
		}
		KSQL.executeUpdate(sql, null, "/base/system/data", null);
	}
	
	// 获取系统更新日志或系统停用通知
	public static JSONObject getSysNoticeInfo(String rowID, String fType) {
		JSONObject result = new JSONObject();
		String sql = "select t.* from B_SystemUpdateLog t ";
		if(Utils.isNotEmptyString(rowID))
			sql += " where t='"+rowID+"'";
		else
			sql += "where t.fType='"+ fType +"' and t.fStatus='已发布' and :currentDateTime() between t.fStartTime and t.fEndTime order by t.fPublishDate desc";
		Map<String, Object> varMap = new HashMap<String, Object>();
		Table t = KSQL.select(sql, varMap, "/base/system/data", null);
		if (t.size() > 0) {
			Iterator<Row> i = t.iterator();
			Row r = i.next();
			result.put("mesFID", r.getString("t"));
			result.put("mesTitle", r.getString("fTitle"));
			result.put("mesContent", r.getString("fContext"));
		}
		return result;
	}

	// 引用消息接收表做更新日志的接收人记录
	public static void updateSysNoticeStatus(String mesFID) {
		String sql = "insert into B_MessageRecord b (b,b.version,b.fMessageID,b.fTargetID,b.fTargetName,b.fStatusID,b.fStatusName)" +
				" values (:guid(),0,'"+mesFID+"',:currentPersonID(),:currentPersonName(),'rsFinished','已完成')";
		KSQL.executeUpdate(sql,	null, "/base/system/data", null);
	}
}
