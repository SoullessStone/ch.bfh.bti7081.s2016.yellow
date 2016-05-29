package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Patient;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Prescription;

public class PatientDTO extends PersonDTO {

	List<PrescriptionDTO> prescriptions = new ArrayList<>();

	public PatientDTO(String name, Date birthdate) {
		super(name, birthdate, "Patient");
	}

	public PatientDTO(Patient patient) {
		super(patient);
		for (Prescription prescription : patient.getPrescriptions()) {
			prescriptions.add(new PrescriptionDTO(prescription,this));
		}
	}

	public List<PrescriptionDTO> getPrescriptions() {
		if (prescriptions != null)
			return Collections.unmodifiableList(prescriptions);
		return new ArrayList<PrescriptionDTO>();
	}

	public void addPrescription(PrescriptionDTO prescription) {
		this.prescriptions.add(prescription);
	}

	public void removePrescription(PrescriptionDTO prescription) {
		this.prescriptions.remove(prescription);
	}

}
