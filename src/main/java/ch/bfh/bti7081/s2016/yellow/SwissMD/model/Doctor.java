package ch.bfh.bti7081.s2016.yellow.SwissMD.model;

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
// TODO: Erstellt von Michel (wegen Abhängigkeit von Meeting), muss noch
// implementiert werden
@Entity
@Table
public class Doctor extends Person {

	@OneToMany(mappedBy = "doctor")
	List<Meeting> meetings;

	Integer officeNumber;

	public Doctor(String name, Date birthdate) {
		super(name, birthdate);
	}

	public Integer getOfficeNumber() {
		return this.officeNumber;
	}

	public void setDangerState(Integer officeNumber) {
		this.officeNumber = officeNumber;
	}
}
