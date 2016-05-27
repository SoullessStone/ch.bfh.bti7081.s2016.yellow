package ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout;

import com.vaadin.ui.CustomComponent;

/**
 * Abstract base class for all elements that can be added to {@link TileLayout}.
 * TileLayoutComponents have a standardized width within the layout. This means
 * that width of all tiles are a multiple of that standard width.
 * 
 * @author nussa2
 */
public abstract class TileLayoutComponent extends CustomComponent {

	private static final long serialVersionUID = -6868160525557566767L;

	private int rowPos, colPos;

	private int stdWidth;

	void setPos(int row, int col) {
		rowPos = row;
		colPos = col;
	}

	int getRowPos() {
		return rowPos;
	}

	int getColPos() {
		return colPos;
	}

	/**
	 * Gets the number of standard units that this component is using within the
	 * layout.
	 * 
	 * @return
	 */
	public int getStdWidth() {
		return stdWidth;
	}

	/**
	 * Set the number of standard units that this component should use within
	 * the layout.
	 * 
	 * @param stdWidth
	 */
	public void setStdWidth(int stdWidth) {
		this.stdWidth = stdWidth;
	}

}
