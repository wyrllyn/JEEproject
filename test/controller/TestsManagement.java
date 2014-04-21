package controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.CompleteHours;
import model.DateUsed;
import model.Days;
import model.Person;
import model.Room;
import model.Slot;
import model.Timetable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.database.DatabaseInterface;

public class TestsManagement {
	
	private Management man;
	private DatabaseInterface dbi;
	private List<Timetable> timetables;
	private Timetable t;

	@Before
	public void setUp() throws Exception {
		man = new Management();
		dbi = DatabaseInterface.getInstance();
		timetables = new ArrayList<Timetable>();
		t = dbi.getTimetableByGroup("M1 Info");
		timetables.add(t);
		man.setTimetables(timetables);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void test_conflictOfTime() {
		Slot slot = dbi.getSlotById(1);
		DateUsed ds = slot.getBeginning();
		DateUsed du = new DateUsed(ds.getDay(), ds.getHours(), ds.getMinutes() + 10);
		assertTrue(man.conflictOfTime(slot, 80, du));
	}

	@Test
	public void test_isThisPersonAvailable_OK() {
		Person p = dbi.getTeacherByName("Goeffon");
		int duration = 80;

		// Note: copied from test_conflictOfTime
		Slot slot = dbi.getSlotById(1);
		DateUsed ds = slot.getBeginning();
		DateUsed beginning = new DateUsed(ds.getDay(), ds.getHours() + 2, ds.getMinutes());

		assertTrue(man.isThisPersonAvailable(p, duration, beginning));
	}
	
	@Test
	public void test_isThisPersonAvailable_KO() {
		Person p = dbi.getTeacherByName("Goeffon");
		int duration = 80;
		
		// Note: copied from test_conflictOfTime
		Slot slot = dbi.getSlotById(1);
		DateUsed ds = slot.getBeginning();
		DateUsed beginning = new DateUsed(ds.getDay(), ds.getHours(), ds.getMinutes() + 10);
		
		assertFalse(man.isThisPersonAvailable(p, duration, beginning));
	}

	@Test
	public void test_isThisRoomEmpty_KO() {
		int duration = 80;
		// Note: copied from test_conflictOfTime
		Slot slot = dbi.getSlotById(1);
		Room room = new Room();
		room.setId(1);
		DateUsed ds = slot.getBeginning();
		DateUsed beginning = new DateUsed(ds.getDay(), ds.getHours(), ds.getMinutes());
		
		assertFalse(man.isThisRoomEmpty(room, duration, beginning));
	}
	
	@Test
	public void test_isThisRoomEmpty_OK() {
		int duration = 80;
		// Note: copied from test_conflictOfTime
		Slot slot = dbi.getSlotById(1);
		Room room = new Room();
		room.setId(1);
		DateUsed ds = slot.getBeginning();
		DateUsed beginning = new DateUsed(ds.getDay(), ds.getHours() + 3, ds.getMinutes());
		
		assertTrue(man.isThisRoomEmpty(room, duration, beginning));
	}
	
	@Test
	public void test_possible_OK() {
		Slot slot = dbi.getSlotById(1);
		DateUsed ds = slot.getBeginning();
		CompleteHours ch = man.possible(ds.getDay(), 80, t);
		assertTrue(ch.getHours() != -1);
		assertTrue(ch.getMinutes() != -1);
		DateUsed du = new DateUsed(ds.getDay(), ch.getHours(), ch.getMinutes());
		for (Slot currentSlot : t.getTimetable().keySet()) {
			assertFalse(currentSlot.getBeginning().includedInto(currentSlot.getDuration(), du, 80));
			assertFalse(du.includedInto(80, currentSlot.getBeginning(), currentSlot.getDuration()));
		}
	}
	
	@Test
	public void test_possible_KO() {
		DateUsed beginning = new DateUsed(Days.LUNDI, 8, 0);
		for (int i = 0; i < 11; i++) {
			Slot slot = new Slot();
			slot.setBeginning(beginning);
			slot.setDuration(60);
			Room room = new Room();
			room.setId(1);
			t.getTimetable().put(slot, room);
			beginning = new DateUsed(Days.LUNDI,
					beginning.getHours() + 1,
					(beginning.getMinutes() + 10) % 60);
		}
		/*for (Slot slot : t.getTimetable().keySet()) {
			System.out.println("\t" + slot);
		}/**/
		CompleteHours ch = man.possible(Days.LUNDI, 60, t);
		assertTrue(ch.getHours() == -1);
		assertTrue(ch.getMinutes() == -1);
	}
	
	@Test
	public void test_possible_OK_bis() {
		DateUsed beginning = new DateUsed(Days.LUNDI, 8, 0);
		for (int i = 0; i < 11; i++) {
			Slot slot = new Slot();
			slot.setBeginning(beginning);
			slot.setDuration(60);
			Room room = new Room();
			room.setId(1);
			// nothing between 14h & 16h
			if (beginning.getHours() < 14 || beginning.getHours() > 16) {
				t.getTimetable().put(slot, room);
			}
			beginning = new DateUsed(Days.LUNDI,
					beginning.getHours() + 1,
					(beginning.getMinutes() + 10) % 60);
		}
		CompleteHours ch = man.possible(Days.LUNDI, 60, t);
		assertFalse(ch.getHours() == -1);
		assertFalse(ch.getMinutes() == -1);
		System.out.println("assert " +ch.getHours());

	}
	
	@Test
	public void test_futureDate() {
		DateUsed beginning = new DateUsed(Days.LUNDI, 8, 0);
		for (int i = 0; i < 11; i++) {
			Slot slot = new Slot();
			slot.setBeginning(beginning);
			slot.setDuration(60);
			Room room = new Room();
			room.setId(1);
			t.getTimetable().put(slot, room);
			beginning = new DateUsed(Days.LUNDI,
					beginning.getHours() + 1,
					(beginning.getMinutes() + 10) % 60);
		}
		Slot tuesday = new Slot();
		tuesday.setBeginning(new DateUsed(Days.MARDI, 8, 0));
		tuesday.setDuration(200);
		t.getTimetable().put(tuesday, null);
		tuesday = new Slot();
		tuesday.setBeginning(new DateUsed(Days.MARDI, 13, 50));
		tuesday.setDuration(90);
		t.getTimetable().put(tuesday, null);
		
		Slot slot = new Slot();
		slot.setDuration(90);
		DateUsed futureDate = man.futureDate(t, slot);
		System.out.println(futureDate);
		assertTrue(futureDate.getDay() != Days.LUNDI);

	}
}
