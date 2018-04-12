import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.justep.system.context.ContextHelper;
import com.justep.system.data.DatabaseProduct;
import com.justep.system.data.KSQL;
import com.justep.system.data.Row;
import com.justep.system.data.SQL;
import com.justep.system.data.Table;
import com.justep.system.transform.SimpleTransform;
import com.justep.system.util.CommonUtils;

public class NoticeWidget {
	private static final String DATA_MODEL = "/MOA/notice/data";

	public static Document queryNoticeInfo() {
		String currentPersonID = ContextHelper.getPerson().getID();
		String query = "select B_NoticeInfo,B_NoticeInfo.fNoticeTitle,B_NoticeInfo.fPublishTime,B_NoticeInfo.fContactPerson"
				+ " from B_NoticeReceivePerson p join  B_NoticeInfo B_NoticeInfo on p.fNoticeInfoID = B_NoticeInfo " + "where p.fPersonalDel=0 and p.fReadStatus=0 and p.fReceiveID = '"
				+ currentPersonID + "'" + " and B_NoticeInfo.fNoticeStatus = '1' and B_NoticeInfo.fNoticeSendStatus=1" + " order by B_NoticeInfo.fPublishTime desc limit 0,6 ";
		return toDOM(KSQL.select(query, null, DATA_MODEL, null));
	}

	private static Document toDOM(Table table) {
		Document result = DocumentHelper.createDocument();
		Element tasks = result.addElement("noticeInfos");
		if (table != null) {
			for (Iterator<Row> it = table.iterator(); it.hasNext();) {
				Row r = it.next();
				Element task = tasks.addElement("noticeInfo");
				task.addAttribute("id", r.getString("B_NoticeInfo"));
				task.addElement("fID").addText(getValue(r.getString("B_NoticeInfo"), ""));
				task.addElement("fNoticeTitle").addText(getValue(r.getString("fNoticeTitle"), ""));
				task.addElement("fContactPerson").addText(getValue(r.getString("fContactPerson"), ""));
				task.addElement("fPublishTime").addText(getValue(SimpleTransform.transToString(r.getDateTime("fPublishTime")), ""));
			}

		}

		return result;
	}

	private static String getValue(String obj, String defaultValue) {
		if (obj == null) {
			return defaultValue;
		} else {
			return obj;
		}
	}

	/**
	 * 查询个人日程
	 * 
	 * @throws ParseException
	 */
	public static List<HashMap<String, String>> queryScheduleInfo() throws ParseException {
		List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String currentPersonID = ContextHelper.getPerson().getID();
		HashMap<String, String> sqlMap = new HashMap<String, String>();
		String sql = "select a.fID,a.fTitle,to_char(a.fBeginTime,'yyyy-MM-dd hh24:mm:ss'),a.fBeginTimePart,to_char(a.fEndTime,'yyyy-MM-dd hh24:mm:ss'),a.fEndTimePart from OA_SD_Schedule a"
				+ " where a.fID in(select b.fMasterID from OA_SD_Executor b where b.fExecutorID='" + currentPersonID + "')"
				+ "and (to_char(sysdate, 'yyyy-mm-dd') between to_char(a.fBeginTime, 'yyyy-mm-dd') and to_char(a.fEndTime, 'yyyy-mm-dd')"
				+ " or to_char(sysdate + 1, 'yyyy-mm-dd') between to_char(a.fBeginTime, 'yyyy-mm-dd') and to_char(a.fEndTime, 'yyyy-mm-dd')"
				+ " or to_char(sysdate + 2, 'yyyy-mm-dd') between to_char(a.fBeginTime, 'yyyy-mm-dd') and to_char(a.fEndTime, 'yyyy-mm-dd'))" + " order by a.fBeginTime,a.fEndTime desc";
		sqlMap.put(DatabaseProduct.DEFAULT.name(), sql);
		Table tab = SQL.select(sqlMap, null, "/MOA/schedule/data");
		Iterator<Row> it = tab.iterator();
		Calendar cal = Calendar.getInstance();
		Date nowDate = CommonUtils.getCurrentDate();// 今天
		cal.setTime(nowDate);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date date1 = cal.getTime();// 明天
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date date2 = cal.getTime();// 后天
		while (it.hasNext()) {
			Row r = it.next();
			String kind = "0";
			Date startTime = dateFormat.parse(r.getString(2));
			Date endTime = dateFormat.parse(r.getString(4));
			String fID = r.getString(0);
			String title = r.getString(1);
			HashMap<String, String> map = new HashMap<String, String>();

			if (startTime.compareTo(nowDate) == 0) {
				kind = "0";
				map.clear();
				map.put("id", fID);
				map.put("kind", kind);
				map.put("title", title);
				map.put("startTime", dateFormat.format(startTime));
				map.put("endTime", dateFormat.format(endTime));
				result.add(map);
			}
			if (startTime.compareTo(date1) == 0) {
				kind = "1";
				map.clear();
				map.put("id", fID);
				map.put("kind", kind);
				map.put("title", title);
				map.put("startTime", dateFormat.format(startTime));
				map.put("endTime", dateFormat.format(endTime));
				result.add(map);
			}
			if (startTime.compareTo(date2) == 0) {
				kind = "2";
				map.clear();
				map.put("id", fID);
				map.put("kind", kind);
				map.put("title", title);
				map.put("startTime", dateFormat.format(startTime));
				map.put("endTime", dateFormat.format(endTime));
				result.add(map);
			}
		}
		return result;
	}
}