package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PatientDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.PatientInSessionManager;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.navigation.NavigationIndex;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

/**
 * Stellt den sich aktuell in der Session Patienten dar
 * 
 * @author SoullessStone
 *
 */
@SuppressWarnings("serial")
public class PatientInSessionTile extends Tile {
	private VerticalLayout verticalLayout = new VerticalLayout();
	private HorizontalLayout horizontalLayout = new HorizontalLayout();

	public PatientInSessionTile(PatientDTO patient) {
		addComponent(horizontalLayout);
		updateTile(patient);
		System.out.println("PatientInSessionTile created");
	}

	public void updateTile(PatientDTO patient) {
		horizontalLayout.removeAllComponents();
		horizontalLayout.addComponent(verticalLayout);
		if (patient != null) {
			horizontalLayout.addComponent(createRemoveImage());
		}
		verticalLayout.removeAllComponents();
		if (patient == null) {
			verticalLayout.addComponent(new Label(
					"Wählen Sie einen Patienten über die Personensuche"));
			return;
		}
		verticalLayout.addComponent(new Label("Name: " + patient.getName()));
		verticalLayout.addComponent(new Label("Stadt: " + patient.getCity()));
	}

	private Button createRemoveImage() {
		Button pictureButton = new Button();
		pictureButton.setStyleName(BaseTheme.BUTTON_LINK);
		pictureButton.setIcon(new ThemeResource("img/icons/remove.png"));
		pictureButton.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				PatientInSessionManager.getInstance().setPatientInSession(null,
						null, getUI().getSession());
				getUI().getNavigator().navigateTo(
						NavigationIndex.PERSONSEARCHVIEW.getNavigationPath());
			}
		});
		return pictureButton;
	}
}
