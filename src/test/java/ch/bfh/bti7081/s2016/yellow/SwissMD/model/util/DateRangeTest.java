package ch.bfh.bti7081.s2016.yellow.SwissMD.model.util;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.IllegalDateRangeException;

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

	@Test
	public void invalidConstructorParam() throws IllegalDateRangeException {
		//
		Date from = new Date();
		Date to = new Date(from.getTime() + 1000000);
		// act
		thrown.expect(IllegalDateRangeException.class);
		new DateRange(to, from);
	}
}
