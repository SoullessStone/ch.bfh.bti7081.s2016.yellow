package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import java.util.List;


import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.MeetingDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PrescriptionDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;

import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Component.Event;

public class MeetingTile extends Tile {
	private MeetingDTO meeting;

	public MeetingTile(MeetingDTO MeetingDTO) {
		this.meeting = MeetingDTO;

		setTitle("#" + meeting.getId() + " - " + meeting.getAppointmentTimeString());

		addComponent(new Label("Patient: " + meeting.getPatient().getName()));
		addComponent(new Label("Arzt: " + meeting.getDoctor().getName()));
		addComponent(new Label("Status: " + meeting.getMeetingState()));
		List<PrescriptionDTO> prescriptions = meeting.getPatient()
				.getPrescriptions();
		if (prescriptions != null && !prescriptions.isEmpty()) {
			addComponent(new Label("Rezeptverschreibungen: "
					+ prescriptions.size()));
		}
	}

}
