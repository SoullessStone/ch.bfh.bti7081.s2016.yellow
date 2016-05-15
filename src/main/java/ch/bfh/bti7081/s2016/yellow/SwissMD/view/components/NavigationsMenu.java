package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import ch.bfh.bti7081.s2016.yellow.SwissMD.NavigatorUI;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;

/**
 * Diese Klasse stellt das Navigationsmenü dar und wird von allen Views
 * eingebunden. Somit können Änderungen in der Navigation hier eingefügt werden
 */
@SuppressWarnings("serial")
public class NavigationsMenu extends CustomComponent {

	// Personensuche (sucht Patienten und Doktoren, navigiert Personenview)
	// Personenview (zeigt auch Doktoren an, Patienten haben mehr Informationen,
	// zb. History)
	// Verschreibungsview (anpassen und erstellen)
	// Wikiview
	// Sitzungsview

	public NavigationsMenu() {
		HorizontalLayout layout = new HorizontalLayout();
		MenuBar menubar = new MenuBar();
		layout.addComponent(createPersonSearchViewButton());
		layout.addComponent(createPersonViewButton());
		layout.addComponent(createPrescriptionViewButton());
		layout.addComponent(createWikiViewButton());
		layout.addComponent(createMeetingViewButton());
		layout.addComponent(logoutButton());
		layout.setSizeUndefined();
		layout.setSpacing(true);
		setSizeUndefined();
		setCompositionRoot(layout);
	}

	private Button createPersonSearchViewButton() {
		Button button = new Button("PersonSearchView",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						getUI().getNavigator().navigateTo(
								NavigatorUI.PERSONSEARCHVIEW);
					}
				});
		return button;
	}

	private Button createPersonViewButton() {
		Button button = new Button("PersonView", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(NavigatorUI.PERSONVIEW);
			}
		});
		return button;
	}

	private Button createPrescriptionViewButton() {
		Button button = new Button("PrescriptionView",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						getUI().getNavigator().navigateTo(
								NavigatorUI.PRESCRIPTIONVIEW);
					}
				});
		return button;
	}

	private Button createWikiViewButton() {
		Button button = new Button("WikiView", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(NavigatorUI.WIKIVIEW);
			}
		});
		return button;
	}

	private Button createMeetingViewButton() {
		Button button = new Button("MeetingView", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(
						NavigatorUI.MEETINGVIEW + "/Hanpeter");
			}
		});
		return button;
	}

	private Button logoutButton() {
		Button button = new Button("Logout", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getSession().close();
				getUI().getPage().setLocation(getLogoutPath());
			}
		});
		return button;
	}

	private String getLogoutPath() {
		return getUI().getPage().getLocation().getPath();
	}

}
