package util;

import model.DateUsed;
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
}
