package pl.edu.agh.kis.automaton.controller.handlers;

import java.util.Map;

import javax.swing.Icon;

import pl.edu.agh.kis.automaton.model.CellCoordinates;
import pl.edu.agh.kis.automaton.model.CellState;

/**
 * Converts coordinates mapped to icons into coordinates mapped to cell states.
 * @author Wojciech Kumoń
 */
public interface IconToStateConverter {
	/**
	 * Converts coordinates mapped to icons into coordinates mapped to cell states.
	 * @param structure coords mapped to icons
	 * @return coords mapped to cell states
	 */
	Map<CellCoordinates, CellState> convert(Map<CellCoordinates, Icon> structure);
}
