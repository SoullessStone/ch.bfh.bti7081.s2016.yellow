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
	
	/** The name of the illness **/
	private String name;
	
	public Illness() {	
	}
	
	public Illness(String code, String name){
		this.code = code;
		this.name = name;
	}
	
	public Illness(IllnessDTO ilnessDTO) {
		this.code = ilnessDTO.getCode();
		this.name = ilnessDTO.getName();
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
