package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ErrorWindow extends Window{
	
	private AbstractOrderedLayout layout;
	
	public ErrorWindow() {
		super();
		setModal(true);
		setDraggable(false);
		setCaption("Fehler");
		layout = new VerticalLayout();
		setContent(layout);
	}
	
	public ErrorWindow(Exception ex) {
		this();
		layout.addComponent(new Label(ex.getLocalizedMessage()));
		// TODO Auto-generated constructor stub
	}

}
