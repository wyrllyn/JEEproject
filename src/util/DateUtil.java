package util;

import java.util.Calendar;
import java.util.Date;

import model.DateUsed;
import model.Days;

public class DateUtil {

	/**
	 * 
	 * @param date
	 * @return String name of the day of the week.
	 */
	public static String getDayOfTheWeek(Date date) { //TODO: is that actually used anywhere?
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
	
	/**
	 * Constructs a DateUsed object out of a regular {@link Date}
	 * @param date
	 * @return {@link DateUsed}
	 */
	public static DateUsed createDateUsed(Date date) {
		DateUsed dateUsed = new DateUsed();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.get(Calendar.DAY_OF_WEEK);
		
		int hours = c.get(Calendar.HOUR);
		int minutes = c.get(Calendar.MINUTE);
		Days day = convertToDays(c.get(Calendar.DAY_OF_WEEK));
		
		dateUsed.setDay(day);
		dateUsed.setHours(hours);
		dateUsed.setMinutes(minutes);
		return dateUsed;
	}

	/**
	 * Convert a Calendar DAY_OF_WEEK into our own Days.
	 * @param calendarDay
	 * @return {@link Days}
	 * @see Calendar
	 */
	public static Days convertToDays(int calendarDay) {
		switch (calendarDay) {
		case Calendar.MONDAY:
			return Days.LUNDI;
		case Calendar.TUESDAY:
			return Days.MARDI;
		case Calendar.WEDNESDAY:
			return Days.MERCREDI;
		case Calendar.THURSDAY:
			return Days.JEUDI;
		case Calendar.FRIDAY:
			return Days.VENDREDI;
		}
		return null;
	}
}
