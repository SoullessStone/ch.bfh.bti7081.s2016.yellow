package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.DoctorDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.SessionUtil;
import ch.bfh.bti7081.s2016.yellow.SwissMD.presenter.PersonPresenter;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.navigation.NavigationIndex;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class LoginTile extends Tile {

	private PersonPresenter personPresenter = new PersonPresenter();

	TextField username = new TextField("User-ID");
	PasswordField password = new PasswordField("Password");

	public LoginTile() {
		setTitleAndIcon("Login", "img/icons/locked-2.png");
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
		tile.setContent(contentLayout);
		setSizeUndefined();
	}

	private Button loginButton() {
		Button button = new Button("Log In", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if (doLogin()){
					getUI().getNavigator().navigateTo(
							NavigationIndex.MEETINGVIEW.getNavigationPath());
				} else {
					Notification.show("Ungültiges Login", Type.HUMANIZED_MESSAGE);
				}
			}
		});
		return button;
	}
	
	private boolean doLogin(){
		
		DoctorDTO doctor;
		boolean success = false;
		try {
			doctor = (DoctorDTO) personPresenter.findPersonById(Long.parseLong(username.getValue()));
			if (doctor != null){
				SessionUtil.setDoctorInSession(doctor, getUI().getSession());
				success = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;

	}

}
