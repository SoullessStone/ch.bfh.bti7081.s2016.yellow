package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import java.util.Date;

public class DoctorDTO extends PersonDTO {

	public DoctorDTO(String name, Date birthdate) {
		super(name, birthdate, "Doctor");
	}

}
