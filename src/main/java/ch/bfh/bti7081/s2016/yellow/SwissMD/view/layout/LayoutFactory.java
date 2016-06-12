package ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout;


/**
 * LayoutFactory has to be used to get Instances of different concrete Layout
 * Factories.
 * 
 * @author nussa2
 *
 */
public abstract class LayoutFactory {

	public enum LayoutType {
		TILE_LAYOUT, SIMPLE_LAYOUT;
	}

	private static final LayoutFactory TILE_LAYOUT_INSTANCE = new TileLayoutFactory();
	private static final LayoutFactory SIMPLE_LAYOUT_INSTANCE = new SimpleLayoutFactory();

	/**
	 * Gets an instance of a concrete LayoutFactory specified by
	 * {@link LayoutType}.
	 * 
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public static LayoutFactory getInstance(LayoutType type) throws Exception {
		switch (type) {
		case TILE_LAYOUT:
			return TILE_LAYOUT_INSTANCE;
		case SIMPLE_LAYOUT:
			return SIMPLE_LAYOUT_INSTANCE;
		default:
			return SIMPLE_LAYOUT_INSTANCE;
		}
	}

	public final BaseLayout createLayout(String... args) throws Exception {
		for (String string : args) {
			String arg[] = string.split(":");
			if (arg.length == 2) {
				String key = arg[0];
				String value = arg[1];
				LayoutArguments argument = getArgument(key);
				if (argument != null) {
					setArgument(argument, value);
				} else {
					throw new Exception("unknown argument: " + key);
				}
			} else {
				throw new Exception("wrong argument format: " + string
						+ " (must be 'key:value')");
			}
		}
		return createLayout();
	}

	/**
	 * Create Layout with specified arguments. Arguments are defined in the
	 * concrete Factory.
	 * 
	 * @param args
	 * @return
	 */
	abstract BaseLayout createLayout();

	abstract LayoutArguments getArgument(String argumentName);

	abstract void setArgument(LayoutArguments argument, String value)
			throws Exception;

}
