package ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout;

/**
 * LayoutFactory has to be used to get Instances of different concrete Layout Factories.
 * 
 * @author nussa2
 *
 */
public abstract class LayoutFactory {

	public enum LayoutType {
		TILE_LAYOUT
	}

	private static final LayoutFactory TILE_LAYOUT_INSTANCE = new TileLayoutFactory();

	/**
	 * Gets an instance of a concrete LayoutFactory specified by {@link LayoutType}.
	 * 
	 * @param type
	 * @return
	 * @throws Exception
	 */
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

	/**
	 * Create Layout with specified arguments. Arguments are defined in the concrete Factory.
	 * 
	 * @param args
	 * @return
	 */
	public abstract BaseLayout createLayout(String... args);

}
