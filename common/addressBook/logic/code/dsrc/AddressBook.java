import java.util.*;

import com.justep.system.data.*;

public class AddressBook {

	private static final String dataModel = "/system/data";
	
	/**
	 * 获取通讯录信息
	 * @author pu
	 * @return result
	 */
	public static ArrayList<Object> getAddressBookList() {
		ArrayList<Object> result = new ArrayList<Object>();
		// 第一步获取一级单位名称
		String sql = "select t,t.sName,t.sFCode from SA_OPOrg t where t.sParent is null and t.sValidState<>-1 and t.sOrgKindID <> 'pos' and t.sOrgKindID <> 'psm' and t.sName<>'起步软件' order by t.sSequence";
		Table tab = KSQL.select(sql, null, dataModel, null);
		Iterator<Row> it = tab.iterator();
		while(it.hasNext()){
			Row r = it.next();
			String sID = r.getString("t");
			String orgName = r.getString("sName");
			Map<String,Object> orgMap = new HashMap<String,Object>();
			orgMap.put("sID", sID);
			orgMap.put("orgName", orgName);
			orgMap.put("personInfo", getPersonInfoList(sID));
			sql = "select t,t.sName,t.sFCode from SA_OPOrg t where t.sOrgKindID <> 'pos' and t.sOrgKindID <> 'psm' and t.sValidState<>-1 and t.sParent='"+sID+"' order by t.sSequence asc";
			Table tabDpt = KSQL.select(sql, null, dataModel, null);
			Iterator<Row> itDpt = tabDpt.iterator();
			ArrayList<Object> resultDpt = new ArrayList<Object>();
			while(itDpt.hasNext()){
				Row rDpt = itDpt.next();
				sID = rDpt.getString("t");
				orgName = rDpt.getString("sName");
				Map<String,Object> dptMap = new HashMap<String,Object>();
				dptMap.put("sID", sID);
				dptMap.put("orgName", orgName);
				dptMap.put("personInfo", getPersonInfoList(sID));
				sql = "select t,t.sName,t.sFCode from SA_OPOrg t where t.sOrgKindID <> 'pos' and t.sOrgKindID <> 'psm' and t.sValidState<>-1 and t.sParent='"+sID+"' order by t.sSequence asc";
				Table tabDpt2 = KSQL.select(sql, null, dataModel, null);
				Iterator<Row> itDpt2 = tabDpt2.iterator();
				ArrayList<Object> resultDpt2 = new ArrayList<Object>();
				while(itDpt2.hasNext()){
					Row rDpt2 = itDpt2.next();
					sID = rDpt2.getString("t");
					orgName = rDpt2.getString("sName");
					Map<String,Object> dptMap2 = new HashMap<String,Object>();
					dptMap2.put("sID", sID);
					dptMap2.put("orgName", orgName);
					dptMap2.put("personInfo", getPersonInfoList(sID));
					resultDpt2.add(dptMap2);
					dptMap.put("deptInfo", resultDpt2);
				}
				resultDpt.add(dptMap);
				orgMap.put("deptInfo", resultDpt);
			}
			result.add(orgMap);
		}
		return result;
	}
	
	private static ArrayList<Object> getPersonInfoList(String sID){
		ArrayList<Object> result = new ArrayList<Object>();
		String sql = "select psm.sName,psm.sTitle,psm.sMobilePhone,org.sName,org.sFName,org.sFID from SA_OPPerson psm optional join SA_OPOrg org on psm.sMainOrgID=org where psm.sValidState<>-1 and org.sValidState<>-1 and org='"+sID+"' order by org.sSequence asc,psm.sNumb asc";
		Table tab = KSQL.select(sql, null, dataModel, null);
		Iterator<Row> it = tab.iterator();
		while(it.hasNext()){
			Row r = it.next();
			String sName = r.getString(0);
			String sTitle = r.getString(1);
			String sMobilePhone = r.getString(2);
			HashMap<String, Object> personInfo = new HashMap<String, Object>();
			personInfo.put("sName", sName);
			personInfo.put("sTitle", sTitle);
			personInfo.put("sMobilePhone", sMobilePhone);
			result.add(personInfo);
		}
		return result;
	}

	public static Table queryPersonInfo(List range,String concept,String select,String from,String aggregate,String dataModel,String fnModel,String condition,Boolean distinct,String idColumn,String filter,Integer limit,Integer offset,String columns,String orderBy,String aggregateColumns,Map variables){
		Table ret = BizData.query(concept, idColumn, select, from, condition, range, filter, distinct, offset, limit, columns, orderBy, aggregate,
				aggregateColumns, variables, dataModel, fnModel);
		return ret;
	}
}