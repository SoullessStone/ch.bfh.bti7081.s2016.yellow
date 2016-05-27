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
	
	// TODO implement in DB
	private String address;
	private String zip;
	private String city;
	private String mobile;
	private String landline;
//	private Integer dangerState;
//	private Integer officeNumber;

	/**
	 * "Change to PersonDTO(Person person)"
	 * @param name
	 * @param birthdate
	 * @param dtype
	 */
	@Deprecated
	public PersonDTO(String name, Date birthdate, String dtype) {
		this.name = name;
		this.birthdate = birthdate;
		this.dtype = dtype;
	}
	
	public PersonDTO(Person person) {
		this.name = person.getName();
		this.birthdate = person.getBirthdate();
		this.dtype = person.getDtype();
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
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getAddress() {
		// TODO real data
		return "Bundesplatz 1a";
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZip() {
		// TODO real data
		return "3000";
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		// TODO real data
		return "Bern";
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMobile() {
		// TODO real data
		return "079 666 66 66";
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getLandline() {
		// TODO real data
		return "031 390 90 90";
	}
	
	public void setLandline(String landline) {
		this.landline = landline;
	}

//	public Integer getDangerState() {
//		return dangerState;
//	}
//
//	public void setDangerState(Integer dangerState) {
//		this.dangerState = dangerState;
//	}

//	public Integer getOfficeNumber() {
//		return officeNumber;
//	}
//
//	public void setOfficeNumber(Integer officeNumber) {
//		this.officeNumber = officeNumber;
//	}
	@Override
	public String toString() {
		return "" + name + " / " + dtype;
	}
}
