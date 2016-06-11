package ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception;

/**
 * Exception that is thrown if an entity could not be saved.
 * 
 * @author K.Suter
 * 
 */
@SuppressWarnings("serial")
public class CouldNotSaveException extends Exception {

	public CouldNotSaveException(String string) {
		super(string);
	}

}
