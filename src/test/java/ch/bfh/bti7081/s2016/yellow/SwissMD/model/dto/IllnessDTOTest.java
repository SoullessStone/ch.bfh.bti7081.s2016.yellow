package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Illness;

public class IllnessDTOTest extends DtoTest {

	@Override
	public void testConstructor() {
		// Arrange
		Illness illness = super.createTestIllness();
		
		// Act
		IllnessDTO sut = new IllnessDTO(illness);
		
		// Assert
		super.validateIllness(sut);
	}
}
