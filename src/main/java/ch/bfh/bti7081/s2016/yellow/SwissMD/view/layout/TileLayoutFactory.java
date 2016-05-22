package ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout;

import com.vaadin.ui.HorizontalLayout;

import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.MenuTile;

public class TileLayoutFactory extends LayoutFactory {
	
	private int numberOfElementsPerRow = 1;

	private TileLayout layout;
	
	public enum Arguments{
		ELEMENTS_PER_ROW("elementsPerRow");
		
		private String name;
		
		Arguments(String name){
			this.name = name;
		}
		
		public String getName(){
			return name;
		}
		
		public static Arguments getArgumentByName(String name){
			for (Arguments argument : Arguments.values()) {
				if (argument.name.equals(name)){
					return argument;
				}
			}
			return null;
		}
		
	}
	
	public TileLayoutFactory() {
		// TODO Auto-generated constructor stub
	}

	public TileLayout createLayout(int numberOfColumns){
		this.numberOfElementsPerRow = numberOfColumns;
		createLayout();
		return layout;
	}

	@Override
	public TileLayout createLayout(String ... args) {
		for (String string : args) {
			String arg[] = string.split(":");
			if (arg.length == 2){
				String key = arg[0];
				String value = arg[1];
				setArgument(key, value);	
			}
		}
		layout = new TileLayout(numberOfElementsPerRow);
		
		layout.addComponent(new MenuTile());
		layout.createNewRow();
		
		layout.getBaseLayout().setSizeFull();
		layout.getBaseLayout().setSpacing(true);

		return layout;
	}
	
	private void setArgument(String key, String value){
		switch (Arguments.getArgumentByName(key)) {
		case ELEMENTS_PER_ROW:
			numberOfElementsPerRow = Integer.parseInt(value);
			break;
		default:
			break;
		}
	}

	
	
}
