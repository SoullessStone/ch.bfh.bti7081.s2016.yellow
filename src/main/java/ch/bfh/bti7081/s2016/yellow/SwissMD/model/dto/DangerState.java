package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import java.util.Date;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.MeetingStateException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.DangerStateType;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.MeetingStateType;

/**
 * 
 * Saves the danger state of a patient
 * 
 * @author D: Halter
 * 
 */
public abstract class DangerState {
	
	abstract void cancelDangerState(PatientDTO patient)
			throws MeetingStateException;
	
	protected void changeDangerState(PatientDTO patient)
			throws MeetingStateException {
			patient.setDangerState(new DangerStateNew());;
	};
	
	abstract DangerStateType getState();
}
