package ch.bfh.bti7081.s2016.yellow.SwissMD.model.util;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PatientDTO;

public class PatientInSessionManager {
	private static PatientInSessionManager manager;
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

	public void setPatientInSession(PatientDTO patientInSession) {
		this.patientInSession = patientInSession;
	}

}
