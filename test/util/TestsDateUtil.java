package util;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import model.DateUsed;
import model.Days;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestsDateUtil {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Note: also indirectly tests convertToDays
	 */
	@Test
	public void test_createDateUsed() {
		Date date = new Date();
		DateUsed du = DateUtil.createDateUsed(date);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		assertEquals(DateUtil.convertToDays(c.get(Calendar.DAY_OF_WEEK)), du.getDay());
		assertEquals(c.get(Calendar.HOUR_OF_DAY), du.getHours());
		assertEquals(c.get(Calendar.MINUTE), du.getMinutes());
	}

	@Test
	public void test_createDate() {
		DateUsed du = new DateUsed(Days.MERCREDI, 10, 0);
		Date date = DateUtil.createDate(du);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		assertEquals(DateUtil.convertToDays(c.get(Calendar.DAY_OF_WEEK)), du.getDay());
		assertEquals(c.get(Calendar.HOUR_OF_DAY), du.getHours());
		assertEquals(c.get(Calendar.MINUTE), du.getMinutes());
	}
}
