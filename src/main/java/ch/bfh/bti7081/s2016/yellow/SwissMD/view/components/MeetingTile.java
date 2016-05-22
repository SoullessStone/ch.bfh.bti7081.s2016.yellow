package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import java.util.List;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.MeetingDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PrescriptionDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Prescription;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;

public class MeetingTile extends Tile {
	private MeetingDTO meeting;

	public MeetingTile(MeetingDTO MeetingDTO) {
		this.meeting = MeetingDTO;

		setTitle(meeting.getAppointmentTimeString());

		addComponent(new Label("Patient: " + meeting.getPatient().getName()));
		addComponent(new Label("Arzt: " + meeting.getDoctor().getName()));
		
		List<PrescriptionDTO> prescriptions = meeting.getPatient().getPrescriptions();
		
		if (prescriptions != null && !prescriptions.isEmpty()) {
			addComponent(new Label("Rezeptverschreibungen: " + prescriptions.size()));
		}

	}

}
