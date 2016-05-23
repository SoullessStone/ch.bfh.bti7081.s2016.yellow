package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

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

	// TODO: CustomType implementieren oder in validFrom und ValidTo aufteilen
	// private DateRange validity;
	// private Date validFrom;
	// private Date validTo;

	public PrescriptionDTO(DrugDTO medication, int dosisInMilligrams,
			DateRange validity) {
		this.medication = medication;
		this.dosisInMilligrams = dosisInMilligrams;
		// this.validity = validity;
	}

	public PrescriptionDTO(DrugDTO medication, int dosisInMilligrams) {
		this.medication = medication;
		this.dosisInMilligrams = dosisInMilligrams;
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
		// return validity;
		return null;
	}

	public void setValidity(DateRange validity) {
		// this.validity = validity;
	}

	@Override
	public String toString() {
		return "PrescriptionDTO [medication=" + medication
				+ ", dosisInMilligrams=" + dosisInMilligrams + "]";
	}

}
