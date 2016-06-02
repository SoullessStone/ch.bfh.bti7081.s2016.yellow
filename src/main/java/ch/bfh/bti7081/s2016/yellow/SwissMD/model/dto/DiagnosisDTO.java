package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import java.util.Date;

/**
 * DTO for Diagnosis. To be used in the views
 * @author SoullessStone
 *
 */
public class DiagnosisDTO extends GenericDTO{
	private IllnessDTO illness;
	private String notes;
	private Date date;
	
	// TODO: "Entity-Constructor"
	@Deprecated
	public DiagnosisDTO(IllnessDTO illnessDTO, String notes, Date date){
		this.illness = illnessDTO;
		this.notes = notes;
		this.date = date;
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
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "DiagnosisDTO [illness=" + illness + ", notes=" + notes
				+ ", date=" + date + "]";
	}

}
