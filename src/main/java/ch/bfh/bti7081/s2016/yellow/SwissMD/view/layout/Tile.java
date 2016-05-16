package ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout;

import java.awt.image.TileObserver;
import java.io.Serializable;

import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class Tile extends TileLayoutComponent {	

	private String title;
	protected Panel tile;
	
	protected AbstractOrderedLayout contentLayout;
	
	public Tile(){
		tile = new Panel();
		title="";
		createDefaultLayout();
		System.out.println("tile created");
	}

	protected void createDefaultLayout() {
		contentLayout = new VerticalLayout();
		contentLayout.setMargin(true);
	
		tile.setContent(contentLayout);
		setCompositionRoot(tile);
	}
	
	public void setTitle(String title){
		tile.setCaption(title);
	}
	
	public void addComponent(Component c){
		contentLayout.addComponent(c);
	}

	@Override
	public void applyWidth() {
		setWidth(width, Unit.PIXELS);	
	}
	

}
