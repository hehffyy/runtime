import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.butone.utils.ModelExtUtils;
import com.justep.exception.BusinessException;
import com.justep.message.SystemMessages;
import com.justep.model.Config;
import com.justep.model.Model;
import com.justep.model.ModelUtils;
import com.justep.system.context.ContextHelper;
import com.justep.system.opm.Operator;
import com.justep.system.opm.OrgConstants;
import com.justep.system.opm.OrgUnit;
import com.justep.system.opm.OrgUtils;
import com.justep.system.opm.Person;
import com.justep.system.opm.PersonMember;
import com.justep.system.opm.api.PersonHelper;
import com.justep.system.process.ExpressEngine;
import com.justep.system.util.CommonUtils;
import com.justep.util.Utils;

public class SystemProcedure {
	/**
	 * 获取
	 * 
	 * @param process
	 * @return
	 */
	public static String getDataModelList(String process) {
		String processModel = process.substring(0, process.lastIndexOf("/"));
		Model model = null;
		while (!processModel.equals("") && model == null) {
			try {
				processModel = processModel.substring(0, processModel.lastIndexOf("/"));
				model = ModelUtils.getModel(processModel + "/data");
			} catch (Exception e) {
			}
		}
		if (model != null) {
			return model.getFullName();
		}
		return "/system/data";
	}

	public static Object getProcessList() {
		Document result = DocumentHelper.createDocument();
		Element root = DocumentHelper.createElement("items");
		result.setRootElement(root);
		Person psn = ContextHelper.getPerson();
		for (String proc : ContextHelper.getProcessList()) {
			Utils.check(Utils.isNotEmptyString(proc), SystemMessages.class, SystemMessages.PROCESS_LIST_NULL);
			Element procElem = root.addElement("item");
			String process = CommonUtils.getPathOfFile(proc);
			String activ = CommonUtils.getNameOfFile(proc);
			// Utils.check(!OrgConstants.ANY.equals(activ),
			// "目前不支持activity为*的授权！");
			if (!OrgConstants.ANY.equals(activ)) {
				procElem.addAttribute("process", process);
				procElem.addAttribute("activity", activ);
				procElem.addAttribute("url", proc);
				procElem.addAttribute("psm-count", Integer.toString(psn.getPersonMemberCount() == 1 ? 1 : psn.getAuthorizedCount(process, activ)));
			}
		}
		return result;
	}

	public static Object getAgentList(String type) {
		if (Utils.isEmptyString(type)) {
			type = "xml";
		}

		if (!type.equals("xml")) {
			List<Map<String, String>> result = new ArrayList<Map<String, String>>();

			for (Person psn : ContextHelper.getOperator().getAgents()) {
				Map<String, String> item = new HashMap<String, String>();
				item.put("id", psn.getID());
				item.put("name", psn.getName());

				result.add(item);
			}

			return result;

		} else {
			Document result = DocumentHelper.createDocument();
			Element root = DocumentHelper.createElement("items");
			result.setRootElement(root);
			for (Person psn : ContextHelper.getOperator().getAgents()) {
				Element procElem = root.addElement("psn");
				procElem.addAttribute("id", psn.getID());
				procElem.addAttribute("name", psn.getName());
			}
			return result;
		}
	}

	public static Object getPersonMembers(String process, String activity, String type) {
		if (Utils.isEmptyString(type)) {
			type = "xml";
		}

		if (!type.equals("xml")) {
			Map<String, String> result = new HashMap<String, String>();
			for (PersonMember pm : ContextHelper.getOperator().getAuthorizedPersonMembers(process, activity, ContextHelper.getPerson().getID())) {
				result.put(pm.getFID(), pm.getFName());
			}
			return result;
		} else {
			Document result = DocumentHelper.createDocument();
			Element root = DocumentHelper.createElement("items");
			result.setRootElement(root);
			for (PersonMember pm : ContextHelper.getOperator().getAuthorizedPersonMembers(process, activity, ContextHelper.getPerson().getID())) {
				Element psmElem = root.addElement("psm");
				psmElem.addAttribute("fid", pm.getFID());
				psmElem.addAttribute("fname", pm.getFName());
			}
			return result;
		}
	}

	public static Object getSysParams() {
		Operator op = ContextHelper.getOperator();
		String executor = ContextHelper.getActionContext().getExecutor();
		if (Utils.isNotEmptyString(executor) && executor.endsWith(".psm")) {
			String personId = OrgUtils.getPersonIDByFID(executor);
			if (!op.getID().equals(personId)) {
				if (!op.getAllPersonMemberFIDs().contains(executor)) {
					OrgUnit org = OrgUtils.findOrgUnitsByFID(executor).get(0);
					throw new BusinessException("您没有【" + org.getFName() + "】的代理权限");
				}
			}
		}

		@SuppressWarnings("unchecked")
		Map<String, Object> ret = (Map<String, Object>) ContextHelper.getSysParams();
		// TODO tkj 2016-10-20 增加前台justep.Context的参数，注意需要处理所有Login方法
		Person p = ContextHelper.getPerson();
		Object o = p.getAttribute("sysParams");
		if (o == null) {
			// 代理环境或者登录时未设置
			com.justep.system.opm.api.Person pp = PersonHelper.loadPerson(p.getID(), Arrays.asList(new String[] { "sTitle", "sGlobalSequence" }));
			p.setAttribute("sTitle", pp.getExtValue("sTitle"));
			p.setAttribute("sGlobalSequence", pp.getExtValue("sGlobalSequence"));
			// 设置任务中心签收模式
			Config c = (Config) ModelUtils.getModel("/system/config").getLocalObject("signMode", Config.TYPE);
			String expr = c == null ? "" : c.getValue();
			try {
				p.setAttribute("signMode", ExpressEngine.calculateBoolean(expr, null, true, ModelUtils.getModel("/base/core/logic/fn")));
			} catch (Exception e) {
			}
			p.setAttribute("sysParams", true);
		}
		ret.put("postName", p.getAttribute("sTitle"));
		ret.put("globalSequence", p.getAttribute("sGlobalSequence"));
		ret.put("sysNoticeInfo", p.getAttribute("sysNoticeInfo"));
		
		//TODO 临时方案待优化
		/**
		 * @see /UI/base/core/template/default/flowtemplate/businessActivityTemplate.w
		 */
		String processDialogWindow = (String) ContextHelper.getActionContext().getProcess()
				.getExtAttributeValue(ModelExtUtils.MODEL_EXT_URI, "processDialogWindow");
		if (processDialogWindow == null) {
			Config c = (Config) ModelUtils.getModel("/system/config").getLocalObject("processDialogWindow", Config.TYPE);
			if (c != null && Utils.isNotEmptyString(c.getValue())) {
				processDialogWindow = c.getValue();
			}
		}
		if (Utils.isNotEmptyString(processDialogWindow)) {
			ret.put("processDialogWindow", processDialogWindow);
		}
		
		/**
		 * @see /UI/base/core/flowOperation/bizOperation.js
		 */
		String batchAdvanceProcessDialog = (String) ContextHelper.getActionContext().getProcess()
		.getExtAttributeValue(ModelExtUtils.MODEL_EXT_URI, "batchAdvanceProcessDialog");
		if (batchAdvanceProcessDialog == null) {
			Config c = (Config) ModelUtils.getModel("/system/config").getLocalObject("batchAdvanceProcessDialog", Config.TYPE);
			if (c != null && Utils.isNotEmptyString(c.getValue())) {
				batchAdvanceProcessDialog = c.getValue();
			}
		}
		if (Utils.isNotEmptyString(batchAdvanceProcessDialog)) {
			ret.put("batchAdvanceProcessDialog", batchAdvanceProcessDialog);
		}
		
		return ret;
	}

	public static void openActivity() {
		// 不处理，为了记录操作日志和添加事件使用
	}

	public static void getSysCode() {
		// 不处理，获取系统代码
	}

	public static void closeActivity() {
		// 不处理，为了记录操作日志和添加事件使用
	}

	public static void createTaskRemindProcedure(String sid) {

	}

	public static String getActivationPackage() {
		return Utils.getActivationPackage();
	}

	public static java.sql.Date getActivationTime() {
		return Utils.getActivationTime();
	}

	public static boolean regActivationPackage(String sPackage) {
		return Utils.regActivationPackage(sPackage);
	}
}