package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DoctorDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PatientDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.SessionUtil;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.navigation.NavigationIndex;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

/**
 * Stellt das Menü dar
 * 
 * @author Nussbaum
 *
 */
@SuppressWarnings("serial")
public class MenuTile extends Tile {
	
	private static final String ICONS_MAGNIFYING_GLASS = "img/icons/magnifying-glass-2.png";
	private static final String ICONS_TEAM = "img/icons/team.png";
	private static final String ICONS_SURGEON = "img/icons/surgeon.png";
	private static final String ICONS_BUSINESSMAN = "img/icons/businessman.png";

	public MenuTile() {
		PatientDTO patientInSession = SessionUtil.getPatientInSession(UI.getCurrent().getSession());

		addComponent(createPersonSessionButton(patientInSession));
		addComponent(createViewButton(
				NavigationIndex.PRESCRIPTIONVIEW.getNavigationPath(),
				"Prescription", "img/icons/pills-3.png"));
		addComponent(createViewButton(
				NavigationIndex.WIKIVIEW.getNavigationPath() + "/1", "Wiki",
				"img/icons/books-2.png"));
		addComponent(createViewButton(
				NavigationIndex.MEETINGVIEW.getNavigationPath(), "Meeting",
				"img/icons/calendar-2.png"));
		addComponent(createViewButton(
				NavigationIndex.PERSONSEARCHVIEW.getNavigationPath(),
				"PersonSearch", ICONS_MAGNIFYING_GLASS));
		addComponent(logoutButton());

		System.out.println("MenuTile created");

	}

	@Override
	protected void createDefaultLayout() {
		contentLayout = new HorizontalLayout();
		contentLayout.setSizeUndefined();
		contentLayout.setSpacing(true);
		tile.setContent(contentLayout);
		setCompositionRoot(contentLayout);
	}

	private Button createViewButton(String viewName, String displayName,
			String path) {
		Button button = new Button(displayName, new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(viewName);
			}
		});
		button.setIcon(new ThemeResource(path));
		button.addStyleName("big");
		return button;
	}

	private Button logoutButton() {
		DoctorDTO doctorInSession = SessionUtil.getDoctorInSession(UI.getCurrent().getSession());
		String buttonText = "Logout";
		
		if (doctorInSession != null){
			buttonText = doctorInSession.getName();
		}

		Button button = new Button(buttonText, new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getSession().close();
				getUI().getPage().setLocation(getLogoutPath());
			}
		});
		button.setIcon(new ThemeResource("img/icons/exit-2.png"));
		button.addStyleName("big");
		button.setWidthUndefined();
		return button;
	}

	private String getLogoutPath() {
		return getUI().getPage().getLocation().getPath();
	}
	
	public Button createPersonSessionButton(PatientDTO patient) {
		if (patient == null) {
			return createViewButton(
					NavigationIndex.PERSONVIEW.getNavigationPath(), "Person",
					ICONS_TEAM);
		} else {
			return createViewButton(
					NavigationIndex.PERSONVIEW.getNavigationPath(), patient.getName(),
					ICONS_BUSINESSMAN);
		}
	}

}
