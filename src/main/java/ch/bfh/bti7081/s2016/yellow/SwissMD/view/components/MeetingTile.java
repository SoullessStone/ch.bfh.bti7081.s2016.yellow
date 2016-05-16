package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import java.util.Date;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.MeetingDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PrescriptionDTO;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class MeetingTile extends CustomComponent {
	private MeetingDTO meeting;

	public MeetingTile(MeetingDTO MeetingDTO) {
		this.meeting = MeetingDTO;

		Panel tile = new Panel(meeting.getAppointmentTimeString());

		VerticalLayout contentLayout = new VerticalLayout();
		contentLayout.addComponent(new Label("Patient: "
				+ meeting.getPatient().getName()));
		contentLayout.addComponent(new Label("Arzt: "
				+ meeting.getDoctor().getName()));
		if (meeting.getPrescriptions() != null && !meeting.getPrescriptions().isEmpty()) {
			contentLayout.addComponent(new Label("Rezeptverschreibungen: " + meeting.getPrescriptions().size()));
		}
		contentLayout.setMargin(true);

		tile.setContent(contentLayout);
		tile.setWidth(300, Unit.PIXELS);
		setCompositionRoot(tile);
	}

}
