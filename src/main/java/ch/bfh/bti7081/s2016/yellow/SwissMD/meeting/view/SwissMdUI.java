package ch.bfh.bti7081.s2016.yellow.SwissMD.meeting.view;


import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Widgetset("ch.bfh.bti7081.s2016.yellow.SwissMD.swissMDWidgetset")
public class SwissMdUI extends UI {

	Navigator navigator;
    protected static final String MAINVIEW = "start";

    @Override
    protected void init(VaadinRequest request) {
        getPage().setTitle("Navigation Example");

        // Create a navigator to control the views
        navigator = new Navigator(this, this);

        // Create and register the views
        navigator.addView("", new StartView());
        navigator.addView(MAINVIEW, new MainView());
    }
}