package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Illness;

/**
 * Data transfer object for an {@code Illness} entity. To use in the views.
 * 
 * @author K. Suter
 * 
 */
public class IllnessDTO extends GenericDTO{
	
	/** The ICD-10 Code **/
	private String code;

	private String description;

	public IllnessDTO(String code, String description){
		this.code = code;
		this.description = description;
	}
	
	public IllnessDTO(Illness illness) {
		this.code = illness.getCode();
		this.description = illness.getDescription();
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
