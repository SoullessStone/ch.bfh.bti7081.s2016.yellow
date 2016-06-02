package ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity;

import java.util.Date;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DiagnosisDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.IllnessDTO;

public class Diagnosis extends AbstractDatabaseObject{

	private Illness illness;
	private String notes;
	private Date date;
	private Patient patient;
	
	public Diagnosis(){
	}
	
	public Diagnosis(DiagnosisDTO diagnosisDTO){
		this.date = diagnosisDTO.getDate();
		this.id = diagnosisDTO.getId();
		this.notes = diagnosisDTO.getNotes();
	}
	
	public Illness getIllness() {
		return illness;
	}
	public void setIllness(Illness illness) {
		this.illness = illness;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
}
