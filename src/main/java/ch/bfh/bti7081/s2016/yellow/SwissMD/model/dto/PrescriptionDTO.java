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
	private DrugDTO drug;
	private int dosisInMilligrams;
	private DateRange validity;

	public PrescriptionDTO(DrugDTO drug, int dosisInMilligrams,
			DateRange validity) {
		this.drug = drug;
		this.dosisInMilligrams = dosisInMilligrams;
		this.setValidity(validity);
	}

	public DrugDTO getDrug() {
		return drug;
	}

	public void setDrug(DrugDTO drug) {
		this.drug = drug;
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

	@Override
	public String toString() {
		return "PrescriptionDTO [medication=" + drug
				+ ", dosisInMilligrams=" + dosisInMilligrams + "]";
	}

}
