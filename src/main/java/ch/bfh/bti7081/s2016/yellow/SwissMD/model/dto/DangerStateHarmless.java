package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.DangerStateException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.DangerStateType;

/**
 * 
 * Danger State which has been newly set. Can not get canceled or performed yet.
 * 
 * @author K.Suter
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
