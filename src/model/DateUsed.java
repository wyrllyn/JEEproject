package model;

import java.util.Calendar;
import java.util.Date;

public class DateUsed implements Comparable<DateUsed> {
	private int hours;
	private int minutes;
	private Days day;
	/**
	 * Default = Monday 6th January 2014
	 */
	private Date date;
	
	private static final int DEFAULT_DAY = 6;
	
	/**
	 * Constructs a DateUsed set to Monday, 9h30.
	 */
	public DateUsed() {
		Calendar c = Calendar.getInstance();
		c.set(2014, Calendar.JANUARY, DEFAULT_DAY, 0, 0, 0);
		date = c.getTime();
		this.hours = 9;
		this.minutes = 30;
		this.day = Days.LUNDI;
	}	
	
	
	public DateUsed(Days beginday, int beginhours, int beginminutes) {
		this();
		setDay(beginday);
		setHours(beginhours);
		setMinutes(beginminutes);
	}



	public DateUsed calcutateEnd(int duration){
		DateUsed end = new DateUsed();
		int min = this.minutes + duration;
		int h = this.hours;
		Days dayE = this.day;
		if (min >= 60) {
			h += min / 60;
			min = min % 60;
			
			if(h == 23){
				h = 0;
				increment(dayE);			
			}
			
		}
		end.setDay(dayE);
		end.setHours(h);
		end.setMinutes(min);
		return end;
	}
	
	public Days increment(Days day){
		switch (day) {
		case LUNDI:
			return Days.MARDI;
		case MARDI:
			return Days.MERCREDI;
		case MERCREDI:
			return Days.JEUDI;
		case JEUDI:
			return Days.VENDREDI;
		case VENDREDI:
			return Days.LUNDI;
		}
		return day;
	}
	
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	public int getMinutes() {
		return minutes;
	}
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	public Days getDay() {
		return day;
	}
	public void setDay(Days day) {
		this.day = day;
	}
	
	/**
	 * is the present Date + duration included into second ? 
	 * @param fDuration
	 * @param second
	 * @param sDuration
	 * @return
	 */
	public boolean includedInto(int fDuration, DateUsed second, int sDuration){
		DateUsed currentEnd = this.calcutateEnd(fDuration);
		DateUsed sEnd = second.calcutateEnd(sDuration);
		
		if (this.compareTo(second) == 0 || currentEnd.compareTo(sEnd) == 0){
			return true;
		}
		if (this.getDay() != second.getDay()){
			return false;
		}
		if (this.getHours() != second.getHours() || this.getHours() != sEnd.getHours()){
			if(this.getHours() <= sEnd.getHours() && this.getHours() >= second.getHours()){
				return true;
			}
		}
		if (currentEnd.getHours() != second.getHours() || currentEnd.getHours() != sEnd.getHours()){
			if(currentEnd.getHours() <= sEnd.getHours() && currentEnd.getHours() >= second.getHours()){
				return true;
			}
		}
		if (this.getHours() == second.getHours() || this.getHours() == sEnd.getHours()){
			if (this.getMinutes() >= second.getMinutes() && this.getMinutes() <= sEnd.getMinutes()){
				return true;
			}
		}
		if (currentEnd.getHours() == second.getHours() || currentEnd.getHours() == sEnd.getHours()){
			if (currentEnd.getMinutes() >= second.getMinutes() && currentEnd.getMinutes() <= sEnd.getMinutes()){
				return true;
			}
		}
		
		
		return false;
	}




	@Override
	public int compareTo(DateUsed secondDate) {
		if (secondDate.getDay() == this.getDay()){
			if (secondDate.getHours() == this.getHours()
					&& secondDate.getMinutes() == this.getMinutes()){
				return 0;
			}
		}
		return -1;
	}

	@Override
	public String toString() {
		return "DateUsed [hours=" + hours + ", minutes=" + minutes + ", day="
				+ day + "]";
	}
	
	/**
	 * Synchronizes the Date with the DateUsed.
	 */
	public void synchronizeDate() {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int dow = 0;
		switch (day) {
		case LUNDI:
			dow = 0;
			break;
		case MARDI:
			dow = 1;
			break;
		case MERCREDI:
			dow = 2;
			break;
		case JEUDI:
			dow = 3;
			break;
		case VENDREDI:
			dow = 4;
			break;
		}
		c.set(2014, Calendar.JANUARY, DEFAULT_DAY + dow, hours, minutes);
		date = c.getTime();
	}

	/**
	 * Recommendation: use the synchronizeDate() method before getting the Date.
	 * @return Date object associated with this DateUsed.
	 */
	public Date getDate() {
		return date;
	}
}
