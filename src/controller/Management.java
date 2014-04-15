package controller;

import java.util.List;
import java.util.Map;

import model.DateUsed;
import model.Days;
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
		toAdd.setGroupName(future.getGroupName());
		int size = future.getToSet().size();

		// creation => to 8:00 to 18:30 // 10minutes of break between classes
		// begin with LUNDI, check all of the hours. IF already used => MARDI etc.
		
		while(size > 0){
			//loop to choose the biggest class (in term of duration)
			
			//use DateUsed for a date
			//after a date is chosen => room with method			
			
			//if an element of future is added => size --	
			//remove it from future
		}
		
		
		timetables.add(toAdd);		
	}
	
	private DateUsed futureDate(Timetable t, Slot s) {
		//check for teacher here ?
		DateUsed d = new DateUsed();
		if (t.getTimetable().size() == 0){
			d.setDay(Days.LUNDI);
			d.setHours(8);
			d.setMinutes(0);
		}
		else {
			
		}
		
		return d;		
	}
	
	//Rename this method / used to know if we can set a duration into a day
	private boolean possible(Days day, int duration, Timetable t){
		//find how to mark a slot as occupied
		// don't forget pauses => must have 10 minutes between classes and 1:30 around 12
		Map<Integer[][], Integer[][]> occupied;
		for (Slot key : t.getTimetable().keySet()) {
			if(key.getBeginning().getDay() == day) {
				
			}
		}
		
		return true;
	}
	
	
	
	// called into createTimeTable	
	private boolean isThisRoomEmpty(Room room, int duration, DateUsed beginning){
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
	
	// called into createTimeTable
	
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
	
	//called into isThisPersonAvailable() & isThisRoomEmpty()
	private boolean conflictOfTime (Slot slot, int duration, DateUsed beginning) {
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
