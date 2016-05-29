package ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout;

import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * Standard implementation of {@link TileLayoutComponent}
 * 
 * @author nussa2
 *
 */
public class Tile extends TileLayoutComponent {

	private String title;
	protected Panel tile = new Panel();

	protected AbstractOrderedLayout contentLayout;

	public Tile(String title) {
		setTitle(title);
		setCompositionRoot(tile);
		createDefaultLayout();
	}
	
	public Tile(String title, String path) {
		setTitleAndIcon(title, path);
		setCompositionRoot(tile);
		createDefaultLayout();
	}
	
	public Tile() {
		this("");
	}

	/**
	 * Override this method to customize the internal Layout of the tile.
	 */
	protected void createDefaultLayout() {
		contentLayout = new VerticalLayout();
		contentLayout.setSpacing(true);
		contentLayout.setMargin(true);
		tile.setContent(contentLayout);
		setWidth("100%");
		setStdWidth(1);
	}

	public void setTitleAndIcon(String title, String path) {
		this.title = title;
		tile.setIcon(new ThemeResource(path));
		tile.setCaption(title);
	}

	public void setTitle(String title) {
		this.title = title;
		tile.setCaption(title);
	}
	
	public String getTitle() {
		return title;
	}

	public void addComponent(Component c) {
		contentLayout.addComponent(c);
	}

}
