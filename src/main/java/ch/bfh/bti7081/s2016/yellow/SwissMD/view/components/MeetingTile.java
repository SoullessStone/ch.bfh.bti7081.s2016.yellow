package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import java.util.List;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.MeetingDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PrescriptionDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Prescription;

public class MeetingTile extends CustomComponent {
	private MeetingDTO meeting;

	public MeetingTile(MeetingDTO MeetingDTO) {
		this.meeting = MeetingDTO;

		Panel tile = new Panel(meeting.getAppointmentTimeString());

		VerticalLayout contentLayout = new VerticalLayout();
		contentLayout.addComponent(new Label("Patient: " + meeting.getPatient().getName()));
		contentLayout.addComponent(new Label("Arzt: " + meeting.getDoctor().getName()));
		List<PrescriptionDTO> prescriptions = meeting.getPatient().getPrescriptions();
		if (prescriptions != null && !prescriptions.isEmpty()) {
			contentLayout.addComponent(new Label("Rezeptverschreibungen: " + prescriptions.size()));
		}
		contentLayout.setMargin(true);

		tile.setContent(contentLayout);
		tile.setWidth(300, Unit.PIXELS);
		setCompositionRoot(tile);
	}

}
