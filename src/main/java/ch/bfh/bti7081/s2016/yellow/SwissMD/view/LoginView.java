package ch.bfh.bti7081.s2016.yellow.SwissMD.view;

import ch.bfh.bti7081.s2016.yellow.SwissMD.presenter.LoginPresenter;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.navigation.NavigationIndex;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

// TODO: Check User credentials
@SuppressWarnings("serial")
public class LoginView extends VerticalLayout implements View {
	private LoginPresenter loginPresenter = new LoginPresenter(this);

	public LoginView() {
		setSizeFull();
		setSpacing(true);

		Label label = new Label("Enter your information below to log in.");
		TextField username = new TextField("Username");
		PasswordField password = new PasswordField("Password");

		addComponent(label);
		addComponent(username);
		addComponent(password);
		addComponent(loginButton());
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
