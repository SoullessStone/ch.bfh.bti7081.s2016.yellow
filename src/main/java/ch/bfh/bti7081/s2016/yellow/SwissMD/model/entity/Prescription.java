package ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.DateRange;

/**
 * Entity that represents a prescription. To use in the data access layer.
 * 
 * @author K.Suter
 * 
 */
// TODO: Erstellt von Michel (wegen Abhängigkeit von Meeting), muss noch
// implementiert werden
@Entity
@Table
public class Prescription extends AbstractDatabaseObject {

	private Drug medication;
	private int dosisInMilligrams;

	// TODO: CustomType implementieren oder in validFrom und ValidTo aufteilen
	// private DateRange validity;
	// private Date validFrom;
	// private Date validTo;

	@ManyToOne
	private Patient patient;

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Prescription(Drug medication, int dosisInMilligrams,
			DateRange validity) {
		this.medication = medication;
		this.dosisInMilligrams = dosisInMilligrams;
		// this.validity = validity;
	}

	public Drug getMedication() {
		return medication;
	}

	public void setMedication(Drug medication) {
		this.medication = medication;
	}

	public int getDosisInMilligrams() {
		return dosisInMilligrams;
	}

	public void setDosisInMilligrams(int dosisInMilligrams) {
		this.dosisInMilligrams = dosisInMilligrams;
	}

	public DateRange getValidity() {
		// return validity;
		return null;
	}

	public void setValidity(DateRange validity) {
		// this.validity = validity;
	}
}
