package ch.bfh.bti7081.s2016.yellow.SwissMD.view.navigation;

import ch.bfh.bti7081.s2016.yellow.SwissMD.view.LoginView;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.MeetingView;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.PersonSearchView;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.PersonView;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.PrescriptionView;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.WikiView;

public enum NavigationIndex {
	
	LOGINVIEW("", LoginView.class),
	PERSONSEARCHVIEW("PERSONSEARCH", PersonSearchView.class),
	PERSONVIEW("PERSONVIEW", PersonView.class),
	PRESCRIPTIONVIEW  ("PRESCRIPTIONVIEW", PrescriptionView.class),
	WIKIVIEW ("WIKIVIEW",WikiView.class),
	MEETINGVIEW ("MEETINGVIEW", MeetingView.class);

	private final String navigationPath;
	private final Class viewClass;
	
	private NavigationIndex(String name, Class viewClass){
		this.navigationPath = name;
		this.viewClass = viewClass;
	}
	
	public Class getViewClass(){
		return viewClass;
	}
	
	public String getNavigationPath(){
		return navigationPath;
	}

}
