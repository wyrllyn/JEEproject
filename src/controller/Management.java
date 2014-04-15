package controller;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import model.CompleteHours;
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
		}
		else {
			
		}
		
		return d;		
	}
	
	//Rename this method / used to know if we can set a duration into a day
	private CompleteHours possible(Days day, int duration, Timetable t){
		// invalid hour if day is full 
		CompleteHours toReturn = new CompleteHours();
		toReturn.setHours(-1);
		toReturn.setMinutes(-1);
		// to mark which hours are occupied
		Map<CompleteHours, CompleteHours> occupied =
				new TreeMap<CompleteHours, CompleteHours>(occupiedMap(day, t));
		
		boolean occ = false;
		DateUsed du = new DateUsed();
		du.setDay(day);
		
		DateUsed ap = new DateUsed();
		
		if(occupied.size() == 0) {
			toReturn.setHours(8);
			toReturn.setMinutes(0);
			return toReturn;
		}
		else {
			// for each valid hours
			for (int h = 8; h <= 18; h++) {
				// boolean if this hours is occupied
				occ = false;
				// if hour is 12 or 13 lunch
				if (!(h == 12) && !(h == 13)) {
					// for each classes already on the timetable
					for (CompleteHours key : occupied.keySet()){
						// if hour matches and end of class is not at the same hour, break & occupied
						if (key.getHours() == h && !(occupied.get(key).getHours() == h)){
							if(key.getMinutes() <= 30) {
								occ = true;
								break;
							}
						}
						// and class ends before the end of hour h
						else if (occupied.get(key).getHours() == h){
							int m = occupied.get(key).getMinutes();
							du.setHours(h);
							du.setMinutes(m);
							// 10 minutes of break, ap will be used							
							ap = du.calcutateEnd(10);
							break;
						}
					}
					// if not occupied
					if (!occ){
						if (du.getHours() != h){
							// if not occupied and du.getHours !=h then h is totally free
							ap.setHours(h);
							ap.setMinutes(0);
						}
						
						//comparaison TODO
						// with t and ap I guess
						//ap.calcutateEnd(duration);
					}
				}
			}
		}		
		return toReturn;
	}

	private Map<CompleteHours, CompleteHours> occupiedMap(Days day, Timetable t) {
		Map<CompleteHours, CompleteHours> occ = new TreeMap<>();
		CompleteHours beg = new CompleteHours();
		CompleteHours end = new CompleteHours();
		for (Slot key : t.getTimetable().keySet()) {
			if(key.getBeginning().getDay() == day) {
				beg.setHours(key.getBeginning().getHours());
				beg.setMinutes(key.getBeginning().getMinutes());
				end.setHours(key.getBeginning().calcutateEnd(key.getDuration()).getHours());
				end.setMinutes(key.getBeginning().calcutateEnd(key.getDuration()).getMinutes());

				occ.put(beg, end);
			}
		}
		return occ;
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
