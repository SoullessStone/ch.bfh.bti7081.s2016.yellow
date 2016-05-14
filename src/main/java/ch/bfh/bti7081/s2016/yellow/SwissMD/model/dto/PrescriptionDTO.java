package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.DateRange;

public class PrescriptionDTO {
	private String medication;
	private int dosisInMilligrams;
	private DateRange validity;

	public PrescriptionDTO(String medication, int dosisInMilligrams,
			DateRange validity) {
		this.medication = medication;
		this.dosisInMilligrams = dosisInMilligrams;
		this.validity = validity;
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

	public DateRange getValidity() {
		return validity;
	}

	public void setValidity(DateRange validity) {
		this.validity = validity;
	}
}
