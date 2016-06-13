package ch.bfh.bti7081.s2016.yellow.SwissMD.model.util;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DangerState;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DangerStateCrisis;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DangerStateDangerToHimself;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DangerStateDangerToOthers;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DangerStateHarmless;

/**
 * Indicates how dangerous a patient is.
 * 
 * @author D. Halter
 * @author K. Suter
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

	public DangerState getDangerState() {
		return this.dangerState;
	}

}
