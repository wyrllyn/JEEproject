package model;

import java.util.Date;

public class Slot {
	private Date beginning;
	private Date end;
	
	public Slot() {
		
	}
	
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public Date getBeginning() {
		return beginning;
	}
	public void setBeginning(Date beginning) {
		this.beginning = beginning;
	}
}
