package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Drug;

/**
 * Data transfer object for a {@code Medication} entity. To use in the views.
 * 
 * @author D. Halter
 * 
 */
public class DrugDTO extends GenericDTO {
	private String tradeName;
	private String substance;
	private String SubstanceQuantitiy;
	private int maxDose;

	public DrugDTO(String tradeName, String substance,
			String SubstanceQuantitiy, int maxDose) {
		this.tradeName = tradeName;
		this.substance = substance;
		this.SubstanceQuantitiy = SubstanceQuantitiy;
		this.maxDose = maxDose;
	}
	
	public DrugDTO(Drug drug) {
		this.tradeName = drug.getTradeName();
		this.substance = drug.getSubstance();
		this.SubstanceQuantitiy = drug.getSubstanceQuantitiy();
		this.maxDose = drug.getMaxDose();
	}

	public DrugDTO(String tradeName) {
		this.tradeName = tradeName;
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

	public int getMaxDose() {
		return maxDose;
	}

	public void setMaxDose(int maxDose) {
		this.maxDose = maxDose;
	}

	@Override
	public String toString() {
		return "" + tradeName + " / " + substance;
	}
}
