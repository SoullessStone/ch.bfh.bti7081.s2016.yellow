package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import java.util.Date;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Diagnosis;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.DangerStateException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.MeetingStateException;

/**
 * DTO for Diagnosis. To be used in the views
 * @author SoullessStone
 *
 */
public class DiagnosisDTO extends GenericDTO{
	private IllnessDTO illness;
	private String notes;
	private Date date;
	private PatientDTO patient;
	
	
	
	public DiagnosisDTO(IllnessDTO illness, String notes, Date date,
			PatientDTO patient) {
		super();
		this.illness = illness;
		this.notes = notes;
		this.date = new Date (date.getTime());
		this.patient = patient;
	}

	public DiagnosisDTO(Diagnosis diagnosis) throws DangerStateException{
		this.illness = new IllnessDTO(diagnosis.getIllness());
		this.notes = diagnosis.getNotes();
		this.date = diagnosis.getDate();
		this.patient = new PatientDTO(diagnosis.getPatient());
		setId(diagnosis.getId());
	}
	
	public IllnessDTO getIllness() {
		return illness;
	}
	public void setIllness(IllnessDTO illness) {
		this.illness = illness;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Date getDate() {
		return new Date(date.getTime());
	}
	
	public void setDate(Date date) {
		this.date = new Date(date.getTime());
	}

	@Override
	public String toString() {
		return "DiagnosisDTO [illness=" + illness + ", notes=" + notes
				+ ", date=" + date + "]";
	}

	public PatientDTO getPatient() {
		return patient;
	}

	public void setPatient(PatientDTO patient) {
		this.patient = patient;
	}

}
