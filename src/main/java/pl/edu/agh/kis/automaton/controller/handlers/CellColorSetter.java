package pl.edu.agh.kis.automaton.controller.handlers;

import pl.edu.agh.kis.automaton.view.GridCell;

/**
 * Allows setting new cell color/image.
 * @author Wojciech Kumoń
 */
public interface CellColorSetter {
	/**
	 * Sets new cell color/image.
	 * @param cell which will get new color/image
	 */
	void click(GridCell cell);
}
