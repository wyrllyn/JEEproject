package util;

import java.util.Comparator;

import model.Days;

public class DaysComparator implements Comparator<Days> {
	
	public int getDayValue(Days day) {
		switch (day) {
		case LUNDI:
			return 0;
		case MARDI:
			return 1;
		case MERCREDI:
			return 2;
		case JEUDI:
			return 3;
		case VENDREDI:
			return 4;
		}
		return 0;
	}

	@Override
	public int compare(Days arg0, Days arg1) {
		return getDayValue(arg0) - getDayValue(arg1);
	}

}
