package fsexchange.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class TimeUtil {
	public static XMLGregorianCalendar convertToXMLGregorianCalendar(Date date) {

		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		XMLGregorianCalendar gc = null;
		try {
			gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return gc;
	}

	public static Date convertToDate(XMLGregorianCalendar cal) {
		GregorianCalendar ca = cal.toGregorianCalendar();
		return ca.getTime();
	}

	public static String dateTimeToString(Date date, String format) {
		if (date == null) {
			return null;
		}
		String f = format;
		if (f == null) {
			f = "yyyy-MM-dd hh:mm:ss";
		}
		DateFormat df = new SimpleDateFormat(f);
		return df.format(date);
	}

	public static String dateTimeToString(Date date) {
		return dateTimeToString(date, null);
	}

	public static Date stringToDateTime(String str) throws ParseException {
		if (str == null) {
			return null;
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return df.parse(str);
	}

	public static int yearOf(Date date) {
		Calendar now = GregorianCalendar.getInstance();
		now.setTimeInMillis(date.getTime());
		return now.get(Calendar.YEAR);
	}

	public static int monthOf(Date date) {
		Calendar now = GregorianCalendar.getInstance();
		now.setTimeInMillis(date.getTime());
		return now.get(Calendar.MONTH) + 1;
	}

	public static int dayOf(Date date) {
		Calendar now = GregorianCalendar.getInstance();
		now.setTimeInMillis(date.getTime());
		return now.get(Calendar.DAY_OF_YEAR);
	}
}
