import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.butone.utils.StringUtils;
import com.justep.common.SystemUtils;
import com.justep.model.ModelUtils;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.BizData;
import com.justep.system.data.BlobUtils;
import com.justep.system.data.KSQL;
import com.justep.system.data.ModifyState;
import com.justep.system.data.Row;
import com.justep.system.data.Table;
import com.justep.system.opm.OrgKinds;
import com.justep.system.opm.OrgUnit;
import com.justep.system.opm.OrgUtils;
import com.justep.system.opm.api.OpmUtils;
import com.justep.system.opm.api.Org;
import com.justep.system.opm.api.OrgHelper;
import com.justep.system.opm.api.OrgKind;
import com.justep.system.util.CommonUtils;
import com.justep.util.Utils;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class PersonInfo {

	public static List<Map<String, Object>> queryPersonSignImageInfo(String personID) {
		List<Map<String, Object>> ret = new ArrayList<Map<String, Object>>();
		Map<String, Object> binds = new HashMap<String, Object>();
		binds.put("pid", personID);
		Table t = KSQL.select("select p,p.fName,p.fImgSize from B_PersonSignImage p where p.fPersonID=:pid and p.fValid='Y' and p.fKind is null", binds, ModelUtils.getModel("/base/personInfo/data"), null);
		if (t.size() > 0) {
			Iterator<Row> i = t.iterator();
			while (i.hasNext()) {
				Map<String, Object> img = new HashMap<String, Object>();
				Row r = i.next();
				img.put("rowid", r.getString("p"));
				img.put("name", r.getString("fName"));
				img.put("size", r.getString("fImgSize"));
				ret.add(img);
			}
		}
		return ret;
	}

	/**
	 * 删除人鱼签名图片前检查使用日志
	 */
	public static void checkLogBeforeDeletePersonSignImage() {
		Table table = (Table) ContextHelper.getActionContext().getParameter("table");
		String concept = (String) ContextHelper.getActionContext().getParameter("concept");
		if (table.size() > 0) {
			Map<String, Object> variables = new HashMap<String, Object>();
			Iterator<Row> i = table.iterator();
			while (i.hasNext()) {
				Row r = i.next();
				if (ModifyState.DELETE.equals(r.getState())) {
					variables.put("imageID", r.getString(concept));
					Table logTable = BizData.query("B_SignImageUseLog", "B", "B.*", "B_SignImageUseLog B", "B.fSignImageID=:imageID", null, null, false, 0, -1, null, null, null, null, variables,
							"/base/personInfo/data", null);
					Utils.check(logTable.size() == 0, "签名图片" + r.getString("fName") + "已被使用，不允许删除，您可以禁用此图片");
				}
			}
		}
	}

	public static void updatePersonGlobalSequenceBeforeSavePerson() {
		Table table = (Table) ContextHelper.getActionContext().getParameter("table");
		if (table.size() > 0) {
			Iterator<Row> i = table.iterator();
			while (i.hasNext()) {
				Row r = i.next();
				if (ModifyState.NEW.equals(r.getState()) || ModifyState.EDIT.equals(r.getState())) {
					String s = getPersonGlobalSequence(r);
					if (!s.equals(r.getString("sGlobalSequence"))) {
						r.setString("sGlobalSequence", s);
					}
				}
			}
		}
	}

	/**
	 * 40位的序列修正
	 */
	private static final String SequenceFix = "0000000000000000000000000000000000000000";

	private static String getPersonGlobalSequence(Row r) {
		String levelCode = r.getString("sLevelCode");
		Integer sequence = r.getInteger("sSequence");
		String personSequenece = SequenceFix + (sequence == null ? "99999" : sequence.toString());
		personSequenece = personSequenece.substring(personSequenece.length() - 5, personSequenece.length());
		String mainOrgID = r.getString("sMainOrgID");
		List<String> exts = new ArrayList<String>();
		exts.add("sSequence");
		Org org = OrgHelper.loadOrg(mainOrgID, exts);
		String deptSequence = org.getSequence();
		deptSequence = SequenceFix + deptSequence.replaceAll("/", "");
		deptSequence = deptSequence.substring(deptSequence.length() - 40, deptSequence.length());
		return (Utils.isEmptyString(levelCode) ? "ZZZZZ" : levelCode) + deptSequence.substring(0, 40) + personSequenece;
	}

	public static void updatePersonGlobalSequenceAfterSortOrgs() {
		List<String> ids = (List<String>) ContextHelper.getActionContext().getParameter("ids");
		String parentID = (String) ContextHelper.getActionContext().getParameter("parentID");
		String str1 = OpmUtils.getMultiValuesEqualCondition("SA_OPOrg", ids.toArray());
		HashMap<String, Object> localHashMap2 = new HashMap();
		localHashMap2.put("parentID", parentID);
		StringBuffer ksql = new StringBuffer(String.format("SELECT p,p.sMainOrgID,p.sLevelCode,p.sSequence,p.sGlobalSequence,p.version,SA_OPOrg.sSequence as psmOrder FROM SA_OPPerson p"
				+ " join SA_OPOrg SA_OPOrg on SA_OPOrg.sPersonID=p and SA_OPOrg.sParent=p.sMainOrgID WHERE SA_OPOrg.sOrgKindID='psm' and (%s) ", new Object[] { str1 }));
		if (Utils.isEmptyString(parentID))
			ksql.append(" AND (SA_OPOrg.sParent IS NULL OR SA_OPOrg.sParent = '') ");
		else
			ksql.append(" AND (SA_OPOrg.sParent = :parentID) ");
		Table table = KSQL.select(ksql.toString(), localHashMap2, "/system/data", null);
		table.getMetaData().setKeyColumn("p");
		table.getMetaData().setStoreByConcepts("SA_OPPerson");
		Iterator<Row> i = table.iterator();
		while (i.hasNext()) {
			Row r = i.next();
			String psmOrder = r.getString("psmOrder");
			psmOrder = psmOrder.substring(psmOrder.lastIndexOf("/") + 1);
			while (psmOrder.charAt(0) == '0' && psmOrder.length() > 1) {
				psmOrder = psmOrder.substring(1);
			}
			r.setInt("sSequence", Integer.parseInt(psmOrder));
			String s = getPersonGlobalSequence(r);
			if (!s.equals(r.getString("sGlobalSequence"))) {
				r.setString("sGlobalSequence", s);
			}
		}
		table.save("/system/data");
	}

	public static String saveDeviceSignImage(String imageStr) throws IOException{
		Map<String, Object> binds = new HashMap<String, Object>();
		String ksql="insert into B_PersonSignImage t (t,t.version,t.fPersonCode,t.fPersonID,t.fLastUpdateTime,t.fValid,t.fName,t.fImgSize,t.fImage,t.fKind)"
			+" values(:fGUID,0,:fPersonCode,:fPersonID,:fLastUpdateTime,'Y','签名设备图片',:fImgSize,:fImage,'签名设备')";
		String guid= StringUtils.getNewGuid32();
		binds.put("fGUID", guid);
		binds.put("fPersonCode", ContextHelper.getPerson().getCode());
		binds.put("fPersonID", ContextHelper.getPerson().getCode());
		byte[] bytes = StringUtils.base64Decode(imageStr);
		InputStream inputStream = new ByteArrayInputStream(bytes);
		binds.put("fImage", inputStream);
		binds.put("fLastUpdateTime",CommonUtils.getCurrentDateTime());
		binds.put("fImgSize", inputStream.available()+"");
		KSQL.executeUpdate(ksql, binds, ModelUtils.getModel("/base/personInfo/data"), null);
		return guid;
	}
}