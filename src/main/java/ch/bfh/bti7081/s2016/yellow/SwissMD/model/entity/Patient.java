package ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PrescriptionDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.DangerStateType;

/**
 * Entity that represents a patient. To use in the data access layer.
 * 
 * @author K.Suter
 * 
 */
// TODO: Erstellt von Michel (wegen Abhängigkeit von Meeting), muss noch
// implementiert werden
@Entity
@Table
public class Patient extends Person {

	@OneToMany(mappedBy = "patient")
	List<Meeting> meetings;
	
	@OneToMany(mappedBy = "patient")
	List<Prescription> prescriptions;

	DangerStateType dangerState;
	
	public Patient(){};
	
	public Patient(String name, Date birthdate) {
		super(name, birthdate);
	}

	public DangerStateType getDangerState() {
		return this.dangerState;
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
