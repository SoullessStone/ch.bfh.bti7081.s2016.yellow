package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Prescription;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.DangerStateException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.IllegalDateRangeException;

public class PrescriptionDTOTest extends DtoTest {

	@Override
	public void testConstructor() {
		// Arrange
		Prescription prescription = null;
		try {
			prescription = super.createTestPrescription();
		} catch (IllegalDateRangeException e) {
			// TODO Fail Test
		}
		// Act
		PrescriptionDTO sut = null;
		sut = new PrescriptionDTO(prescription);
		// Assert
		super.validatePrescription(sut);
	}
}
