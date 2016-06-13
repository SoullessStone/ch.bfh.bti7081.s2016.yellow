package ch.bfh.bti7081.s2016.yellow.SwissMD.view.navigation;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.Navigator.ComponentContainerViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

// https://github.com/degiere/vaadin-navigation-example

@Theme("mytheme")
@Widgetset("ch.bfh.bti7081.s2016.yellow.SwissMD.MyAppWidgetset")
public class NavigatorUI extends UI {

	public Navigator navigator;

	@Override
	protected void init(VaadinRequest request) {

		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);
		setContent(layout);
		ComponentContainerViewDisplay viewDisplay = new ComponentContainerViewDisplay(
				layout);

		navigator = new Navigator(UI.getCurrent(), viewDisplay);

		for (NavigationIndex index : NavigationIndex.values()) {
			String navPath = index.getNavigationPath();
			navigator.addView(navPath, index.getViewClass());
		}

	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = NavigatorUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}
}
