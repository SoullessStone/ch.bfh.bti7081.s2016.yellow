package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import java.util.Date;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.MeetingStateException;
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
	void planMeeting(MeetingDTO meeting, Date appointmentTime)
			throws MeetingStateException {
		if (appointmentTime.after(new Date())) {
			meeting.changeMeetingState(new MeetingStatePlanned());
		} else {
			meeting.changeMeetingState(new MeetingStateOverdue());
		}
		meeting.setAppointmentTime(appointmentTime);
		meeting.changeMeetingState(new MeetingStatePlanned());
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
