package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.MeetingStateException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.MeetingStateType;


/**
 * 
 * Meeting which has been newly created. Can not get canceled or performed yet. Must be planned first.
 * 
 * @author K.Suter
 * 
 */
public class MeetingStateNew extends MeetingState {

	@Override
	void cancelMeeting(MeetingDTO meeting) throws MeetingStateException {
		throw new MeetingStateException("Ein noch nicht geplantes Meeting kann nicht abgesagt werden.");
	}

	@Override
	void performMeeting(MeetingDTO meeting) throws MeetingStateException {
		throw new MeetingStateException("Ein noch nicht geplantes Meeting kann nicht durchgeführt werden.");
	}

	@Override
	MeetingStateType getState() {
		return MeetingStateType.NEW;
	}

}
