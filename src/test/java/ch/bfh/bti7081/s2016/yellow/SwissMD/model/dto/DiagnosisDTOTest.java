package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import org.junit.Test;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Diagnosis;

public class DiagnosisDTOTest extends DtoTest{

	@Test
	public void testConstructor(){
		// Arrange
		Diagnosis diagnosis = super.createTestDiagnosis();
		
		// Act
		DiagnosisDTO sut = new DiagnosisDTO(diagnosis);
		
		// Assert
		super.validateDiagnosis(sut);
	}
}
