package ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout;

import java.util.List;

import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;

public abstract class BaseLayout extends CustomComponent {
	
	public abstract void addComponent(Component component);
	
	public abstract List<Component> getComponents();
	
	public abstract void createRowBrake();
	
	public abstract void finishLayout();

}
