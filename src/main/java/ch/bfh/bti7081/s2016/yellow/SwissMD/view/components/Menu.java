package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import ch.bfh.bti7081.s2016.yellow.SwissMD.view.NavigatorUI;

import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.MenuBar;

@SuppressWarnings("serial")
public class Menu extends CustomComponent {

	
	// Personensuche (sucht Patienten und Doktoren, navigiert Personenview)
	// Personenview (zeigt auch Doktoren an, Patienten haben mehr Informationen, zb. History)
	// Verschreibungsview (anpassen und erstellen)
	// Wikiview
	// Sitzungsview
	
	
	public Menu() {
		HorizontalLayout layout = new HorizontalLayout();
		MenuBar menubar = new MenuBar();
		layout.addComponent(mainButton());
		layout.addComponent(helpButton());
		layout.addComponent(logoutButton());
		layout.setSizeUndefined();
		layout.setSpacing(true);
		setSizeUndefined();
		setCompositionRoot(layout);
	}
	
	private Button mainButton() {
		Button button = new Button("Main", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(NavigatorUI.MAINVIEW);
			}
		});
		return button;
	}
	
	private Button helpButton() {
		Button button = new Button("Help", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(NavigatorUI.HELPVIEW);
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
