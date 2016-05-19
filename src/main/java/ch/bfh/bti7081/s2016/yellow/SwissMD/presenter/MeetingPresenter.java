package ch.bfh.bti7081.s2016.yellow.SwissMD.presenter;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DrugDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PersonDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PrescriptionDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.CouldNotSaveException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.DateRange;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.MeetingView;

public class MeetingPresenter {
	// TODO: Knows the model
	private MeetingView meetingView;

	public MeetingPresenter(MeetingView meetingView) {
		System.out.println("init MeetingPresenter");
		this.meetingView = meetingView;
	}

	public void save() throws CouldNotSaveException {
		// TODO: DO!
		System.out.println("Saving...");
	}

	public PersonDTO getPatientForMeeting(int i) {
		// TODO: Real Data
		return new PersonDTO("Peter Test", new Date(768907564000L));
	}

	public PersonDTO getDoctorForMeeting(int i) {
		// TODO: Real Data
		return new PersonDTO("Dieter Dümmlich", new Date(9997564000L));
	}

	public DrugDTO getDrugForMeeting(int i) {
		// TODO: Real Data
		return new DrugDTO("Aspirin", "Acidum acetylsalicylicum","324 mg",  8);
	}
	
	public List<PrescriptionDTO> getPerscriptionsForMeeting(int i) {
		// TODO: Real Data
		return Arrays.asList(new PrescriptionDTO("Aspirin", 1_000,
				new DateRange(new Date(1451606400000L), new Date(1454284800000L))));
	}
}
