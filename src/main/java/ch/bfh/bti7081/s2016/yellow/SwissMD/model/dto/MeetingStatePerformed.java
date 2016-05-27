package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import java.util.Date;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.MeetingStateException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.MeetingStateType;


/**
 * 
 * Meeting which has been performed. Can not get canceled, planned or performed anymore.
 * 
 * @author K.Suter
 * 
 */
public class MeetingStatePerformed extends MeetingState {

	@Override
	void cancelMeeting(MeetingDTO meeting) throws MeetingStateException {
		throw new MeetingStateException("Ein bereits durchgeführtes Meeting kann nicht mehr abgesagt werden.");
	}

	@Override
	protected void planMeeting(MeetingDTO meeting, Date newAppointmentTime) throws MeetingStateException {
		throw new MeetingStateException("Ein bereits durchgeführtes Meeting kann nicht mehr geplant werden.");
	}

	@Override
	void performMeeting(MeetingDTO meeting) throws MeetingStateException {
		throw new MeetingStateException("Ein bereits durchgeführtes Meeting kann nicht noch einmal durchgeführt werden.");
	}

	@Override
	MeetingStateType getState() {
		return MeetingStateType.PERFORMED;
	}
}
