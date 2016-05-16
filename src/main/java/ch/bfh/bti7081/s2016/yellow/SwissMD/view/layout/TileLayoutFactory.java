package ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout;

import com.vaadin.ui.HorizontalLayout;

import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.MenuTile;

public class TileLayoutFactory extends LayoutFactory {
	
	private int defaultNumberOfColumns = 1;
	
	private static final TileLayoutFactory INSTANCE = new TileLayoutFactory();
	
	private TileLayoutFactory(){}
	
	private TileLayout layout;
	
	public static TileLayoutFactory getInstance(){
		return INSTANCE;
	}

	public TileLayout createLayout(int numberOfColumns){
		this.defaultNumberOfColumns = numberOfColumns;
		createLayout();
		return layout;
	}

	@Override
	public TileLayout createLayout() {
		layout = new TileLayout(defaultNumberOfColumns);
		
		layout.addComponent(new MenuTile());
		layout.createNewRow();
		
		layout.getBaseLayout().setSizeFull();
		layout.getBaseLayout().setSpacing(true);

		return layout;
	}

	
	
}
