package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import java.io.File;

import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.navigation.NavigationIndex;

import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.themes.Reindeer;

public class MenuTile extends Tile {

	public MenuTile() {

		addComponent(createViewButton(
				NavigationIndex.PERSONSEARCHVIEW.getNavigationPath(),
				"PersonSearch","img/patients.png"));
		addComponent(createViewButton(
				NavigationIndex.PERSONVIEW.getNavigationPath(), "Person","img/patients.png"));
		addComponent(createViewButton(
				NavigationIndex.PRESCRIPTIONVIEW.getNavigationPath(),
				"Prescription","img/medicine.png"));
		addComponent(createViewButton(
				NavigationIndex.WIKIVIEW.getNavigationPath(), "Wiki","img/library.png"));
		addComponent(createViewButton(
				NavigationIndex.MEETINGVIEW.getNavigationPath() + "/3",
				"Meeting","img/note.png"));

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

	private Button createViewButton(String viewName, String displayName, String path) {
		Button button = new Button(displayName, new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(viewName);
			}
		});
		//button.setIcon(new ThemeResource(path));
		//button.setIcon(ThemeIcons);
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
