package ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

//TODO: Erstellt von Michel (wegen Abhängigkeit von Meeting), muss noch implementiert werden
// TODO: Erweitere PersonTile, wenn nötig

/**
 * Entity that represents a person. Can be a doctor or a patient. To use in the
 * data access layer.
 * 
 * @author K.Suter
 * 
 */
@Entity
@Table
public class Person extends AbstractDatabaseObject {

	private String dtype;
	private String name;
	private Date birthdate;

	public Person(String name, Date birthdate) {
		this(name, birthdate, "Person");
	}

	public Person(String name, Date birthdate, String dtype) {
		this.dtype = dtype;
		this.name = name;
		this.birthdate = birthdate;
	}

	public Person() {
	};

	public String getDtype() {
		return dtype;
	}

	public void setDtype(String dtype) {
		this.dtype = dtype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Override
	public String toString() {
		return "" + this.name + " / " + this.dtype;
	}
}
