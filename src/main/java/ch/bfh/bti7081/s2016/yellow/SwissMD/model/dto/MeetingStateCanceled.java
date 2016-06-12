package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import java.util.Date;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.MeetingStateException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.MeetingStateType;

/**
 * 
 * Meeting that has been canceled. Cannot get canceled, planned or performed
 * anymore.
 * 
 * @author K.Suter
 * 
 */
public class MeetingStateCanceled extends MeetingState {

	@Override
	void cancelMeeting(MeetingDTO meeting) throws MeetingStateException {
		throw new MeetingStateException(
				"Ein bereits abgesagtes Meeting kann nicht noch einmal abgesagt werden.");
	}

	@Override
	protected void planMeeting(MeetingDTO meeting, Date appointmentTime)
			throws MeetingStateException {
		throw new MeetingStateException(
				"Ein bereits abgesagtes Meeting kann nicht mehr geplant werden.");
	}

	@Override
	void performMeeting(MeetingDTO meeting) throws MeetingStateException {
		throw new MeetingStateException(
				"Ein bereits abgesagtes Meeting kann nicht durchgeführt werden.");
	}

	@Override
	MeetingStateType getState() {
		return MeetingStateType.CANCELED;
	}

}
