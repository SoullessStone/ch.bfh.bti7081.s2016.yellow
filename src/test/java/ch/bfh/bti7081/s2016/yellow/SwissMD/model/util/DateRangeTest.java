package ch.bfh.bti7081.s2016.yellow.SwissMD.model.util;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.IllegalDateRangeException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.DateRange;

public class DateRangeTest {
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void happy() throws IllegalDateRangeException {
		//
		ExpectedException.none();
		Date from = new Date();
		Date to = new Date(from.getTime() + 1000000);
		// act
		DateRange sut = new DateRange(from, to);
		// assert
		assertNotNull(sut);
	}

	@Test(expected=IllegalDateRangeException.class)
	public void invalidConstructorParam() throws IllegalDateRangeException {
		//
		thrown.expect(IllegalDateRangeException.class);
		Date from = new Date();
		Date to = new Date(from.getTime() + 1000000);
		// act
		new DateRange(to, from);
	}
}
