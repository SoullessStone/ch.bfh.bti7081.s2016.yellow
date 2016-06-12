package ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception;

/**
 * Exception that is thrown if the state of a meeting could not be changed.
 * 
 * @author K.Suter
 * 
 */
@SuppressWarnings("serial")
public class MeetingStateException extends Exception {

	public MeetingStateException(String message) {
		super(message);
	}
}
