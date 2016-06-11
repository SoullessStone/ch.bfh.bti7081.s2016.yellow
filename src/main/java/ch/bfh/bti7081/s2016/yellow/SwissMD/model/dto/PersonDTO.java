package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import java.util.Date;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Person;

/**
 * Data transfer object for a {@code Person} entity. To use in the views.
 * 
 * @author K.Suter
 * 
 */
public class PersonDTO extends GenericDTO {
	private String dtype;
	private String name;
	private Date birthdate;

	private String address;
	private String zip;
	private String city;
	private String mobile;
	private String landline;

	public PersonDTO() {
	}
	
	public PersonDTO(Person person) {
		this.name = person.getName();
		this.birthdate = person.getBirthdate();
		this.dtype = person.getDtype();
		this.address = person.getAddress();
		this.zip = person.getZip();
		this.city = person.getCity();
		this.mobile = person.getMobile();
		this.landline = person.getLandline();
		this.setId(person.getId());
	}

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
		return new Date(birthdate.getTime());
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = new Date(birthdate.getTime());
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLandline() {
		return landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	// public Integer getOfficeNumber() {
	// return officeNumber;
	// }
	//
	// public void setOfficeNumber(Integer officeNumber) {
	// this.officeNumber = officeNumber;
	// }
	@Override
	public String toString() {
		return "" + name + " / " + dtype;
	}
}
