package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Meeting;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.DangerStateException;

public class MeetingDTOTest extends DTOTest {

	@Override
	public void testConstructor() {
		// Arrange
		Meeting meeting = super.createTestMeeting();
		// Act
		MeetingDTO sut = null;
		try {
			sut = new MeetingDTO(meeting);
			// Assert
			super.validateMeeting(sut);
		} catch (DangerStateException e) {
			
			e.printStackTrace();
		}

	}
}
