package ch.bfh.bti7081.s2016.yellow.SwissMD.view.navigation;

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
	
	HorizontalLayout layout;

	// Personensuche (sucht Patienten und Doktoren, navigiert Personenview)
	// Personenview (zeigt auch Doktoren an, Patienten haben mehr Informationen,
	// zb. History)
	// Verschreibungsview (anpassen und erstellen)
	// Wikiview
	// Sitzungsview
	
	public HorizontalLayout getLayout(){
		return layout;
	}

	NavigationsMenu() {
		layout = new HorizontalLayout();
		//MenuBar menubar = new MenuBar();

		layout.addComponent(createViewButton(NavigationIndex.PERSONSEARCHVIEW.getNavigationPath(),"PersonSearch"));
		layout.addComponent(createViewButton(NavigationIndex.PERSONVIEW.getNavigationPath(),"Person"));
		layout.addComponent(createViewButton(NavigationIndex.PRESCRIPTIONVIEW.getNavigationPath(),"Prescription"));
		layout.addComponent(createViewButton(NavigationIndex.WIKIVIEW.getNavigationPath(),"PersonSearch"));
		layout.addComponent(createViewButton(NavigationIndex.MEETINGVIEW.getNavigationPath()+ "/Hanpeter","Meeting"));

		layout.addComponent(logoutButton());
		layout.setSizeUndefined();
		layout.setSpacing(true);
		setSizeUndefined();
		setCompositionRoot(layout);
	}
	
	private Button createViewButton(String viewName, String displayName) {
		Button button = new Button(displayName,
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						getUI().getNavigator().navigateTo(
								viewName);
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
