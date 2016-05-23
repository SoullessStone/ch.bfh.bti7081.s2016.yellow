package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import java.util.Date;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.DateRange;

/**
 * Data transfer object for a {@code Prescription} entity. To use in the views.
 * 
 * @author K.Suter
 * 
 */
public class PrescriptionDTO extends GenericDTO {
	private DrugDTO medication;
	private int dosisInMilligrams;
	private Date validFrom;
	private Date validTo;

	public PrescriptionDTO(DrugDTO medication, int dosisInMilligrams,
			DateRange validity) {
		this.medication = medication;
		this.dosisInMilligrams = dosisInMilligrams;
		this.setValidity(validity);
	}


	public DrugDTO getMedication() {
		return medication;
	}

	public void setMedication(DrugDTO medication) {
		this.medication = medication;
	}

	public int getDosisInMilligrams() {
		return dosisInMilligrams;
	}

	public void setDosisInMilligrams(int dosisInMilligrams) {
		this.dosisInMilligrams = dosisInMilligrams;
	}

	public DateRange getValidity() {
		return new DateRange(this.validFrom, this.validTo);
	}

	public void setValidity(DateRange validity) {
		this.validFrom=validity.getFrom();
		this.validTo=validity.getTo();
	}

	@Override
	public String toString() {
		return "PrescriptionDTO [medication=" + medication
				+ ", dosisInMilligrams=" + dosisInMilligrams + "]";
	}

}
