package controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.DateUsed;
import model.Person;
import model.Timetable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import util.DateUtil;
import database.DatabaseInterface;

public class TestsManagement {
	
	private Management man;
	private DatabaseInterface dbi;
	private List<Timetable> timetables;

	@Before
	public void setUp() throws Exception {
		man = new Management();
		dbi = DatabaseInterface.getInstance();
		timetables = new ArrayList<Timetable>();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_isThisPersonAvailable() {
		Timetable t = dbi.getTimetableByGroup("M1 Info");
		timetables.add(t);
		
		@SuppressWarnings("static-access")
		Person p = dbi.getTeacherByName("Goeffon");
		int duration = 80;
		DateUsed beginning = DateUtil.createDateUsed(new Date(800));
		
		man.setTimetables(timetables);
		assertTrue(man.isThisPersonAvailable(p, duration, beginning));
	}

}
