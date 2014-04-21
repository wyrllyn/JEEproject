package util;

import java.util.Calendar;
import java.util.Date;

import model.DateUsed;
import model.Days;

public class DateUtils {
	
	/**
	 * Constructs a Date object out of a {@link DateUsed}
	 * @param dateUsed
	 * @return {@link Date}
	 */
	public static Date createDate(DateUsed dateUsed) {
		dateUsed.synchronizeDate();
		return new Date(dateUsed.getDate().getTime());
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
		
		int hours = c.get(Calendar.HOUR_OF_DAY);
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
	
	public static Days getDay(String day) {
		day = day.toUpperCase();
		switch (day) {
		case "MONDAY":
		case "LUNDI":
			return Days.LUNDI;
		case "TUESDAY":
		case "MARDI":
			return Days.MARDI;
		case "WEDNESDAY":
		case "MERCREDI":
			return Days.MERCREDI;
		case "THURSDAY":
		case "JEUDI":
			return Days.JEUDI;
		case "FRIDAY":
		case "VENDREDI":
			return Days.VENDREDI;
		}
		return null;
	}
}
