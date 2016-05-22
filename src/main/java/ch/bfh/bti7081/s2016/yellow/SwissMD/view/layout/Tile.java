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
	protected Panel tile= new Panel();

	
	protected AbstractOrderedLayout contentLayout;
	
	public Tile(String title){
		setTitle(title);
		setCompositionRoot(tile);
		createDefaultLayout();
	}
	
	public Tile(){
		this("");
	}

	protected void createDefaultLayout() {
		contentLayout = new VerticalLayout();
		contentLayout.setSpacing(true);
		contentLayout.setMargin(true);
		tile.setContent(contentLayout);
		setWidth("100%");
		setStdWidth(1);
	}
	
	public void setTitle(String title){
		this.title = title;
		tile.setCaption(title);
	}
	
	public String getTitle(){
		return title;
	}
	
	public void addComponent(Component c){
		contentLayout.addComponent(c);
	}
	

	

}
