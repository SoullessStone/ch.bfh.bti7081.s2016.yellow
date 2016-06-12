package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Meeting;

public class MeetingDTOTest extends DTOTest {

	@Override
	public void testConstructor() {
		// Arrange
		Meeting meeting = super.createTestMeeting();
		// Act
		MeetingDTO sut = new MeetingDTO(meeting);
		// Assert
		super.validateMeeting(sut);
	}
}
