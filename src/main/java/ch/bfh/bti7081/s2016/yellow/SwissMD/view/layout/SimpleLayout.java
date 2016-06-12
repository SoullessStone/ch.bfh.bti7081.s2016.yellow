package ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout;

import java.util.List;

import org.apache.commons.collections.IteratorUtils;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

public class SimpleLayout extends BaseLayout {

	VerticalLayout baseLayout;

	Alignment alignment;

	public SimpleLayout() {
		baseLayout = new VerticalLayout();
		setCompositionRoot(baseLayout);
		alignment = Alignment.TOP_LEFT;
	}

	@Override
	public void addComponent(Component component) {
		baseLayout.addComponent(component);
		baseLayout.setComponentAlignment(component, alignment);
	}

	@Override
	public List<Component> getComponents() {
		return IteratorUtils.toList(baseLayout.iterator());
	}

	@Override
	public void createRowBrake() {
		// TODO Alex Auto-generated method stub

	}

	@Override
	public void finishLayout() {
		// TODO Alex Auto-generated method stub

	}

}
