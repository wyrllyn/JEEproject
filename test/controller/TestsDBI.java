package controller;

import static org.junit.Assert.*;

import java.util.List;

import model.Person;
import model.Room;
import model.Slot;
import model.Timetable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import database.DatabaseInterface;

public class TestsDBI {

	private DatabaseInterface dbi;
	
	@Before
	public void setUp() throws Exception {
		dbi = DatabaseInterface.getInstance();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_connect() {
		dbi.connect();
		assertTrue(dbi.authenticateUser("testUsr", "password"));
	}

	@Test
	public void test_getRooms() {
		List<Room> rooms = dbi.getRooms();
		assertFalse(rooms.isEmpty());
	}
	
	@Test
	public void test_getTeacherByName() {
		@SuppressWarnings("static-access")
		Person teacher = dbi.getTeacherByName("Goeffon");
		assertNotNull(teacher);
		assertEquals("Goeffon", teacher.getName());
		assertEquals("prof", teacher.getType());
	}
	
	@Test
	public void test_getSlotById() {
		Slot slot = dbi.getSlotById(1);
		assertNotNull(slot);
		assertEquals("CM", slot.getType());
		assertEquals(80, slot.getDuration());
	}
	
	@Test
	public void test_getTimetableByGroup() {
		Timetable timetable = dbi.getTimetableByGroup("M1 Info");
		assertNotNull(timetable);
	}
}