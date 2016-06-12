package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.DangerStateException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.DangerStateType;

public class DangerStateDangerToHimself extends DangerState {

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
		return DangerStateType.DANGER_TO_HIMSELF;
	}

}
