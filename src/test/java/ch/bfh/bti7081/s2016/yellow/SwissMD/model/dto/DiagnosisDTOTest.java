package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import org.junit.Test;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Diagnosis;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.DangerStateException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.MeetingStateException;

public class DiagnosisDTOTest extends DtoTest{

	@Test
	public void testConstructor() {
		// Arrange
		Diagnosis diagnosis = super.createTestDiagnosis();
		
		// Act
		DiagnosisDTO sut = null;
		try {
			sut = new DiagnosisDTO(diagnosis);
			// Assert
			super.validateDiagnosis(sut);
		} catch (DangerStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
