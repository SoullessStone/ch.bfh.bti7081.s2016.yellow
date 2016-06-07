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

	protected void changeDangerState(PatientDTO patient, DangerState newState)
			throws MeetingStateException {
			patient.setDangerState(newState);;
	};

	abstract DangerStateType getState();
}
