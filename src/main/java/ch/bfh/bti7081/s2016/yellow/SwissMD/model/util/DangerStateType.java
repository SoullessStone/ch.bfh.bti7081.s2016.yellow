package ch.bfh.bti7081.s2016.yellow.SwissMD.model.util;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DangerState;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DangerStateCrisis;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DangerStateDangerToHimself;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DangerStateDangerToOthers;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DangerStateHarmless;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.MeetingState;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.MeetingStateCanceled;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.MeetingStateNew;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.MeetingStatePerformed;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.MeetingStatePlanned;

/**
 * Indicates how dangerous a patient is.
 * 
 * @author D. Halter & K. Suter
 * 
 */
public enum DangerStateType {
	HARMLESS(new DangerStateHarmless(), "Harmlos"), CRISIS(new DangerStateCrisis(),
			"Krise"), DANGER_TO_HIMSELF(new DangerStateDangerToHimself(), "Eigengefaehrdung"), 
			DANGER_TO_OTHERS(new DangerStateDangerToOthers(), "Fremdgefaehrdung");

	private DangerState dangerState;
	private String stateTitle;
	
	DangerStateType(DangerState curDangerState, String stateTitle) {
		this.dangerState = curDangerState;
		this.stateTitle = stateTitle;
	}

	public String getDangerStateTitle() {
		return stateTitle;
	}
	
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
