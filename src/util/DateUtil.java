package util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

<<<<<<< HEAD
	/**
	 * 
	 * @param date
	 * @return String name of the day of the week.
	 */
=======
>>>>>>> a5e44f369724e5ec09ebbbee423855a5e8acc672
	public static String getDayOfTheWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day = c.get(Calendar.DAY_OF_WEEK);
		switch (day) {
		case Calendar.MONDAY:
			return "Monday";
		case Calendar.TUESDAY:
			return "Tuesday";
		case Calendar.WEDNESDAY:
			return "Wednesday";
		case Calendar.THURSDAY:
			return "Thursday";
		case Calendar.FRIDAY:
			return "Friday";
		case Calendar.SATURDAY:
			return "Saturday";
		}
		// Note: sunday is an error
		return "Error";
	}
	
}
