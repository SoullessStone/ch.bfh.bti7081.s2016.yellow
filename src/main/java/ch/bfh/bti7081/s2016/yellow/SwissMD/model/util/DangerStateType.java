package ch.bfh.bti7081.s2016.yellow.SwissMD.model.util;

/**
 * Indicates how dangerous a patient is.
 * 
 * @author K.Suter
 * 
 */
public enum DangerStateType {
	HARMLESS, DANGER_TO_HIMSELF, DANGER_TO_OTHERS, DANGER_TO_EVERYONE;
	
	public String getString(DangerStateType t){
		switch (t) {
		case HARMLESS:
			return "Harmlos";

		default:
			break;
		}
		return null;
	}
	
}
