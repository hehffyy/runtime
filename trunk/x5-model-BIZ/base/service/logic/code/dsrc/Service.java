import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.justep.exception.BusinessException;
import com.justep.system.data.KSQL;
import com.justep.system.data.Row;
import com.justep.system.data.Table;
import com.justep.system.opm.OrgUtils;
import com.justep.system.opm.api.Person;
import com.justep.system.opm.api.PersonHelper;
import com.justep.system.process.ProcessUtils;
import com.justep.util.Utils;

public class Service {

	private static String getIDByMail(String mail) {
		String id = null;
		if (Utils.isNotEmptyString(mail) && mail.contains("@")) {
			String query = "select p from SA_OPPerson p where UPPER(p.sMail)=:mail";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("mail", mail.toUpperCase());
			Table table = KSQL.select(query, params, ProcessUtils.CORE_MODEL, null);
			Iterator<Row> it = table.iterator();
			if (it.hasNext()) {
				Row r = it.next();
				id = r.getString("p");
			}
		} else {
			String query = "select p from SA_OPPerson p where UPPER(p.sCode)=:code or UPPER(p.sLoginName)=:code";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("code", mail.toUpperCase());
			Table table = KSQL.select(query, params, ProcessUtils.CORE_MODEL, null);
			Iterator<Row> it = table.iterator();
			if (it.hasNext()) {
				Row r = it.next();
				id = r.getString("p");
			}
		}
		return id;
	}

	public static boolean checkPassword(String name, String pwd) {
		String id = getIDByMail(name);
		if (id == null) {
			throw new BusinessException("用户账号不存在");
		}
		List<String> exts = new ArrayList<String>();
		exts.add("sPassword");
		Person person = PersonHelper.loadPerson(id, exts);
		String p1 = person.getPassword();
		String p2 = OrgUtils.encryptPassword(pwd);
		return p1.equals(p2);
	}
}