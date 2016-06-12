package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Prescription;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.DangerStateException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.IllegalDateRangeException;

public class PrescriptionDTOTest extends DTOTest {

	@Override
	public void testConstructor() {
		// Arrange
		Prescription prescription = null;
		PrescriptionDTO sut = null;

		try {
			// Act
			prescription = super.createTestPrescription();
			sut = new PrescriptionDTO(prescription);
			// Assert
			super.validatePrescription(sut);
		} catch (IllegalDateRangeException e) {
			// TODO Fail Test
		} catch (DangerStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
