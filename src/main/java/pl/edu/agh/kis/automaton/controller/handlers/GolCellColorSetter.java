package pl.edu.agh.kis.automaton.controller.handlers;

import pl.edu.agh.kis.automaton.view.CellImages;
import pl.edu.agh.kis.automaton.view.GridCell;

/**
 * Allows setting new cell image.
 * @author Wojciech Kumoń
 */
public class GolCellColorSetter implements CellColorSetter {

	/**
	 * If cell is white, this method makes it black, otherwise cell will be
	 * white.
	 * @param cell cell which will get new icon
	 */
	@Override
	public void click(GridCell cell) {
		if (cell.getIcon() == CellImages.WHITE) {
			cell.setIcon(CellImages.BLACK);
		} else {
			cell.setIcon(CellImages.WHITE);
		}
	}

}
