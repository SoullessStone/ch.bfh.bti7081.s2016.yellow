package ch.bfh.bti7081.s2016.yellow.SwissMD.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.DateRange;

//TODO: Erstellt von Michel (wegen Abhängigkeit von Meeting), muss noch implementiert werden
@Entity
@Table
public class Prescription {
	
	@Id
    @GeneratedValue
    private Long id;

	// TODO: Medication ist kein String!
	private String medication;
	private int dosisInMilligrams;
	
	// TODO: Hibernate custom type
	//@OneToOne(fetch = FetchType.EAGER, mappedBy = "stock", cascade = CascadeType.ALL)
	//private DateRange validity;
	
	@ManyToOne
	private Meeting meeting;

	public Prescription(String medication, int dosisInMilligrams,
			DateRange validity) {
		this.medication = medication;
		this.dosisInMilligrams = dosisInMilligrams;
		//this.validity = validity;
	}

	public String getMedication() {
		return medication;
	}

	public void setMedication(String medication) {
		this.medication = medication;
	}

	public int getDosisInMilligrams() {
		return dosisInMilligrams;
	}

	public void setDosisInMilligrams(int dosisInMilligrams) {
		this.dosisInMilligrams = dosisInMilligrams;
	}

//	public DateRange getValidity() {
//		return validity;
//	}
//
//	public void setValidity(DateRange validity) {
//		this.validity = validity;
//	}

}
