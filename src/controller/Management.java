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
		// will be added into timetables
		Timetable toAdd = new Timetable();
		toAdd.setGroupName(future.getGroupName());
		Map<Slot,Room> construction = new TreeMap<>();
		
		// to avoid error
		int size = future.getToSet().size();
		
		//to test slot and rooms
		Slot tempSlot = new Slot();
		Room thisRoom = new Room();
		
		DateUsed temp = new DateUsed();
		
		while(size > 0){
			//loop to choose the biggest class (in term of duration)
			int biggest = 0;
			int thisOne = -1;
			for (int i = 0; i < future.getToSet().size(); i++){
				if (future.getToSet().get(i).getDuration() > biggest) {
					biggest = future.getToSet().get(i).getDuration();
					thisOne = i;
				}
			}
			
			//try a date
			tempSlot = future.getToSet().get(thisOne);
			temp = futureDate(toAdd, tempSlot);	
			//check if teacher available			
			if (!isThisPersonAvailable(tempSlot.getTeacher(),
					tempSlot.getDuration(), temp)){
				for (int i = 0; i < future.getToSet().size(); i++ ){
					tempSlot = future.getToSet().get(thisOne);
					temp = futureDate(toAdd, tempSlot);
					if (isThisPersonAvailable(tempSlot.getTeacher(),
					tempSlot.getDuration(), temp)){
						break;
					}
				}
			}
			//after a date is chosen => room with method
			for (int i = 0; i < rooms.size() ; i++){
				if (isThisRoomEmpty(rooms.get(i), tempSlot.getDuration(), temp)){
					thisRoom = rooms.get(i);
					break;
				}
			}
			//add this element
			tempSlot.setBeginning(temp);
			construction.put(tempSlot, thisRoom);
			//of future is added => size --	
			//remove it from future
			size--;
			future.getToSet().remove(thisOne);

		}
		
		toAdd.setTimetable(construction);
		timetables.add(toAdd);		
	}
	
	private DateUsed futureDate(Timetable t, Slot s) {
		DateUsed d = new DateUsed();
		if (possible(Days.LUNDI, s.getDuration(), t).getHours() != -1){
			d.setDay(Days.LUNDI);
			d.setHours(possible(Days.LUNDI, s.getDuration(), t).getHours());
			d.setMinutes(possible(Days.LUNDI, s.getDuration(), t).getMinutes());			
		}
		else if (possible(Days.MARDI, s.getDuration(), t).getHours() != -1){
			d.setDay(Days.MARDI);
			d.setHours(possible(Days.MARDI, s.getDuration(), t).getHours());
			d.setMinutes(possible(Days.MARDI, s.getDuration(), t).getMinutes());	
		}
		else if (possible(Days.MERCREDI, s.getDuration(), t).getHours() != -1){
			d.setDay(Days.MERCREDI);
			d.setHours(possible(Days.MERCREDI, s.getDuration(), t).getHours());
			d.setMinutes(possible(Days.MERCREDI, s.getDuration(), t).getMinutes());	
		}
		else if (possible(Days.JEUDI, s.getDuration(), t).getHours() != -1){
			d.setDay(Days.JEUDI);
			d.setHours(possible(Days.JEUDI, s.getDuration(), t).getHours());
			d.setMinutes(possible(Days.JEUDI, s.getDuration(), t).getMinutes());	
		}
		else if (possible(Days.VENDREDI, s.getDuration(), t).getHours() != -1){
			d.setDay(Days.VENDREDI);
			d.setHours(possible(Days.VENDREDI, s.getDuration(), t).getHours());
			d.setMinutes(possible(Days.VENDREDI, s.getDuration(), t).getMinutes());	
		}
		else {
			System.err.println("Problem in method DateUsed, maybe possible() method");
		}
		
		return d;		
	}
	
	//Rename this method / used to know if we can set a slot into a day
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
						//comparison, is the beginning date + the duration included ?
						for ( Slot key : t.getTimetable().keySet()){
							if (key.getBeginning().includedInto(key.getDuration(),
									ap, duration) ||
									ap.includedInto(duration, key.getBeginning(), 
									key.getDuration())){
								break;
							}
							else {
								toReturn.setHours(ap.getHours());
								toReturn.setMinutes(ap.getMinutes());
								return toReturn;
							}
						}
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
