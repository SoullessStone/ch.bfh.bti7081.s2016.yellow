package ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections.map.HashedMap;

import com.google.gwt.dom.client.Element;
import com.google.gwt.layout.client.Layout;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class TileLayout extends BaseLayout{
	
	private VerticalLayout baseLayout;
	
	private Map<Integer,HorizontalLayout> rows;
	
	private Map<HorizontalLayout,TileLayoutComponent> tiles;
	
	private int maxColumns;
	
	public TileLayout(int maxColumns){
		baseLayout = new VerticalLayout();
		rows = new HashedMap();
		tiles = new HashedMap();
		this.maxColumns = maxColumns;
		createNewRow();
	}
	
	int getDefaultColumNumber() {
		return maxColumns;
	}
	
	public VerticalLayout getBaseLayout(){
		return baseLayout;
	}

	void addRow(HorizontalLayout layout){
		baseLayout.addComponent(layout);	
		rows.put(rows.size(), layout);
	}
	
	private void addTile(TileLayoutComponent tile){
		addTile(tile, maxColumns);
	}
	
	private void addTile(TileLayoutComponent tile, int colPos){
		HorizontalLayout currentRow = rows.get(rows.size()-1);
		if (currentRow.getComponentCount() >= colPos){
			createNewRow();
			currentRow = rows.get(rows.size()-1);
			colPos = 1;
		}
		currentRow.addComponent(tile);
		tiles.put(currentRow, tile);
		tile.setPos(rows.size()-1, colPos);
	}
	
	public void createNewRow(){
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		addRow(horizontalLayout);
	}
	
	/*public void addTile(TileLayoutComponent tile){
		addRowElement(tile);
	}*/
	
	@Override
	public void addComponent(Component component) {
		addTile((TileLayoutComponent) component);
	}

	@Override
	public List<Component> getComponents() {
		return (List<Component>) tiles;
	}

	@Override
	public void createRowBrake() {
		createNewRow();	
	}
	/*
	@Override
	public void replaceComponent(Component oldComponent, Component newComponent) {
		List<Component> list = tiles.entrySet().stream().filter(c -> c.equals(oldComponent)).map(c -> c.getValue()).map(c -> (Component)c).collect(Collectors.toList());
		int index = list.indexOf(oldComponent);
			Component currentComponent = list.get(index);
			currentComponent = newComponent;
		//tiles.values().
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getComponentCount() {
		return tiles.size();
	}

	@Override
	public Iterator<Component> iterator() {
		return tiles.entrySet().stream().map(c -> (Component) c).iterator();
	}*/

	@Override
	public Component toVaadinComponent() {
		return getBaseLayout();
	}
	
}
