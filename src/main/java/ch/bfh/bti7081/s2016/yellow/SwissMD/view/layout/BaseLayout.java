package ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout;

import java.util.List;

import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;

/**
 * Abstract BaseLayout class is the super type of all concrete Layouts.
 * Components are added with {@link BaseLayout#addComponent(Component)} to the layout sequently.
 * After last element has been added, {@link BaseLayout#finishLayout()} has to be called.
 *
 * @author nussa2
 *
 */
public abstract class BaseLayout extends CustomComponent {

	/**
	 * Adds a component to the next free space in the layout.
	 * 
	 * @param component
	 */
	public abstract void addComponent(Component component);

	/**
	 * Gets a list of all components registered on the layout.
	 * 
	 * @return
	 */
	public abstract List<Component> getComponents();

	/**
	 * Add a new "row" to the layout in order to place subsequently added elements below all previously added elements.
	 */
	public abstract void createRowBrake();

	/**
	 * Call this method after the last element has been added. This instructs the layout to calculate final positions adding footers, etc.
	 * After the layout has been finished, no more components can be added.
	 */
	public abstract void finishLayout();

}
