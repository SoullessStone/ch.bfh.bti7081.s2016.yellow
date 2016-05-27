package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import java.util.Date;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.MeetingStateType;


/**
 * 
 * Meeting which is planned but not performed yet. Can get canceled, planned or performed.
 * 
 * @author K.Suter
 * 
 */
public class MeetingStatePlanned extends MeetingState {

	@Override
	void cancelMeeting(MeetingDTO meeting) {
		meeting.changeMeetingState(new MeetingStateCanceled());
	}

	@Override
	void performMeeting(MeetingDTO meeting) {
		meeting.changeMeetingState(new MeetingStatePerformed());
	}

	@Override
	MeetingStateType getState() {
		return MeetingStateType.PLANNED;
	}

}
