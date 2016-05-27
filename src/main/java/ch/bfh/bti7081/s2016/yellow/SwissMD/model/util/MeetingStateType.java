package ch.bfh.bti7081.s2016.yellow.SwissMD.model.util;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.MeetingState;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.MeetingStateCanceled;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.MeetingStateNew;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.MeetingStatePerformed;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.MeetingStatePlanned;


public enum MeetingStateType {
	NEW(new MeetingStateNew()),
	PLANNED(new MeetingStatePlanned()),
	CANCELED(new MeetingStateCanceled()),
	PERFORMED(new MeetingStatePerformed());
	
	private MeetingState state;
	
	MeetingStateType(MeetingState ms) {
		this.state = ms;
	}
	
	public MeetingState getMeetingState(){
		return this.state;
	}
}
