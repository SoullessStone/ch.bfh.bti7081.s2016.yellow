package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Drug;

public class DrugDTOTest extends DTOTest {

	@Override
	public void testConstructor() {
		// Arrange
		Drug drug = super.createTestDrug();
		// Act
		DrugDTO sut = new DrugDTO(drug);
		// Assert
		super.validateDrug(sut);
	}
}
