package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Patient;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Prescription;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.DangerStateException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.MeetingStateException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.DangerStateType;

public class PatientDTO extends PersonDTO {

	List<PrescriptionDTO> prescriptions = new ArrayList<>();

	private Long legalAid;
	private Long familyDoctor;
	private DangerState dangerState;

	public PatientDTO(String name, Date birthdate, DangerState dangeState) {
		setName(name);
		setBirthdate(birthdate);
		setDangerState(dangerState);
		setDtype("Patient");
	}

	public PatientDTO(Patient patient) throws DangerStateException {
		super(patient);
		this.legalAid = patient.getLegalAid();
		this.familyDoctor = patient.getFamilyDoctor();
		DangerStateType checkDangerState = patient.getDangerState();
		if (checkDangerState != null) {
			this.dangerState = checkDangerState.getDangerState();
		} else {
			this.dangerState.setDangerStateHarmless(this);
		}

		for (Prescription prescription : patient.getPrescriptions()) {
			prescriptions.add(new PrescriptionDTO(prescription, this));
		}
	}

	/**
	 * This method must remain protected! It is not allowed to change the danger
	 * state from outside this package.
	 **/
	protected void changeDangerState(DangerState newDangerState) {
		this.dangerState = newDangerState;
	}

	public void setDangerStateHarmless() throws DangerStateException {
		this.dangerState.setDangerStateHarmless(this);
	};

	public void setDangerStateCrisis() throws DangerStateException {
		this.dangerState.setDangerStateCrisis(this);
	};

	public void setDangerStateDangerToHimself() throws DangerStateException {
		this.dangerState.setDangerStateDangerToHimself(this);
	};

	public void setDangerStateDangerToOthers() throws DangerStateException {
		this.dangerState.setDangerStateDangerToOthers(this);
	};

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
		return this.dangerState.getState();
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
