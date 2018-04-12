import java.sql.Date;
import java.util.Calendar;

import com.butone.workdate.WorkDayCaches;
import com.justep.model.Model;
import com.justep.model.ModelUtils;
import com.justep.system.data.KSQL;
import com.justep.system.data.Row;
import com.justep.system.data.Table;
import com.justep.system.util.CommonUtils;

public class WorkDaysManage {

	public static void initWorkDays(Integer year) {
		if (year == null) {
			year = CommonUtils.getYear(CommonUtils.getCurrentDate());
		}
		Model dataModel = ModelUtils.getModel("/base/system/workDaysManage/data");
		KSQL.executeUpdate(" DELETE FROM B_WorkDaysMang b where b.fYear=" + year, null, dataModel, null);
		Table table = KSQL.select("select b.* from B_WorkDaysMang b where 1=0", null, dataModel, null);
		table.getMetaData().setStoreByConcept("B_WorkDaysMang", true);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.DATE, 1);
		int n = year;
		CommonUtils.getMonth(cal.getTime());
		while (year == n) {
			Row r = table.appendRow();
			int month = cal.get(Calendar.MONTH) + 1;
			int week = cal.get(Calendar.DAY_OF_WEEK);
			int day = cal.get(Calendar.DATE);
			r.setString("b", CommonUtils.createGUID());
			r.setDate("fDate", new Date(cal.getTimeInMillis()));
			r.setInt("fYear", year);
			r.setInt("fMonth", month);
			r.setInt("fDay", day);
			r.setInt("fWeek", week);
			r.setInt("version", 0);
			r.setInt("fKey", 360 * year + 30 * month + day);
			r.setString("fIsWorkDay", (week == 1 || week == 7) ? "否" : "是");
			r.setString("fKind", "无变化");
			cal.add(Calendar.DATE, 1);
			n = cal.get(Calendar.YEAR);
		}
		table.save(dataModel);
	}

	public static void refreshWorkDayCahce(Integer year, Integer month) {
		WorkDayCaches.reloadDatesOfYearMonth(year, month);
	}
}