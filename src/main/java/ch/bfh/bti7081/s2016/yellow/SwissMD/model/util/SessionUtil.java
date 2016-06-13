package ch.bfh.bti7081.s2016.yellow.SwissMD.model.util;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DoctorDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PatientDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PersonDTO;

import com.vaadin.server.VaadinSession;

/**
 * Verbindet das MenuTile mit der Session.
 * 
 * @author Mutz
 *
 */
public class SessionUtil {

	
	public static PatientDTO getPatientInSession(VaadinSession vaadinSession) {
		return (PatientDTO) vaadinSession.getAttribute("currentPatient");
	}

	public static void setPatientInSession(PatientDTO patient, PersonDTO person,
			VaadinSession vaadinSession) {

		vaadinSession.setAttribute("currentPatient", patient);
		vaadinSession.setAttribute("currentPerson", person);
	}
	
	public static void setDoctorInSession(DoctorDTO doctor,
			VaadinSession vaadinSession) {

		vaadinSession.setAttribute("currentDoctor", doctor);
	}	
	
	public static DoctorDTO getDoctorInSession(VaadinSession vaadinSession) {
		return (DoctorDTO) vaadinSession.getAttribute("currentDoctor");
	}
	

}
