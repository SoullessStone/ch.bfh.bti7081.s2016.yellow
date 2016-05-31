package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import com.vaadin.server.Page;
import com.vaadin.server.Page.Styles;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Button;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.navigation.NavigationIndex;

public class LoginTile extends Tile {
	
	TextField username = new TextField("Username");
	PasswordField password = new PasswordField("Password");
	
	public LoginTile() {
		setTitleAndIcon("Login","img/icons/locked-2.png");
		addComponent(username);
		addComponent(password);
		addComponent(loginButton());
	}
	
	@Override
	protected void createDefaultLayout() {
		contentLayout = new VerticalLayout();
		contentLayout.setSpacing(true);
		contentLayout.setMargin(true);
		contentLayout.setSizeFull();
		
		tile.setWidth("300px");	
		//tile.setHeight("300px");	
		tile.setContent(contentLayout);
		setSizeUndefined();
	}

	private Button loginButton() {
		Button button = new Button("Log In", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
					getUI().getNavigator().navigateTo(
							NavigationIndex.MEETINGVIEW.getNavigationPath());
			}
		});
		return button;
	}
	

}
