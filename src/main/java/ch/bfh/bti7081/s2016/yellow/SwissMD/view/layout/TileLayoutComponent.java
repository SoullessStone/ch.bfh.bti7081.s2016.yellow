package ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout;

import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.CustomComponent;

public abstract class TileLayoutComponent extends CustomComponent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6868160525557566767L;
	
	private int rowPos, colPos;
	
	private int stdWidth;
	
	public void setPos(int row, int col){
		rowPos = row;
		colPos = col;
	}
	
	public int getStdWidth(){
		return stdWidth;
	}

	public void setStdWidth(int stdWidth) {
		this.stdWidth = stdWidth;
	}
	

}
