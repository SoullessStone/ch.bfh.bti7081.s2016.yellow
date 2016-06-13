package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import org.junit.Test;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Patient;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.DangerStateException;

public class PatientDTOTest extends DTOTest {

	@Test
	public void testConstructor() {
		// Arrange
		Patient patient = super.createTestPatient();

		// Act
		PatientDTO sut = null;
		try {
			sut = new PatientDTO(patient);
			
			// Assert
			super.validatePatient(sut);
		} catch (DangerStateException e) {
			
			e.printStackTrace();
		}


	}
}
