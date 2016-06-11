package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.MeetingStateException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.DangerStateType;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.MeetingStateType;


/**
 * 
 * Meeting which has been newly created. Can not get canceled or performed yet. Must be planned first.
 * 
 * @author K.Suter
 * 
 */
public class DangerStateNew extends DangerState {

	@Override
	DangerStateType getState() {
		return DangerStateType.HARMLESS;
	}

	@Override
	void cancelDangerState(PatientDTO patient) throws MeetingStateException {
		// TODO Auto-generated method stub
		
	}

}
