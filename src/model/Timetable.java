package model;

import java.util.Map;

public class Timetable {
	private Map<Slot, Room> timetable;
	private String groupName;
	
	public Timetable() {
		
	}
	
	public Map<Slot, Room> getTimetable() {
		return timetable;
	}

	public void setTimetable(Map<Slot, Room> timetable) {
		this.timetable = timetable;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Override
	public String toString() {
		return "Timetable [timetable=" + timetable + ", groupName=" + groupName
				+ "]";
	}
	
}
