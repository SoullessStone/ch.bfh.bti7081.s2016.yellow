package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import com.vaadin.event.MouseEvents.ClickListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.MeetingDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.i18nHelper;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.navigation.NavigationIndex;

public class MeetingTile extends Tile {
	private MeetingDTO meeting;

	public MeetingTile(MeetingDTO MeetingDTO) {
		this.meeting = MeetingDTO;
		GridLayout grid = new GridLayout(2, 1);
		VerticalLayout vertical = new VerticalLayout();
        
		setTitle("#" + meeting.getId() + " - " + meeting.getAppointmentTimeString());
		
		Link meetingLink = new Link("Meetingdetails",
				new ExternalResource("#!" + NavigationIndex.MEETINGVIEW+ "/" + meeting.getId()));
		meetingLink.setIcon(new ThemeResource("img/icons/binoculars_small.png"));
		
		vertical.addComponent(new Label("Patient: " + meeting.getPatient().getName()));
		vertical.addComponent(new Label("Arzt: " + meeting.getDoctor().getName()));
		vertical.addComponent(new Label("Status: " + i18nHelper.getMeetingStateTypeTranlation(meeting.getMeetingState())));
		vertical.addComponent(meetingLink);
		this.tile.addStyleName("noshadow");

		grid.addComponent(vertical, 0, 0);
		int end = meeting.getNotes().length();
		if (end > 100) {
			end = 100;
		}
		String notesField = meeting.getNotes().substring(0, end);
		grid.addComponent(new Label(notesField), 1, 0);
		grid.setSpacing(true);

		this.addComponent(grid);
	}

}
