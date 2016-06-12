package ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.IteratorUtils;

import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * The TileLayout is a layout that consists of a number of horizontal containers
 * (called rows) which are added sequently in a vertical layout container. All
 * components added must be of type {@link TileLayoutComponent} and are added to
 * last row. Generation of rows is generally handled automatically. The maximal
 * number of elements that can be added to a row is defined by
 * {@link TileLayout#maxElementsPerRow}. If {@link TileLayout#createRowBrake()}
 * is called then the last component within this row is expanded to the maximal
 * available space if there is such. {@link TileLayout#finishLayout()} does
 * essentially the same as create row break but no more elements can be added to
 * the layout.
 * 
 * @author nussa2
 *
 */
public class TileLayout extends BaseLayout {

	VerticalLayout baseLayout;

	private List<HorizontalLayout> rows;

	int maxElementsPerRow;

	/*
	 * private boolean finished = false;
	 */

	TileLayout(int maxElementsPerRow) {
		baseLayout = new VerticalLayout();
		rows = new ArrayList<>();
		this.maxElementsPerRow = maxElementsPerRow;
		setCompositionRoot(baseLayout);
		createNewRow();
	}

	int getDefaultColumNumber() {
		return maxElementsPerRow;
	}

	void addRow(HorizontalLayout layout) {
		baseLayout.addComponent(layout);
		rows.add(layout);
	}

	private void addTile(TileLayoutComponent tile) {
		addTile(tile, maxElementsPerRow);
	}

	private void addTile(TileLayoutComponent tile, int elementsPerRow) {
		HorizontalLayout currentRow = getCurrentRow();
		int currentPos = currentRow.getComponentCount();
		if (currentPos >= elementsPerRow) {
			createNewRow();
			currentRow = getCurrentRow();
			currentPos = 1;
		}

		if ((elementsPerRow - calculateUsedTileSpace(currentRow)) < tile
				.getStdWidth()) {
			createNewRow();
			currentRow = getCurrentRow();
		}

		currentRow.addComponent(tile);
		// tiles.put(currentRow, tile);
		tile.setPos(rows.size() - 1, currentPos);
		System.out.println("set Tile pos to " + (rows.size() - 1) + ", "
				+ currentPos);
	}

	private void calculateElementExpansions(HorizontalLayout row) {
		int usedSize = 0;
		int maxSize = maxElementsPerRow;

		List<TileLayoutComponent> tiles = getRowElements(row);
		for (TileLayoutComponent tile : tiles) {
			if (usedSize >= maxSize) {
				createNewRowContainer();
				usedSize = 0;
			}
			float expandRatio = ((float) tile.getStdWidth()) / maxSize;
			usedSize += tile.getStdWidth();
			if (tiles.indexOf(tile) == tiles.size() - 1 && usedSize < maxSize) {
				expandRatio = (float) usedSize / maxSize;
			}
			row.setExpandRatio(tile, expandRatio);
		}
	}

	void createNewRow() {
		if (!rows.isEmpty()) {
			calculateElementExpansions(getCurrentRow());
		}
		createNewRowContainer();
	}

	private void createNewRowContainer() {
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setWidth("100%");
		horizontalLayout.setSpacing(true);
		addRow(horizontalLayout);
	}

	private HorizontalLayout getCurrentRow() {
		return rows.get(rows.size() - 1);
	}

	private List<TileLayoutComponent> getRowElements(HorizontalLayout row) {
		return IteratorUtils.toList(row.iterator());
	}

	private int calculateUsedTileSpace(HorizontalLayout row) {
		int usedSpace = 0;
		for (TileLayoutComponent tile : getRowElements(row)) {
			usedSpace += tile.getStdWidth();
		}
		return usedSpace;
	}

	@Override
	public void addComponent(Component component) {
		addTile((TileLayoutComponent) component);
	}

	@Override
	public List<Component> getComponents() {
		List<Component> components = new ArrayList<>();
		for (HorizontalLayout row : rows) {
			components.addAll(getRowElements(row));
		}
		return components;
	}

	@Override
	public void createRowBrake() {
		createNewRow();
	}

	@Override
	public void finishLayout() {
		// finished = true;
		if (!rows.isEmpty()) {
			calculateElementExpansions(getCurrentRow());
		}
	}

}
