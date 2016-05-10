package ch.bfh.bti7081.s2016.yellow.SwissMD;

import javax.servlet.annotation.WebServlet;

import ch.bfh.bti7081.s2016.yellow.SwissMD.view.LoginView;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.MeetingView;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.PersonSearchView;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.PersonView;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.PrescriptionView;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.WikiView;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
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
public class NavigatorUI extends UI{

	public Navigator navigator;

	public static final String PERSONSEARCHVIEW = "PERSONSEARCH";
	public static final String PERSONVIEW = "PERSONVIEW";
	public static final String PRESCRIPTIONVIEW = "PRESCRIPTIONVIEW";
	public static final String WIKIVIEW = "WIKIVIEW";
	public static final String MEETINGVIEW = "MEETINGVIEW";

	@Override
	protected void init(VaadinRequest request) {
		
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);
		setContent(layout);
		ComponentContainerViewDisplay viewDisplay = new ComponentContainerViewDisplay(layout);
		navigator = new Navigator(UI.getCurrent(), viewDisplay);
		navigator.addView("", new LoginView());
		navigator.addView(PERSONSEARCHVIEW, new PersonSearchView());
		navigator.addView(PERSONVIEW, new PersonView());
		navigator.addView(PRESCRIPTIONVIEW, new PrescriptionView());
		navigator.addView(WIKIVIEW, new WikiView());
		navigator.addView(MEETINGVIEW, new MeetingView());
		
	}
	


    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = NavigatorUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
