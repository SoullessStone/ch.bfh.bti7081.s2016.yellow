package ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout;

import com.vaadin.ui.Alignment;

import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.TileLayoutFactory.Arguments;

public class SimpleLayoutFactory extends LayoutFactory {

	private Alignment componentsAlignment;
	
	public enum Arguments implements LayoutArguments{
		COMPONENTS_ALIGNMENT("componentsAlignment");

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


	@Override
	LayoutArguments getArgument(String argumentName) {
		return SimpleLayoutFactory.Arguments.getArgumentByName(argumentName);
	}

	@Override
	BaseLayout createLayout() {
		
		SimpleLayout layout = new SimpleLayout();
		layout.setSizeFull();
		layout.baseLayout.setSizeFull();
		layout.alignment = componentsAlignment;

		return layout;
	}

	@Override
	void setArgument(LayoutArguments argument, String value) throws Exception {
		switch ((Arguments)argument) {
		case COMPONENTS_ALIGNMENT:
			switch (value) {
			case "left":
				componentsAlignment = Alignment.MIDDLE_LEFT;
				break;
			case "right":
				componentsAlignment = Alignment.MIDDLE_RIGHT;
				break;
			case "center":
				componentsAlignment = Alignment.MIDDLE_CENTER;
				break;
			default:
				throw new Exception("unknown value '"+value+"' for argument '"+argument.getName()+"'");
			}
			break;
		default:
			throw new Exception("unknown Argument '"+argument.getName()+"'");
		}
		
	}

}
