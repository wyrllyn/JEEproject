package model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestsDateUsed {
	
	private DateUsed du;
	private DateUsed du_overlap;

	@Before
	public void setUp() throws Exception {
		du = new DateUsed(Days.JEUDI, 9, 0);
		du_overlap = new DateUsed(Days.JEUDI, 9, 30);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_includedInto() {
		assertTrue(du.includedInto(80, du_overlap, 80));
	}
	
	@Test
	public void test_includedInto_bis() {
		du = new DateUsed(Days.MARDI, 8, 0);
		du_overlap = new DateUsed(Days.MARDI, 9, 30);
		assertTrue(du.includedInto(200, du_overlap, 90));
		assertTrue(du_overlap.includedInto(90, du, 200));
	}
	
	@Test
	public void test_includedInto_same_date() {
		du = new DateUsed(Days.MARDI, 8, 0);
		du_overlap = new DateUsed(Days.MARDI, 8, 0);
		assertTrue(du.includedInto(200, du_overlap, 90));
		assertTrue(du_overlap.includedInto(90, du, 200));
	}
	
	@Test
	public void test_synchronizeDate() {
		du = new DateUsed(Days.MARDI, 8, 0);
		long timeBefore = du.getDate().getTime();
		du.synchronizeDate();		
		long timeAfter = du.getDate().getTime();
		assertNotEquals(timeBefore, timeAfter);
	}
	
	/**
	 * Not an actual test, just here for reference.
	 */
	@Test
	public void forReference() {
		du = new DateUsed();
		du.synchronizeDate();
		System.out.println("Monday:\t\t" + du.getDate() + " " + du.getDate().getTime());
		du.setDay(Days.MARDI);
		du.synchronizeDate();
		System.out.println("Tuesday:\t" + du.getDate() + " " + du.getDate().getTime());
		du.setDay(Days.MERCREDI);
		du.synchronizeDate();
		System.out.println("Wednesday:\t" + du.getDate() + " " + du.getDate().getTime());
		du.setDay(Days.JEUDI);
		du.synchronizeDate();
		System.out.println("Thursday:\t" + du.getDate() + " " + du.getDate().getTime());
		du.setDay(Days.VENDREDI);
		du.synchronizeDate();
		System.out.println("Friday:\t\t" + du.getDate() + " " + du.getDate().getTime());
	}

}
