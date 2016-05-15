package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import java.util.Date;

/**
 * Data transfer object for a  {@code Person} entity. To use in the views.
 * 
 * @author K.Suter
 * 
 * */
public class PersonDTO {
	private String name;
	private Date birthdate;

	public PersonDTO(String name, Date birthdate) {
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
