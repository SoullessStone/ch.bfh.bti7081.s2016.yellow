package ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout;

import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.MenuTile;

/**
 * Factory for creating a new {@link TileLayout}.
 * 
 * @author nussa2
 *
 */
public class TileLayoutFactory extends LayoutFactory {

	private int numberOfElementsPerRow = 1;

	public enum Arguments implements LayoutArguments {
		ELEMENTS_PER_ROW("elementsPerRow");

		private String name;

		Arguments(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public static Arguments getArgumentByName(String name) {
			for (Arguments argument : Arguments.values()) {
				if (argument.name.equals(name)) {
					return argument;
				}
			}
			return null;
		}
	}

	TileLayoutFactory() {
	}

	@Override
	LayoutArguments getArgument(String argumentName) {
		return TileLayoutFactory.Arguments.getArgumentByName(argumentName);
	}

	TileLayout createLayout(int numberOfColumns) {
		this.numberOfElementsPerRow = numberOfColumns;
		return createLayout();
	}

	@Override
	TileLayout createLayout() {

		TileLayout layout = new TileLayout(numberOfElementsPerRow);
		MenuTile menuTile = new MenuTile();
		
		layout.addComponent(menuTile);
		layout.createNewRow();

		layout.baseLayout.setSizeFull();
		layout.baseLayout.setSpacing(true);

		return layout;
	}

	@Override
	void setArgument(LayoutArguments argument, String value) throws Exception {
		switch ((Arguments) argument) {
		case ELEMENTS_PER_ROW:
			numberOfElementsPerRow = Integer.parseInt(value);
			break;
		default:
			throw new Exception("unknown Argument '" + argument.getName() + "'");
		}
	}

}
