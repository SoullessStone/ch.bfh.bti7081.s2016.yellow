package ch.bfh.bti7081.s2016.yellow.SwissMD.model.util;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.MeetingState;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.MeetingStateCanceled;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.MeetingStateNew;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.MeetingStatePerformed;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.MeetingStatePlanned;

/**
 * Indicates the state of a meeting. If used to save the state to the database.
 * 
 * @author K.Suter
 * 
 */
public enum MeetingStateType {
	NEW(new MeetingStateNew(),"neu"),
	PLANNED(new MeetingStatePlanned(),"geplant"),
	CANCELED(new MeetingStateCanceled(),"abgesagt"),
	PERFORMED(new MeetingStatePerformed(),"abgeschlossen");
	
	private MeetingState state;
	private String localization;
	
	MeetingStateType(MeetingState ms, String localization) {
		this.state = ms;
		this.localization = localization;
	}
	
	public String getLocalization(){
		return localization;
	}
	
	public MeetingState getMeetingState(){
		return this.state;
	}
}
