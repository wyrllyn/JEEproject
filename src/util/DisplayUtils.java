package util;

import java.util.List;

import controller.database.DatabaseInterface;
import model.DateUsed;
import model.Person;
import model.Slot;

public class DisplayUtils {

	
	public static String printSlot(Slot slot) {
		String string = "";
		DateUsed end = slot.getBeginning().calcutateEnd(slot.getDuration());
		//string += "<tr><td>" + slot.getBeginning().getDay() + "</td>";
		string += "<td>" + slot.getName() + " : "
				+ slot.getBeginning().getHours() + "h" + slot.getBeginning().getMinutes()
				+ " - " + end.getHours() + "h" + end.getMinutes()
				+"</td>";
		//string += "</tr>";
		return string;
	}
	
	public static String getTeacherSelectOptions() {
		String string = "";
		DatabaseInterface dbi = DatabaseInterface.getInstance();
		List<Person> teachers = dbi.getTeachers();
		for (Person teacher : teachers) {
			string += "<option value=\"" + teacher.getName() + "\">";
			string += teacher.getName();
			string += "</option>";
		}
		return string;
	}
	
	public static String getDaysSelectOptions() {
		String string = "";
		string += "<option value=\"lundi\">Lundi</option>";
		string += "<option value=\"mardi\">Mardi</option>";
		string += "<option value=\"mercredi\">Mercredi</option>";
		string += "<option value=\"jeudi\">Jeudi</option>";
		string += "<option value=\"vendredi\">Vendredi</option>";
		return string;
	}
}
