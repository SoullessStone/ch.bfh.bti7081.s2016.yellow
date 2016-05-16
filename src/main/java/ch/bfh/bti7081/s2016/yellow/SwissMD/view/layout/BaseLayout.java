package ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout;

import java.util.List;

import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Component;

public abstract class BaseLayout {
	
	public abstract void addComponent(Component component);
	
	public abstract List<Component> getComponents();
	
	public abstract void createRowBrake();
	
	public abstract Component toVaadinComponent();

}
