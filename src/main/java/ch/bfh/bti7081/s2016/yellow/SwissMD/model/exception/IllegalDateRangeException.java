package ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception;

/**
 * Exception that is thrown if the start date of a {@link DateRange} is after the end date.
 * 
 * @author K.Suter
 * 
 */
@SuppressWarnings("serial")
public class IllegalDateRangeException extends Exception {

	public IllegalDateRangeException(String message) {
		super(message);
	}
}
