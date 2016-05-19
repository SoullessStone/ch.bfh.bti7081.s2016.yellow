package ch.bfh.bti7081.s2016.yellow.SwissMD.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.DateRange;

/**
 * Entity that represents a srug. To use in the data access layer.
 * 
 * @author D. Halter
 * 
 */

@Entity
@Table
public class Drug {

	@Id
	@GeneratedValue
	private Long id;
	
	private String tradeName;
	private String substance;
	private String SubstanceQuantitiy;
	private int maxDose;


	// TODO: Hibernate custom type
	// @OneToOne(fetch = FetchType.EAGER, mappedBy = "stock", cascade =
	// CascadeType.ALL)
	// private DateRange validity;


	public Drug(String tradeName, String substance, String SubstanceQuantitiy, int maxDose) {
		this.tradeName = tradeName;
		this.substance = substance;
		this.SubstanceQuantitiy = SubstanceQuantitiy;
		this.maxDose = maxDose;
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



	// public DateRange getValidity() {
	// return validity;
	// }
	//
	// public void setValidity(DateRange validity) {
	// this.validity = validity;
	// }

}
