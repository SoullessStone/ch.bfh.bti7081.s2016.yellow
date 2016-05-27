package ch.bfh.bti7081.s2016.yellow.SwissMD.view;

import ch.bfh.bti7081.s2016.yellow.SwissMD.presenter.LoginPresenter;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.BaseLayout;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.LayoutFactory;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.LayoutFactory.LayoutType;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.TileLayoutFactory;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.navigation.NavigationIndex;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

// TODO: Check User credentials
@SuppressWarnings("serial")
public class LoginView extends CustomComponent implements View {
	private LoginPresenter loginPresenter = new LoginPresenter(this);
	private BaseLayout layout;

	public LoginView() {
		try {
			layout = LayoutFactory.getInstance(LayoutType.TILE_LAYOUT)
					.createLayout(
							TileLayoutFactory.Arguments.ELEMENTS_PER_ROW
									.getName() + ":3");

			layout.setSizeFull();

			Label label = new Label("Enter your information below to log in.");
			TextField username = new TextField("Username");
			PasswordField password = new PasswordField("Password");

			Tile tile = new Tile();
			tile.addComponent(label);
			tile.addComponent(username);
			tile.addComponent(password);
			tile.addComponent(loginButton());
			layout.addComponent(tile);
			layout.createRowBrake();
			layout.finishLayout();
			setCompositionRoot(layout);

		} catch (Exception e1) {
			// TODO Go to error View
			e1.printStackTrace();
		}
	}

	@Override
	public void enter(ViewChangeEvent event) {
		Notification.show("Welcome! Please log in.");
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
