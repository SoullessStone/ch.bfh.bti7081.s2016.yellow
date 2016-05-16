package ch.bfh.bti7081.s2016.yellow.SwissMD.model;

import java.util.Date;
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
// TODO: Erstellt von Michel (wegen Abhängigkeit von Meeting), muss noch
// implementiert werden
@Entity
@Table
public class Patient extends Person {

	@OneToMany(mappedBy = "patient")
	List<Meeting> meetings;

	DangerStateType dangerState;

	public Patient(String name, Date birthdate) {
		super(name, birthdate);
	}

	public DangerStateType getDangerState() {
		return this.dangerState;
	}

	public void setDangerState(DangerStateType newState) {
		this.dangerState = newState;
	}
}
