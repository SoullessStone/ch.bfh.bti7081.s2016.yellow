package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import java.util.Date;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Prescription;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.MeetingStateException;
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
	private PatientDTO patient;


	public PrescriptionDTO(DrugDTO drug, int dosisInMilligrams,
			DateRange validity, PatientDTO patient) {
		this.patient = patient;
		this.drug = drug;
		this.dosisInMilligrams = dosisInMilligrams;
		this.setValidity(validity);
	}
	
	public PrescriptionDTO(Prescription prescription) throws MeetingStateException {
		this.patient = new PatientDTO(prescription.getPatient());
		this.drug = new DrugDTO(prescription.getDrug());
		this.dosisInMilligrams = prescription.getDosisInMilligrams();
		this.setValidity(prescription.getValidity());
		this.setId(prescription.getId());
	}
	
	public PrescriptionDTO(Prescription prescription, PatientDTO patient) {
		this.patient = patient;
		this.drug = new DrugDTO(prescription.getDrug());
		this.dosisInMilligrams = prescription.getDosisInMilligrams();
		this.setValidity(prescription.getValidity());
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
	
	public PatientDTO getPatient() {
		return patient;
	}

	public void setPatient(PatientDTO patient) {
		this.patient = patient;
	}

}
