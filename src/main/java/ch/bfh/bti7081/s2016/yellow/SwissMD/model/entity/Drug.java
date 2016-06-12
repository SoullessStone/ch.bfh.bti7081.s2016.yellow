package ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DrugDTO;

/**
 * Entity that represents a drug. To use in the data access layer.
 * 
 * @author D. Halter
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table
public class Drug extends AbstractDatabaseObject {

	private String tradeName;
	private String substance;
	private String substanceQuantitiy;
	private int maxDose;

	public Drug() {
	}

	public Drug(DrugDTO drugDTO) {
		this.tradeName = drugDTO.getTradeName();
		this.substance = drugDTO.getSubstance();
		this.substanceQuantitiy = drugDTO.getSubstanceQuantitiy();
		this.maxDose = drugDTO.getMaxDose();
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
		return substanceQuantitiy;
	}

	public void setSubstanceQuantitiy(String substanceQuantitiy) {
		this.substanceQuantitiy = substanceQuantitiy;
	}

	public int getMaxDose() {
		return maxDose;
	}

	public void setMaxDose(int maxDose) {
		this.maxDose = maxDose;
	}

}
