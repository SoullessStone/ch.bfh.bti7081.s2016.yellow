package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import java.util.Date;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Doctor;

public class DoctorDTO extends PersonDTO {

	public DoctorDTO(String name, Date birthdate) {
		setName(name);
		setBirthdate(birthdate);
		setDtype("Doctor");
	}

	public DoctorDTO(Doctor doctor) {
		super(doctor);
	}

}
