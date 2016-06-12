package ch.bfh.bti7081.s2016.yellow.SwissMD.view;

import ch.bfh.bti7081.s2016.yellow.SwissMD.presenter.LoginPresenter;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.LoginTile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.BaseLayout;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.LayoutFactory;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.LayoutFactory.LayoutType;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;

/**
 * Login-View, könnte implementiert werden, falls mal Security gewünscht wäre
 * 
 * @author SoullessStone
 *
 */
@SuppressWarnings("serial")
public class LoginView extends CustomComponent implements View {
	private LoginPresenter loginPresenter = new LoginPresenter(this);
	private BaseLayout layout;

	public LoginView() {
		setSizeFull();
		try {
			layout = LayoutFactory.getInstance(LayoutType.SIMPLE_LAYOUT)
					.createLayout("componentsAlignment:center");

			setCompositionRoot(layout);

		} catch (Exception e1) {
			// TODO Go to error View
			e1.printStackTrace();
		}
	}

	@Override
	public void enter(ViewChangeEvent event) {
		Notification.show("Welcome! Please log in.");

		Tile tile = new LoginTile();
		tile.addStyleName("my-login");
		layout.addComponent(tile);
	}

}
