package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.DangerStateException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.MeetingStateException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.DangerStateType;

/**
 * 
 * Saves the danger state of a patient
 * 
 * @author D: Halter
 * 
 */
public abstract class DangerState {

	protected abstract void setDangerStateHarmless(PatientDTO patient) throws DangerStateException;
	protected abstract void setDangerStateCrisis(PatientDTO patient) throws DangerStateException;
	protected abstract void setDangerStateDangerToHimself(PatientDTO patient) throws DangerStateException;
	protected abstract void setDangerStateDangerToOthers(PatientDTO patient) throws DangerStateException;
	
	abstract DangerStateType getState();
}
