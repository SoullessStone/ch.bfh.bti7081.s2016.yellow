package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import org.junit.internal.runners.statements.Fail;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Drug;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Illness;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Prescription;
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
		PrescriptionDTO sut = new PrescriptionDTO(prescription);
		// Assert
		super.validatePrescription(sut);
	}
}
