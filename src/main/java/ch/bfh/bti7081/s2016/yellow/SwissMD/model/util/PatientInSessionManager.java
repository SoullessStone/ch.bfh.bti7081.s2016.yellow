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
	private static PatientInSessionManager manager;
	private static VaadinSession vaadinSession;
	private PatientDTO patientInSession;

	private PatientInSessionManager() {

	}

	public static PatientInSessionManager getInstance() {
		if (manager == null) {
			manager = new PatientInSessionManager();
		}
		return manager;
	}

	public PatientDTO getPatientInSession() {
		return patientInSession;
	}

	public void setPatientInSession(PatientDTO patient, PersonDTO person,
			VaadinSession vaadinSession) {
		PatientInSessionManager.vaadinSession = vaadinSession;
		vaadinSession.setAttribute("currentPatient", patient);
		vaadinSession.setAttribute("currentPerson", person);
		this.patientInSession = patient;
	}

}
