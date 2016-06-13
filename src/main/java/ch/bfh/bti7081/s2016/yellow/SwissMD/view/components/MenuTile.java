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
 * @author nussa2
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
	private static final String MEETING = "Meetings";
	private static final String DIAGNOSIS = "Diagnosen";
	private static final String PRESCRIPTION = "Medikamente";
	private static final String ICONS_MAGNIFYING_GLASS = "img/icons/magnifying-glass-2.png";
	private static final String ICONS_TEAM = "img/icons/team.png";
	private static final String ICONS_SURGEON = "img/icons/surgeon.png";
	private static final String ICONS_BUSINESSMAN = "img/icons/businessman.png";
	private static final String BIGPERSON_STYLE = "bigPerson";
	private static final String LOGOUT_STYLE = "logout";
	private static final String VIEWNAME_SESSIONKEY = "activeView";

	public MenuTile() {
		PatientDTO patientInSession = SessionUtil.getPatientInSession(UI
				.getCurrent().getSession());

		if (patientInSession != null) {
			addComponent(createPersonSessionButton(patientInSession));
			addComponent(createViewButton(
					NavigationIndex.PRESCRIPTIONVIEW.getNavigationPath(),
					PRESCRIPTION, ICONS_PILLS, BIG_STYLE));
			addComponent(createViewButton(
					NavigationIndex.WIKIVIEW.getNavigationPath(), DIAGNOSIS,
					ICONS_BOOKS, BIG_STYLE));
			addComponent(createViewButton(
					NavigationIndex.MEETINGVIEW.getNavigationPath(), MEETING,
					ICONS_CALENDAR, BIG_STYLE));
		}
		addComponent(createViewButton(
				NavigationIndex.PERSONSEARCHVIEW.getNavigationPath(),
				PERSON_SEARCH, ICONS_MAGNIFYING_GLASS, BIG_STYLE));
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
			String path, String style) {
		Button button = new Button(displayName, new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				UI.getCurrent().getSession()
						.setAttribute(VIEWNAME_SESSIONKEY, viewName);
				getUI().getNavigator().navigateTo(viewName);
			}
		});
		button.setIcon(new ThemeResource(path));
		if (viewName.equals(UI.getCurrent().getSession().getAttribute(
				VIEWNAME_SESSIONKEY))) {
			button.addStyleName(style + "Active");
		}else{
			button.addStyleName(style);
		}
		return button;
	}

	private Button logoutButton() {
		DoctorDTO doctorInSession = SessionUtil.getDoctorInSession(UI
				.getCurrent().getSession());
		String buttonText = LOGOUT;

		if (doctorInSession != null) {
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
		button.addStyleName(LOGOUT_STYLE);
		button.setWidthUndefined();
		return button;
	}

	private String getLogoutPath() {
		return getUI().getPage().getLocation().getPath();
	}

	public Button createPersonSessionButton(PatientDTO patient) {
		if (patient == null) {
			Button b = createViewButton(
					NavigationIndex.PERSONVIEW.getNavigationPath(), PERSON,
					ICONS_TEAM, BIGPERSON_STYLE);
			return b;
		} else {
			Button b = createViewButton(
					NavigationIndex.PERSONVIEW.getNavigationPath(),
					patient.getName(), ICONS_BUSINESSMAN, BIGPERSON_STYLE);
			return b;
		}
	}

}
