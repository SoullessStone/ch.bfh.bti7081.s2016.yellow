package ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity that represents a person. Can be a doctor or a patient. To use in the
 * data access layer.
 * 
 * @author K.Suter
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table
public class Person extends AbstractDatabaseObject {

	private String dtype;
	private String name;
	private Date birthdate;
	private String address;
	private String zip;
	private String city;
	private String mobile;
	private String landline;

	public Person(String name, Date birthdate) {
		this(name, birthdate, "Person");
	}

	public Person(String name, Date birthdate, String dtype) {
		this.dtype = dtype;
		this.name = name;
		this.birthdate = new Date(birthdate.getTime());
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

	@Override
	public String toString() {
		return "" + this.name + " / " + this.dtype;
	}
}
