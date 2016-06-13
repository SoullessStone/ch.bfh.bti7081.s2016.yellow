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
	
	private static final String LOGOUT = "Logout";
	private static final String PERSON = "Person";
	private static final String BIG_STYLE = "big";
	private static final String ICONS_EXIT = "img/icons/exit-2.png";
	private static final String ICONS_CALENDAR = "img/icons/calendar-2.png";
	private static final String ICONS_BOOKS = "img/icons/books-2.png";
	private static final String ICONS_PILLS = "img/icons/pills-3.png";
	private static final String PERSON_SEARCH = "Person search";
	private static final String MEETING = "Meeting";
	private static final String DIAGNOSIS = "Diagnosis";
	private static final String PRESCRIPTION = "Prescription";
	private static final String ICONS_MAGNIFYING_GLASS = "img/icons/magnifying-glass-2.png";
	private static final String ICONS_TEAM = "img/icons/team.png";
	private static final String ICONS_SURGEON = "img/icons/surgeon.png";
	private static final String ICONS_BUSINESSMAN = "img/icons/businessman.png";

	public MenuTile() {
		PatientDTO patientInSession = SessionUtil.getPatientInSession(UI.getCurrent().getSession());

		addComponent(createPersonSessionButton(patientInSession));
		addComponent(createViewButton(
				NavigationIndex.PRESCRIPTIONVIEW.getNavigationPath(),
				PRESCRIPTION, ICONS_PILLS));
		addComponent(createViewButton(
				NavigationIndex.WIKIVIEW.getNavigationPath() + "/1", DIAGNOSIS,
				ICONS_BOOKS));
		addComponent(createViewButton(
				NavigationIndex.MEETINGVIEW.getNavigationPath(), MEETING,
				ICONS_CALENDAR));
		addComponent(createViewButton(
				NavigationIndex.PERSONSEARCHVIEW.getNavigationPath(),
				PERSON_SEARCH, ICONS_MAGNIFYING_GLASS));
		addComponent(logoutButton());

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
		button.addStyleName(BIG_STYLE);
		return button;
	}

	private Button logoutButton() {
		DoctorDTO doctorInSession = SessionUtil.getDoctorInSession(UI.getCurrent().getSession());
		String buttonText = LOGOUT;
		
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
		button.setIcon(new ThemeResource(ICONS_EXIT));
		button.addStyleName(BIG_STYLE);
		button.setWidthUndefined();
		return button;
	}

	private String getLogoutPath() {
		return getUI().getPage().getLocation().getPath();
	}
	
	public Button createPersonSessionButton(PatientDTO patient) {
		if (patient == null) {
			return createViewButton(
					NavigationIndex.PERSONVIEW.getNavigationPath(), PERSON,
					ICONS_TEAM);
		} else {
			return createViewButton(
					NavigationIndex.PERSONVIEW.getNavigationPath(), patient.getName(),
					ICONS_BUSINESSMAN);
		}
	}

}
