package controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestsDBI {

	private DatabaseInterface dbi;
	
	@Before
	public void setUp() throws Exception {
		dbi = DatabaseInterface.dbInterface;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		dbi.connect();
		assertTrue(dbi.authenticateUser("test", "password"));
	}

}
