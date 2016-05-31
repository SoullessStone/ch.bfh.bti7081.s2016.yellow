package ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.IllnessDTO;

/**
 * Entity that represents an illness. Can be used by a diagnosis.
 * 
 * @author K. Suter
 * 
 */
@Entity
@Table
public class Illness extends AbstractDatabaseObject {

	/** The ICD-10 Code **/
	private String code;

	private String description;
	
	public Illness(String code, String description){
		this.code = code;
		this.description = description;
	}
	
	public Illness(IllnessDTO ilnessDTO) {
		this.code = ilnessDTO.getCode();
		this.description = ilnessDTO.getDescription();
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
