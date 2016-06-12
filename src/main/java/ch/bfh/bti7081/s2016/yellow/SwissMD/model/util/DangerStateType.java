package ch.bfh.bti7081.s2016.yellow.SwissMD.model.util;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DangerState;

/**
 * Indicates how dangerous a patient is.
 * 
 * @author D. Halter & K. Suter
 * 
 */
public enum DangerStateType {
	HARMLESS, CRISIS, DANGER_TO_HIMSELF, DANGER_TO_OTHERS;

	private DangerState dangerState;

	public String getString(DangerStateType t) {
		switch (t) {
		case HARMLESS:
			return "Harmlos";
		case CRISIS:
			return "Krise";
		case DANGER_TO_HIMSELF:
			return "Eigengefaehrdung";
		case DANGER_TO_OTHERS:
			return "Fremdgefaehrdung";
		default:
			break;
		}
		return null;
	}

	public DangerState getDangerState() {
		return this.dangerState;
	}

}
