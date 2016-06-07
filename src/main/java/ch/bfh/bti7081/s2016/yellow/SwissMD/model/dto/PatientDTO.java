package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Patient;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Prescription;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.DangerStateType;

public class PatientDTO extends PersonDTO {

	List<PrescriptionDTO> prescriptions = new ArrayList<>();
	
	private Long legalAid;
	private Long familyDoctor;
	private DangerState dangerState;

	public PatientDTO(String name, Date birthdate) {
		setName(name);
		setBirthdate(birthdate);
		setDtype("Patient");
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
	
	public DangerStateType getDangerState() {
		return dangerState.getState();
	}

	public void setDangerState(DangerState newDangerState) {
		this.dangerState = newDangerState;
	}

	public Long getLegalAid() {
		return legalAid;
	}

	public void setLegalAid(Long legalAid) {
		this.legalAid = legalAid;
	}

	public Long getFamilyDoctor() {
		return familyDoctor;
	}

	public void setFamilyDoctor(Long familyDoctor) {
		this.familyDoctor = familyDoctor;
	}

	
	

}
