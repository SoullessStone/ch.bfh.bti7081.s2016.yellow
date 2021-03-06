package ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.DangerStateType;

/**
 * Entity that represents a patient. To use in the data access layer.
 * 
 * @author K.Suter
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table
public class Patient extends Person {

	@OneToMany(mappedBy = "patient")
	List<Meeting> meetings;

	@OneToMany(mappedBy = "patient")
	List<Prescription> prescriptions;

	private Long legalAid;
	private Long familyDoctor;
	private DangerStateType dangerState;

	public DangerStateType getDangerState() {
		return this.dangerState;
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

	public void setDangerState(DangerStateType newState) {
		this.dangerState = newState;
	}

	public List<Prescription> getPrescriptions() {
		if (prescriptions != null)
			return Collections.unmodifiableList(prescriptions);
		return new ArrayList<Prescription>();
	}

	public void addPrescription(Prescription prescription) {
		this.prescriptions.add(prescription);
	}

	public void removePrescription(Prescription prescription) {
		this.prescriptions.remove(prescription);
	}
}
