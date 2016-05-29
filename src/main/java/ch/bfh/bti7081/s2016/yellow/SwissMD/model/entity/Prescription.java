package ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.IllegalDateRangeException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.DateRange;

/**
 * Entity that represents a prescription. To use in the data access layer.
 * 
 * @author K.Suter
 * 
 */
@Entity
@Table
public class Prescription extends AbstractDatabaseObject {
	
	@OneToOne
	private Drug drug;
	private int dosisInMilligrams;
	//needed only for persistence
	private Date validFrom;
	//needed only for persistence
	private Date validTo;
	@Transient
	private DateRange validity;

	@ManyToOne
	private Patient patient;

	public Prescription() {
		
	}

	public Prescription(Drug drug, int dosisInMilligrams,
			DateRange validity) {
		this.drug = drug;
		this.dosisInMilligrams = dosisInMilligrams;
		this.validFrom = validity.getFrom();
		this.validTo = validity.getTo();
		this.setValidity(validity);
	}

	public Drug getDrug() {
		return drug;
	}

	public void setDrug(Drug drug) {
		this.drug = drug;
	}

	public int getDosisInMilligrams() {
		return dosisInMilligrams;
	}

	public void setDosisInMilligrams(int dosisInMilligrams) {
		this.dosisInMilligrams = dosisInMilligrams;
	}
	
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public DateRange getValidity() {
		if (validity == null){
			try {
				validity = new DateRange(validFrom, validTo);
			} catch (IllegalDateRangeException e) {
				//Should not happen here..
			}
		}
		return validity;
	}

	public void setValidity(DateRange validity) {
		this.validity = validity;
		this.validFrom = validity.getFrom();
		this.validTo = validity.getTo();
	}
}
