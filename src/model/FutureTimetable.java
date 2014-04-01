package model;

import java.util.List;

public class FutureTimetable {
	private String groupName;
	private List<Slot> toSet;
	
	public FutureTimetable() {
		
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<Slot> getToSet() {
		return toSet;
	}

	public void setToSet(List<Slot> toSet) {
		this.toSet = toSet;
	}
	
	

}
