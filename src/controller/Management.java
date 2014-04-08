package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.tiles.request.collection.KeySet;

import model.FutureTimetable;
import model.Person;
import model.Room;
import model.Slot;
import model.Timetable;

public class Management {

	static final long ONE_MINUTE_IN_MILLIS=60000;//millisecs
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
		int totalTime = 0;
		for (int i = 0; i < future.getToSet().size() ; i++) {
			totalTime += future.getToSet().get(i).getDuration();
		}
		
		//trouver comment manipuler dates en java...
		/*
		 
		  static final long ONE_MINUTE_IN_MILLIS=60000;//millisecs

			long t=date.getTime();
			Date afterAddingTenMins=new Date(t + (10 * ONE_MINUTE_IN_MILLIS));
		 */
		
		if (totalTime > 20){
			
		}
		
		
		timetables.add(toAdd);		
	}
	
	// know if a room is available 
	
	public boolean isThisRoomEmpty(Room room, int duration, Date beginning){
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
	
	public boolean isThisPersonAvailable(Person p, int duration, Date beginning) {
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
	
	public boolean conflictOfTime (Slot slot, int duration, Date beginning) {
		if(slot.getBeginning().equals(beginning)){
			return true;
		}
		
		//TODO: compare if dates + duration are in conflict or not
		Date endSlot = slot.getBeginning();
		Date endTest = beginning;
		
		return false;
	}
	
	public void removeSlot(Timetable table, Slot slotToRemove){
		
	}
	
	/*public static void main(){

		SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd HH:mm");
		Date d1 = df.parse(interviewList.get(37).getTime());
		long t = date.getTime();
		Date afterAddingTenMins=new Date(t + (10 * ONE_MINUTE_IN_MILLIS));
	}*/

}
