package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Doctor;

public class DoctorDTOTest extends DtoTest {

	@Override
	public void testConstructor() {
		// Arrange
		Doctor doctor = super.createTestDoctor();
		// Act
		DoctorDTO sut = new DoctorDTO(doctor);
		// Assert
		super.validateDoctor(sut);
	}
}
