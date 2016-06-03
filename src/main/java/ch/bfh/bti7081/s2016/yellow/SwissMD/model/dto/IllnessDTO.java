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
	
	/** The name of the illness **/
	private String name;

	public IllnessDTO(String code, String name){
		this.code = code;
		this.name = name;
	}
	
	public IllnessDTO(Illness illness) {
		this.code = illness.getCode();
		this.name = illness.getName();
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
