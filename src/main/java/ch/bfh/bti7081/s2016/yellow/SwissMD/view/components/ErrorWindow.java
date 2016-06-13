package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import ch.bfh.bti7081.s2016.yellow.SwissMD.view.navigation.NavigationIndex;

import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;

public class ErrorWindow extends Window{
	
	private static final String ERROR_CAUSE = "Fehlerursache:";
	private static final String ERROR_DESCRIPTION = "Es ist ein schwerwiegender Fehler aufgetreten. Sie wurden abgemeldet. Bei wiederholtem auftreten, wenden Sie sich bitte an den Support.";
	private static final String ERROR_CAPTION = "Fehler";
	private AbstractOrderedLayout layout;
	
	public ErrorWindow() {
		super();
		setModal(true);
		setDraggable(false);
		setCaption(ERROR_CAPTION);
		setResizable(false);
		addCloseListener(new CloseListener() {	
			@Override
			public void windowClose(CloseEvent e) {
				UI.getCurrent().getNavigator().navigateTo(NavigationIndex.LOGINVIEW.getNavigationPath());	
			}
		});
		layout = new VerticalLayout();
		layout.setSpacing(true);
		layout.addComponent(new Label(ERROR_DESCRIPTION));
		setContent(layout);
	}
	
	public ErrorWindow(Exception ex) {
		this();
		layout.addComponent(new Label(ERROR_CAUSE));
		layout.addComponent(new Label(ex.getMessage()));
	}

}
