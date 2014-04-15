package controller;

import static org.junit.Assert.*;

import java.util.List;

import model.Room;

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
		boolean type = false;
		if(dbi.authenticateUser("test", "password")!=2)
			type = true;
		assertTrue(type);
	}

	@Test
	public void test_getRooms() {
		List<Room> rooms = dbi.getRooms();
		assertFalse(rooms.isEmpty());
	}
}
