import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

import com.justep.system.data.*;
import com.justep.util.Utils;

public class MySMS {

	private final static String DATAMODEL = "/common/mySMS/data";
	
	public static Map<String,String> sendSMSMessage(String smsID) {
		 Map<String,String> result = new  HashMap<String,String>();
		if (Utils.isNotEmptyString(smsID)) {
			HashMap<String, String> sqlMap = new HashMap<String, String>();
			String sql = "select t.fSMSContent,t.fSMSType,t.fIsReplay from B_smsInfo t where t.fID='"+smsID+"'";
			sqlMap.put(DatabaseProduct.DEFAULT.name(), sql);
			Table table = SQL.select(sqlMap, null, DATAMODEL);
			Iterator<Row> it = table.iterator();
			if (it.hasNext()) {
				Row r = it.next();
				String fSMSContent = String.valueOf(r.getValue(0));
				String fSMSType = String.valueOf(r.getValue(1));
				int fIsReplay = Integer.parseInt(String.valueOf(r.getValue(2)==null?"0":r.getValue(2)));
				// 查询人员信息
				String psnSql = "select t.fPhone from B_smsReceivePerson t where t.fSMSID='"+smsID+"'";
				sqlMap.clear();
				sqlMap.put(DatabaseProduct.DEFAULT.name(), psnSql);
				Table psnTable = SQL.select(sqlMap, null, DATAMODEL);
				Iterator<Row> pit = psnTable.iterator();
				if (null != psnTable && psnTable.size() >= 0) {
					StringBuffer mobileStr = new StringBuffer();
					for (int i = 0; i < psnTable.size(); i++) {
						Row psnRow = pit.next();
						String mobilephone = psnRow.getString(0) == null ? "" : psnRow.getString(0);
						if (Utils.isNotEmptyString(mobilephone)) {
							mobileStr.append(mobilephone).append(";");
						}
					}
					if (Utils.isNotNull(mobileStr) && Utils.isNotEmptyString(mobileStr.toString())) {
						String mobileStrs = mobileStr.toString();
						if(mobileStr.toString().endsWith(";")){
							mobileStrs = mobileStrs.substring(0, mobileStrs.length()-1);
						}
						// 短信发送任务表 TMIS_SMSSENDTASK
						sql = "INSERT INTO TMIS_SMSSENDTASK(FSMSGUID,FDESTADDR,FMESSAGECONTENT,FSTATUSREPORT,FSTATE,FREQUESTID,FREQUESTTYPE,FNEEDBACK)"
								+ " VALUES(sys_guid(),?,?,1,'等待中',?,?,?)";
						sqlMap.clear();
						ArrayList<Object> binds = new ArrayList<Object>();
						byte[] bytes = mobileStrs.getBytes();
						InputStream inputStream = new ByteArrayInputStream(bytes);
						binds.add(inputStream);
						binds.add(fSMSContent);
						binds.add(smsID);
						binds.add(fSMSType);
						binds.add(fIsReplay);
						sqlMap.put(DatabaseProduct.DEFAULT.name(), sql);
						SQL.executeUpdate(sqlMap, binds, DATAMODEL);
						result.put("status", "true");
						result.put("info", "短信发送成功！");
						return result;
					}
					result.put("status", "false");
					result.put("info", "手机号码不能为空！");
					return result;
				}
				result.put("status", "false");
				result.put("info", "短信接收者不能为空！");
				return result;
			}
			result.put("status", "false");
			result.put("info", "未找到短信内容！");
			return result;
		} else {
			result.put("status", "false");
			result.put("info", "未找到短信内容！");
			return result;
		}
	}

	public static String copyPersonInfoFromOrgToRec(List<String> personIDs, String smsID) {
		String result = "添加成功！";
		String ids = personIDs.toString().replaceAll(" ", "").replaceAll(",", "','");
		ids = ids.substring(1, ids.length() - 1);
		HashMap<String, String> sqlMap = new HashMap<String, String>();
		String sql = "select count(t.sid) from sa_opperson t where t.sID in('" + ids + "') and (t.smobilephone is null or exists(select 1 from B_smsReceivePerson b" +
				" where b.fPersonID=t.sID and b.fSMSID='" + smsID + "' and b.fPhone=t.smobilephone))";
		sqlMap.put(DatabaseProduct.DEFAULT.name(), sql);
		Table tab = SQL.select(sqlMap, null, DATAMODEL);
		int count = Integer.parseInt(String.valueOf(tab.iterator().next().getValue(0)));
		if (count > 0)
			result = "添加" + personIDs.size() + "个人员，其中" + count + "个未登记手机号或已添加至列表中！";
		sql = "insert into B_smsReceivePerson r select sys_guid(),0,t.sid,t.sname,t.smobilephone,'"+smsID+"' from sa_opperson t" +
		" where t.sID in('"+ids+"') and t.smobilephone is not null and not exists(select 1 from B_smsReceivePerson b" +
				" where b.fPersonID=t.sID and b.fSMSID='"+smsID+"' and b.fPhone=t.smobilephone)";
		System.out.println(sql);
		sqlMap.clear();
		sqlMap.put(DatabaseProduct.DEFAULT.name(), sql);
		SQL.executeUpdate(sqlMap, null, DATAMODEL);
		return result;
	}

	public static String copyPersonInfoFromOrgToTemp(List<String> personIDs,String templateID){
		String result = "添加成功！";
		String ids = personIDs.toString().replaceAll(" ","").replaceAll(",", "','");
		ids = ids.substring(1, ids.length()-1);
		HashMap<String, String> sqlMap = new HashMap<String, String>();
		String sql = "select count(t.sid) from sa_opperson t where t.sID in('"+ids+"') and (t.smobilephone is null or exists(select 1 from B_receivePersonTemplate b" +
						" where b.fPersonID=t.sID and b.fTemplateID='"+templateID+"' and b.fPhone=t.smobilephone))";
		sqlMap.put(DatabaseProduct.DEFAULT.name(), sql);
		Table tab = SQL.select(sqlMap, null, DATAMODEL);
		int count = Integer.parseInt(String.valueOf(tab.iterator().next().getValue(0)));
		if(count>0)
			result = "添加"+personIDs.size()+"个人员，其中"+count+"个未登记手机号或已添加至列表中！";
		sql = "insert into B_receivePersonTemplate r select sys_guid(),0,t.sid,t.sname,t.smobilephone,'"+templateID+"' from sa_opperson t" +
				" where t.sID in('"+ids+"') and t.smobilephone is not null and not exists(select 1 from B_receivePersonTemplate b" +
						" where b.fPersonID=t.sID and b.fTemplateID='"+templateID+"' and b.fPhone=t.smobilephone)";
		sqlMap.clear();
		sqlMap.put(DatabaseProduct.DEFAULT.name(), sql);
		SQL.executeUpdate(sqlMap, null, DATAMODEL);
		return result;
	}

	public static String copyPersonInfoFromTempToRec(String templateID,String smsID){
		String result = "添加成功！";
		HashMap<String, String> sqlMap = new HashMap<String, String>();
		String sql = "select count(t.fid) from B_receivePersonTemplate t where t.fTemplateID ='" + templateID + "' and (t.fPhone is null or exists(select 1 from B_smsReceivePerson b" +
				" where b.fPersonName=t.fPersonName and b.fSMSID='" + smsID + "' and b.fPhone=t.fPhone))";
		sqlMap.put(DatabaseProduct.DEFAULT.name(), sql);
		Table tab = SQL.select(sqlMap, null, DATAMODEL);
		int count = Integer.parseInt(String.valueOf(tab.iterator().next().getValue(0)));
		if (count > 0)
			result = "其中" + count + "个已添加至列表中！";
		sql = "insert into B_smsReceivePerson r select sys_guid(),0,t.fPersonID,t.fPersonName,t.fPhone,'"+smsID+"' from B_receivePersonTemplate t" +
		" where t.fTemplateID  ='" + templateID + "' and t.fPhone is not null and not exists(select 1 from B_smsReceivePerson b" +
				" where b.fPersonName=t.fPersonName and b.fSMSID='" + smsID + "' and b.fPhone=t.fPhone)";
		System.out.println(sql);
		sqlMap.clear();
		sqlMap.put(DatabaseProduct.DEFAULT.name(), sql);
		SQL.executeUpdate(sqlMap, null, DATAMODEL);
		return result;
	}
}