package ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.DateRange;

/**
 * Entity that represents a drug. To use in the data access layer.
 * 
 * @author D. Halter
 * 
 */
@Entity
@Table
public class Drug extends AbstractDatabaseObject {

	private String tradeName;
	private String substance;
	private String SubstanceQuantitiy;
	private int maxDose;

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

}
