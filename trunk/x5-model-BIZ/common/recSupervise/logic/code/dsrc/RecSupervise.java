import java.util.*;

import com.butone.utils.SysUtils;
import com.justep.common.SystemUtils;
import com.justep.model.ModelUtils;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.Expression;
import com.justep.system.data.Row;
import com.justep.system.data.Table;
import com.justep.system.opm.OrgUnit;
import com.justep.system.opm.OrgUtils;
import com.justep.system.opm.PersonMember;
import com.justep.system.opm.api.Person;
import com.justep.system.opm.api.PersonHelper;
import com.justep.system.process.Task;
import com.justep.system.process.TaskDB;
import com.justep.system.process.TaskKind;
import com.justep.system.transform.Utils;
import com.justep.system.util.CommonUtils;

public class RecSupervise {
	
	private final static String BASE_FNMODEL = "/base/core/logic/fn";
	
	public static void addBizRecs(List<String> bizrecids) {
		String sql = "insert into B_SuperviseRec(fdbid,version,fbizrecid,fcreatorid,fcreator,fdeptid,fcreatedate,fdbrid,fdbr,fstatus)" +
		" values(sys_guid(),0,?,?,?,?,?,?,?,'处理中')";
		for (String bizrecid : bizrecids) {
			SysUtils.executeSql(sql, bizrecid, ContextHelper.getPerson().getID(), ContextHelper.getPerson().getName(),
					ContextHelper.getPersonMember().getDept().getID(), CommonUtils.getCurrentDate(),
					ContextHelper.getPerson().getID(), ContextHelper.getPerson().getName());
		}
	}

	public static void delBizRec(String dbid){
		SysUtils.executeSql("delete from B_SuperviseStage where fdbid=?", dbid);
		SysUtils.executeSql("delete from B_SuperviseRec where fdbid=?", dbid);
	}

	public static void finishBizRec(String dbid){
		SysUtils.executeSql("update B_SuperviseRec set fStatus='已完成',fFinishDate=sysdate where fdbid=?",dbid);
	}

	public static void sendPhaseReminderMessage(){
		String sql = "select sup.fbizrecid,sup.fdbrid,attr.fincomedocname,attr.farchivescode from B_SuperviseRec sup" +
				" left join b_bizrecattr attr on sup.fbizrecid=attr.fbizrecid where sup.fstatus='处理中'" +
				" and exists(select 1 from B_SuperviseStage sta where sup.fdbid=sta.fdbid and trunc(sta.fenddate-1)=trunc(sysdate))";
		Table tab = SysUtils.query(sql);
		Iterator<Row> it = tab.iterator();
		// 发送消息fn函数准备参数
		Map<String, Object> variables = new HashMap<String, Object>();
		while(it.hasNext()){
			Row r = it.next();
			variables.clear();
			String personID = r.getString(1);
			variables.put("targets", personID);
			variables.put("type", "");
			String content = "办文号为【"+r.getString(3)+"】的案卷阶段明天到期。";
			variables.put("title", content);
			variables.put("url", "");
			variables.put("exts", null);
			variables.put("kind", "督办案卷提醒");
			String exp = "sendMessage(:targets,:type,:title,:url,:exts,:kind)";
			// 执行发送消息的fn函数
			Expression.evaluate(exp, variables, ModelUtils.getModel(BASE_FNMODEL));
			
			// 执行发送短信的fn函数
			exp = "sendSMSMessage(:personIDOrFIDs,:fMessageContent,:typeName)";
			variables.clear();
			List<String> personIDs = new ArrayList<String>();
			personIDs.add(personID);
			variables.put("personIDOrFIDs", personIDs);
			variables.put("fMessageContent", content);
			variables.put("typeName", "督办提醒短信");
			Expression.evaluate(exp, variables, ModelUtils.getModel(BASE_FNMODEL));
		}
	}

	public static String sendCuiBanInfo(String fDbID,String fCuiBanInfo,String fCuiBanType,String fBizRecID){
		String result = "催办信息发送失败，未查找到案卷信息！";
		String sql = "select b.fFlowId from B_BizRec b where fBizRecId=?";
		Table tab = SysUtils.query(sql, fBizRecID);
		if(tab.size()==0)
			return result;
		Iterator<Row> it = tab.iterator();
		if(it.hasNext()){
			String sFlowID = it.next().getString(0);
			if(SystemUtils.isNotEmptyString(sFlowID)){
				List<String> executorIDs = new ArrayList<String>();
				List<String> executorNames = new ArrayList<String>();
				Map<String, Task> tasks = TaskDB.loadFlowByTask(sFlowID);
				String personID = "";
				for (Task task : tasks.values()) {
					if (!task.activation())
						continue;
					if (TaskKind.NOTICE.equals(task.getKind())
							|| TaskKind.PROCESSINSTANCE.equals(task.getKind()))
						continue;
					if (Utils.isNotEmptyString(task.getExecutorPersonID())) {
						personID = task.getExecutorPersonID();
						executorIDs.add(personID);
						Person psn = PersonHelper.loadPerson(personID, null);
						executorNames.add(psn.getName());
					} else {
						List<OrgUnit> orgs = OrgUtils.findOrgChildren2(
								task.getExecutorFID(), null, null, false, true, true);
						for (OrgUnit org : orgs) {
							personID = OrgUtils.getPersonIDByFID(org.getFID());
							executorIDs.add(personID);
							Person psn = PersonHelper.loadPerson(personID, null);
							executorNames.add(psn.getName());
						}
					}
				}
				if(executorIDs.size()==0)
					return "催办信息发送失败，未查找到案卷的办理人员信息！";
				
				// 发送消息fn函数准备参数
				Map<String, Object> variables = new HashMap<String, Object>();
				String exp = "";
				if(fCuiBanType.contains("微信")){
					String str_personIDs = executorIDs.toString().replaceAll(" ", "");
					str_personIDs = str_personIDs.substring(1,str_personIDs.length()-1);
					variables.put("targets", str_personIDs);
					variables.put("type", "");
					variables.put("title", fCuiBanInfo);
					variables.put("url", "");
					variables.put("exts", null);
					variables.put("kind", "督办催办提醒");
					exp = "sendMessage(:targets,:type,:title,:url,:exts,:kind)";
					// 执行发送消息的fn函数
					Expression.evaluate(exp, variables, ModelUtils.getModel(BASE_FNMODEL));
				}
				
				if(fCuiBanType.contains("短信")){
					// 执行发送短信的fn函数
					exp = "sendSMSMessage(:personIDOrFIDs,:fMessageContent,:typeName)";
					variables.clear();
					variables.put("personIDOrFIDs", executorIDs);
					variables.put("fMessageContent", fCuiBanInfo);
					variables.put("typeName", "督办催办提醒");
					Expression.evaluate(exp, variables, ModelUtils.getModel(BASE_FNMODEL));
				}
				result = "已成功发送催办消息给"+ executorNames.toString();
			}
			
			sql = "insert into B_SuperviseCuiBan(fID,version,fDbID,fDeptID,fCreatorID,fCreator,fCreateDate,fCuiBanInfo,fCuiBanType,fBizRecID)" +
					" values(sys_guid(),0,?,?,?,?,?,?,?,?)";
			SysUtils.executeSql(sql, fDbID, ContextHelper.getPersonMember().getDept().getID(), ContextHelper.getPerson().getID(), ContextHelper.getPerson().getName(),
					CommonUtils.getCurrentDate(), fCuiBanInfo, fCuiBanType, fBizRecID);
		}
		return result;
	}
}