package model;

enum Days {
	LUNDI,
	MARDI,
	MERCREDI,
	JEUDI,
	VENDREDI
};

public class DateUsed implements Comparable {
	private int hours;
	private int minutes;
	private Days day;
	
	public DateUsed() {
		
	}	
	
	public DateUsed calcutateEnd(int duration){
		DateUsed end = new DateUsed();
		int min = this.minutes + duration;
		int h = this.hours;
		Days dayE = this.day;
		if (min >= 60) {
			min -= 60;
			if(h == 23){
				h = 0;
				increment(dayE);			
			}
			else {
				h++;
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
	
	//is the present Date + duration included into second ? 
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
	public int compareTo(Object arg0) {
		DateUsed secondDate = (DateUsed) arg0;
		if (secondDate.getDay() == this.getDay()){
			if (secondDate.getHours() == this.getHours()
					&& secondDate.getMinutes() == this.getMinutes()){
				return 0;
			}
		}
		// TODO later before or after
		return -1;
	}
	
	

}
