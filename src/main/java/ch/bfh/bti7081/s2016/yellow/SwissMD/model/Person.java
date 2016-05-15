package ch.bfh.bti7081.s2016.yellow.SwissMD.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//TODO: Erstellt von Michel (wegen Abhängigkeit von Meeting), muss noch implementiert werden
// TODO: Erweitere PersonTile, wenn nötig

/**
 * Entity that represents a person. Can be a doctor or a patient. To use in the
 * data access layer.
 * 
 * @author K.Suter
 * 
 * */
@Entity
@Table
public class Person {

	@Id
	@GeneratedValue
	private Long id;

	private String name;
	private Date birthdate;

	public Person(String name, Date birthdate) {
		this.name = name;
		this.birthdate = birthdate;
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

}
