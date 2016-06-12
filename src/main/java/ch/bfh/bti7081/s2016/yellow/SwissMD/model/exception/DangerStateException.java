package ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception;

/**
 * Exception that is thrown if the danger state of a patient could not be changed.
 * 
 * @author D. Halter
 * 
 */

@SuppressWarnings("serial")
public class DangerStateException extends Exception {

	public DangerStateException(String message) {
		super(message);
	}
}
