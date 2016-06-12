package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PatientDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PersonDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.PatientInSessionManager;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.navigation.NavigationIndex;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

@SuppressWarnings("serial")
public class PatientInSessionTile extends Tile {
	private VerticalLayout verticalLayout = new VerticalLayout();

	public PatientInSessionTile(PatientDTO patient) {
		addComponent(verticalLayout);
		updateTile(patient);
		System.out.println("PatientInSessionTile created");
	}

	public void updateTile(PatientDTO patient) {
		verticalLayout.removeAllComponents();
		if (patient == null) {
			verticalLayout.addComponent(new Label(
					"Wählen Sie einen Patienten über die Personensuche"));
			return;
		}
		verticalLayout.addComponent(new Label("Name: " + patient.getName()));
		verticalLayout.addComponent(new Label("Name: " + patient.getCity()));
		verticalLayout.addComponent(createButton());
	}

	private Button createButton() {
		Button button = new Button("Löschen");
		button.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				PatientInSessionManager.getInstance().setPatientInSession(null);
				getUI().getNavigator().navigateTo(
						NavigationIndex.PERSONSEARCHVIEW.getNavigationPath());
			}
		});
		return button;
	}

}
