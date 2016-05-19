package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import java.util.Date;

/**
 * Data transfer object for a {@code Drug} entity. To use in the views.
 * 
 * @author D. Halter
 * 
 */
public class DrugDTO {
	private String tradeName;
	private String substance;
	private String SubstanceQuantitiy;
	private String dose;

	public DrugDTO(String tradeName, String substance, String SubstanceQuantitiy, String dose) {
		this.tradeName = tradeName;
		this.substance = substance;
		this.SubstanceQuantitiy = SubstanceQuantitiy;
		this.dose = dose;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getSubstance() {
		return substance;
	}

	public void setSubstance(String substance) {
		this.substance = substance;
	}

	public String getSubstanceQuantitiy() {
		return SubstanceQuantitiy;
	}

	public void setSubstanceQuantitiy(String substanceQuantitiy) {
		SubstanceQuantitiy = substanceQuantitiy;
	}

	public String getDose() {
		return dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}
	


}
