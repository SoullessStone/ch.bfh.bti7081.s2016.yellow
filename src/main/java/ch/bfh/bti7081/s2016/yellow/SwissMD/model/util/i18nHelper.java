package ch.bfh.bti7081.s2016.yellow.SwissMD.model.util;

/**
 * Gets translations
 * Later on we could integrate languages here
 * 
 * @author S.Schaad
 * 
 */
public class i18nHelper {
	private static final String NEW = "Neu" ;
	private static final String PLANNED = "Geplant" ;
	private static final String PERFORMED = "Abgeschlossen" ;
	private static final String CANCELED = "Abgesagt" ;
	private static final String OVERDUE = "fFällig" ;
	
	i18nHelper() {}
	
	public static String getMeetingStateTypeTranlation(MeetingStateType stateType){
		switch(stateType.toString()){
		case "NEW": return NEW;
		case "PLANNED": return PLANNED;
		case "PERFORMED": return PERFORMED;
		case "CANCELED": return CANCELED;
		case "OVERDUE": return OVERDUE;
	}
	return NEW;
	}
}
