package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.DangerStateException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.DangerStateType;

/**
 * 
 * Represents the danger state harmless . which is not really a danger state, but is needed - for a patient
 * 
 * @author D. Halter
 * 
 */

public class DangerStateHarmless extends DangerState {

	@Override
	protected void setDangerStateHarmless(PatientDTO patient)
			throws DangerStateException {
		patient.changeDangerState(new DangerStateHarmless());
	}

	@Override
	protected void setDangerStateCrisis(PatientDTO patient)
			throws DangerStateException {
		patient.changeDangerState(new DangerStateCrisis());
	}

	@Override
	protected void setDangerStateDangerToHimself(PatientDTO patient)
			throws DangerStateException {
		patient.changeDangerState(new DangerStateDangerToHimself());
	}

	@Override
	protected void setDangerStateDangerToOthers(PatientDTO patient)
			throws DangerStateException {
		patient.changeDangerState(new DangerStateDangerToOthers());
	}

	@Override
	DangerStateType getState() {
		return DangerStateType.HARMLESS;
	}

}
