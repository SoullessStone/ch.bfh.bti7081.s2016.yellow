package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import org.junit.Test;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Patient;

public class PatientDTOTest extends DTOTest {

	@Test
	public void testConstructor() {
		// Arrange
		Patient patient = super.createTestPatient();

		// Act
		PatientDTO sut = new PatientDTO(patient);

		// Assert
		super.validatePatient(sut);
	}
}
