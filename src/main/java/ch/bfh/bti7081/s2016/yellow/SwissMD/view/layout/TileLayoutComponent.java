package ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout;

import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.CustomComponent;

public abstract class TileLayoutComponent extends CustomComponent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6868160525557566767L;
	
	private int rowPos, colPos;
	protected float width;
	
	public void setPos(int row, int col){
		rowPos = row;
		colPos = col;
	}
	
	public void setWidth(int pixels){
		width = pixels;
		applyWidth();
	}
	
	public float getWidth(){
		return width;
	}
	
	public abstract void applyWidth();

}
