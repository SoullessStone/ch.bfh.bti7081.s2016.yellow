package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;

import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.navigation.NavigationIndex;

public class MenuTile extends Tile {
	
	public MenuTile(){
		
		addComponent(createViewButton(NavigationIndex.PERSONSEARCHVIEW.getNavigationPath(),"PersonSearch"));
		addComponent(createViewButton(NavigationIndex.PERSONVIEW.getNavigationPath(),"Person"));
		addComponent(createViewButton(NavigationIndex.PRESCRIPTIONVIEW.getNavigationPath(),"Prescription"));
		addComponent(createViewButton(NavigationIndex.WIKIVIEW.getNavigationPath(),"PersonSearch"));
		addComponent(createViewButton(NavigationIndex.MEETINGVIEW.getNavigationPath()+ "/3","Meeting"));
		
		addComponent(logoutButton());

	}
	
	@Override
	protected void createDefaultLayout() {
		contentLayout = new HorizontalLayout();
		contentLayout.setSizeUndefined();
		//layout.setSpacing(true);
		tile.setContent(contentLayout);
		setCompositionRoot(contentLayout);
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
