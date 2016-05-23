package ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout;

/**
 * @author nussa2
 *
 */
public abstract class LayoutFactory {

	public enum LayoutType {
		TILE_LAYOUT
	}

	private static final LayoutFactory TILE_LAYOUT_INSTANCE = new TileLayoutFactory();

	public static LayoutFactory getInstance(LayoutType type) throws Exception {
		switch (type) {
		case TILE_LAYOUT:
			return TILE_LAYOUT_INSTANCE;
		default:
			throw new Exception("No such layout found.");
		}

	}

	/*
	 * public BaseLayout createLayout(String ... args){ for (String string :
	 * args) {
	 * 
	 * } return createLayout(args); }
	 */

	public abstract BaseLayout createLayout(String... args);

}
