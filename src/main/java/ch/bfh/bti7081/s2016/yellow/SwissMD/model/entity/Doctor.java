package ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity that represents a doctor. To use in the data access layer.
 * 
 * @author K.Suter
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table
public class Doctor extends Person {

	@OneToMany(mappedBy = "doctor")
	List<Meeting> meetings;

	//TODO: change to string (office R.207)
	Integer officeNumber;

	public Doctor() {
	};

	public Doctor(String name, Date birthdate) {
		super(name, birthdate);
	}

	public Integer getOfficeNumber() {
		return this.officeNumber;
	}

	public void setOfficeNumber(Integer officeNumber) {
		this.officeNumber = officeNumber;
	}
}
