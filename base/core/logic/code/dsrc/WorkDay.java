import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.butone.workdate.WorkDayUtils;
import com.justep.exception.BusinessException;
import com.justep.system.util.CommonUtils;

public class WorkDay {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			System.out.println(sdf.parse("2016-09-18 01:00:00"));
		} catch (Exception e) {
			throw new BusinessException("日期格式错误，应为yyyy-MM-dd HH:mm:ss");
		}
	}

	/**
	 * 得到days个工作日后的工作日
	 * @param start 开始日期
	 * @param days 工作日
	 * @param includeToDay 是否包含开始日期(默认为否)
	 * @return days个工作日后的第一个工作日
	 * @throws ParseException 
	 */
	public static Date getDateAfterWorkDays(Object start, Object days, boolean includeToDay) {
		if (days == null || start == null)
			return null;
		Date realStart;
		if (start instanceof Date) {
			realStart = (Date) start;
		} else if (start instanceof String) {
			SimpleDateFormat sdf;
			if (start.toString().length() > 10)
				sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			else
				sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				realStart = sdf.parse((String) start);
			} catch (Exception e) {
				throw new BusinessException("日期格式错误，应为yyyy-MM-dd或者yyyy-MM-dd HH:mm:ss");
			}
		} else {
			throw new BusinessException("不支持的开始时间(start)数据类型:" + start.getClass().getName());
		}
		return WorkDayUtils.getDateAfterWorkDays(realStart, new BigDecimal(days.toString()), includeToDay);
	}

	/**
	 * 得到days个工作日后的工作日
	 * @param start 开始日期
	 * @param days 工作日
	 * @param includeToDay 是否包含开始日期(默认为否)
	 * @return days个工作日后的第一个工作日
	 * @throws ParseException 
	 */
	public static Date getDateAfterNatureDays(Object start, Object days, boolean includeToDay) {
		if (days == null || start == null)
			return null;
		Date realStart;
		if (start instanceof Date) {
			realStart = (Date) start;
		} else if (start instanceof String) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				realStart = sdf.parse((String) start);
			} catch (Exception e) {
				throw new BusinessException("日期格式错误，应为yyyy-MM-dd HH:mm:ss");
			}
		} else {
			throw new BusinessException("不支持的开始时间(start)数据类型:" + start.getClass().getName());
		}
		return WorkDayUtils.getDateAfterNatureDays(realStart, new BigDecimal(days.toString()), includeToDay);
	}

	/**
	 * 计算两个日期之间的工作日数，如果开始日期为非工作日，实际开始日期应为之后第一个工作日
	 * @param startDate 开始日期
	 * @param endDate   结束日期
	 * @return Integer
	 */
	public static Integer calcWorkDaysBetween(Date startDate, Date endDate) {
		Long days = WorkDayUtils.calcWorkDaysBetween(startDate, endDate);
		return days != null ? days.intValue() : null;
	}

	/**
	 * 获得工作日耗时
	 * @param start)Date
	 * @param endDate
	 * @param includeStart
	 * @param half
	 * @return
	 */
	public static Float getLostWorkDays(Date startDate, Date endDate, boolean includeStart, boolean half) {
		Double ret = WorkDayUtils.calcLostDaysBetween(startDate, endDate, "工作日", includeStart, half);
		if (ret == null)
			return null;
		else
			return ret.floatValue();
	}

	/**
	 * 获得自然日耗时
	 * @param startDate
	 * @param endDate
	 * @param includeStart
	 * @param half
	 * @return
	 */
	public static Float getLostNatureDays(Date startDate, Date endDate, boolean includeStart, boolean half) {
		Double ret = WorkDayUtils.calcLostDaysBetween(startDate, endDate, "自然日", includeStart, half);
		if (ret == null)
			return null;
		else
			return ret.floatValue();
	}

	/**
	 * 日期转换
	 * @param obj
	 * @return
	 */
	public static java.sql.Date toDateEx(Object obj) {
		if (obj == null)
			return null;
		else if (obj instanceof String) {
			String dateStr = obj.toString();
			if (dateStr.contains("/"))
				dateStr = dateStr.replaceAll("/", "-");
			else if (dateStr.contains("\\"))
				dateStr = dateStr.replaceAll("\\", "-");
			else if (dateStr.contains("."))
				dateStr = dateStr.replaceAll(".", "-");
			else if (dateStr.contains("年"))
				dateStr = dateStr.replace("年", "-").replace("月", "-").replace("日", "-");
			return CommonUtils.toDate(dateStr);
		} else if (obj instanceof Integer) {
			Integer intVal = (Integer) obj;
			Date result = new Date(intVal);
			return CommonUtils.toDate(result);
		} else
			return CommonUtils.toDate(obj);
	}

	/**
	 * 时间转换
	 * @param obj
	 * @return
	 */
	public static Timestamp toDateTimeEx(Object obj) {
		if (obj == null)
			return null;
		else
			return CommonUtils.toDateTime(obj);
	}

}