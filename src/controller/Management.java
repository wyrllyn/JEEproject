package controller;

import java.util.List;

import model.FutureTimetable;
import model.Slot;
import model.Timetable;

public class Management {
	private List<Timetable> timetables;
	private List<FutureTimetable> toSet; 

	public Management() {
		
	}

	public List<Timetable> getTimetables() {
		return timetables;
	}

	public void setTimetables(List<Timetable> timetables) {
		this.timetables = timetables;
	}

	public List<FutureTimetable> getToSet() {
		return toSet;
	}

	public void setToSet(List<FutureTimetable> toSet) {
		this.toSet = toSet;
	}
	
	public void createTimeTable(FutureTimetable future){
		
	}
	
	public void removeSlot(Timetable table, Slot slotToRemove){
		
	}
	
	//TODO: modification method but what parameters should we use ?

}
