package controller;

import java.util.List;
import java.util.Map;

import model.DateUsed;
import model.FutureTimetable;
import model.Person;
import model.Room;
import model.Slot;
import model.Timetable;

public class Management {

	private List<Timetable> timetables;
	private List<FutureTimetable> toSet; 
	private List<Room> rooms;

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
	
	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public void createTimeTable(FutureTimetable future){
		Timetable toAdd = new Timetable();	
		// creation => to 8:00 to 18:30 // 10minutes of break between classes
		// begin with LUNDI, check all of the hours. IF already used => MARDI etc.

		
	/*	if (totalTime > 20){
			
		}*/
		
		
		timetables.add(toAdd);		
	}
	
	// know if a room is available 
	
	public boolean isThisRoomEmpty(Room room, int duration, DateUsed beginning){
		for (int i = 0; i < timetables.size() ; i++) {
			Map<Slot,Room> temp = timetables.get(i).getTimetable();
			for (Slot key : timetables.get(i).getTimetable().keySet()){
				if(temp.get(key) == room){
					// compare duration and beginning => if conflict return false else nothing
					if (conflictOfTime(key, duration, beginning)){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public boolean isThisPersonAvailable(Person p, int duration, DateUsed beginning) {
		for (int i = 0; i < timetables.size() ; i++) {
			Map<Slot,Room> temp = timetables.get(i).getTimetable();
			for (Slot key : timetables.get(i).getTimetable().keySet()){
				if(key.getTeacher().equals(p)){
					// compare duration and beginning => if conflict return false else nothing
					if (conflictOfTime(key, duration, beginning)){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public boolean conflictOfTime (Slot slot, int duration, DateUsed beginning) {
		//check if a slot is included into another
		if(slot.getBeginning().includedInto(slot.getDuration(), beginning, duration) ||
				beginning.includedInto(duration, slot.getBeginning(), slot.getDuration())){
			return true;
		}
		return false;
	}
	
	public void removeSlot(Timetable table, Slot slotToRemove){
		
	}

}
