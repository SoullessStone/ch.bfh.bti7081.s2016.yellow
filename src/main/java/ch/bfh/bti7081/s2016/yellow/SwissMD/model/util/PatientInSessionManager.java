package ch.bfh.bti7081.s2016.yellow.SwissMD.model.util;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Component;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PatientDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PersonDTO;

/**
 * Verbindet das MenuTile mit der Session.
 * 
 * @author Mutz
 *
 */
public class PatientInSessionManager {

	
	public static PatientDTO getPatientInSession(VaadinSession vaadinSession) {
		return (PatientDTO) vaadinSession.getAttribute("currentPatient");
	}

	public static void setPatientInSession(PatientDTO patient, PersonDTO person,
			VaadinSession vaadinSession) {

		vaadinSession.setAttribute("currentPatient", patient);
		vaadinSession.setAttribute("currentPerson", person);
	}

}
